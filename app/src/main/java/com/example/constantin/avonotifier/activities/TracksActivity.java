package com.example.constantin.avonotifier.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.constantin.avonotifier.App;
import com.example.constantin.avonotifier.Globals;
import com.example.constantin.avonotifier.R;
import com.example.constantin.avonotifier.impl.GoogleCalenderScheduler;
import com.example.constantin.avonotifier.impl.ToastMessages;
import com.example.constantin.avonotifier.logic.AppDateFormatter;
import com.example.constantin.avonotifier.logic.Dossie;
import com.example.constantin.avonotifier.logic.DossieFetcher;
import com.example.constantin.avonotifier.logic.DossieNotifications;
import com.example.constantin.avonotifier.logic.IAppMessages;
import com.example.constantin.avonotifier.logic.ICalendarScheduler;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.logic.MTime;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.MeetingsCalendar;
import com.example.constantin.avonotifier.logic.ScheduledMeetings;
import com.example.constantin.avonotifier.logic.Track;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TracksActivity extends AppCompatActivity
        implements DossieFetcher.Handler, TrackView.Handler, OnDateSelectedListener {
    boolean mTwoPane;
    IAppMessages inAppNotifications;
    IAppMessages calendarNotifications;

    @BindView(R.id.item_list)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;

    @BindView(R.id.app_bar)
    View appBar;

    @BindView(R.id.selectedDayMeetings)
    LinearLayout selectedDayMeetings;

    TracksAdapter recyclerAdapter;

    IUserStorage userStorage;
    MeetingsCalendar meetingsCalendar;
    DossieFetcher dossieTracker;
    DossieNotifications notifications;
    ICalendarScheduler calendar;
    AppDateFormatter formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracks_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        userStorage = Globals.userStorage;
        dossieTracker = Globals.dossieTracker;
        formatter = Globals.dateFormatter;

        inAppNotifications = new ToastMessages(getApplicationContext());
        calendarNotifications = new ToastMessages(getApplicationContext(), Gravity.TOP);
        notifications = new DossieNotifications(this, DetailsActivity.class, formatter);
        calendar = new GoogleCalenderScheduler(this);

        ScheduledMeetings scheduled = new ScheduledMeetings(userStorage.getDossies());
        meetingsCalendar = new MeetingsCalendar(scheduled.all());

        dossieTracker.AddHandler(this);
        mTwoPane = findViewById(R.id.item_detail_container) != null;

        initRecyclerView();
        initCalendar();
    }

    private void initCalendar() {
        calendarView.setSaveEnabled(true);
        calendarView.setSelectedDate(Calendar.getInstance());

        List<CalendarDay> daysWithOneDot = meetingsCalendar.daysWith(1);
        List<CalendarDay> daysWithTwoDots = meetingsCalendar.daysWith(2);
        List<CalendarDay> daysWithThreeDots = meetingsCalendar.daysWith(-1);

        DotsDecorator oneDotDecorator = new DotsDecorator(daysWithOneDot, new DotSpan(5, Color.WHITE));
        DotsDecorator twoDotDecorator = new DotsDecorator(daysWithTwoDots, new TwoDotsSpan(5, Color.WHITE));
        DotsDecorator threeDotDecorator = new DotsDecorator(daysWithThreeDots, new ThreeDotsSpan(5, Color.WHITE));

        calendarView.addDecorator(oneDotDecorator);
        calendarView.addDecorator(twoDotDecorator);
        calendarView.addDecorator(threeDotDecorator);
        calendarView.setOnDateChangedListener(this);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay day, boolean selected) {
        MTime mtime = new MTime(day.getYear(), day.getMonth(), day.getDay());
        List<Meeting> meetings = meetingsCalendar.meetingsInDay(mtime);

        selectedDayMeetings.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(selectedDayMeetings.getContext());
        for(Meeting meeting: meetings) {
            View view = inflater.inflate(R.layout.track_view_light, selectedDayMeetings, true);

            Track track = new Track(meeting.getDossieId());
            TrackView trackView = new TrackView(view, formatter, TracksActivity.this);
            trackView.Configure(track, meeting);
        }
    }

    private void initRecyclerView() {
        Context context = recyclerView.getContext();
        recyclerAdapter = new TracksAdapter(context, userStorage, formatter, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        App app = (App)getApplication();
        app.onSuspend();
    }

    void showAddTrackDialog() {
        DialogFragment newFragment = TrackDialog.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        newFragment.show(fragmentManager, "dialog");
    }

    public void openGoogleCalendar(View view) {
        calendar.open();
    }

    @Override
    public void HandleTracked(DossieFetcher.Result result) {
        userStorage.addTrack(result.track);
        userStorage.addDossier(DossieFromTrack(result.track));

        List<Track> tracks = userStorage.getTracks();
        recyclerAdapter.notifyItemChanged(tracks.size() - 1);
        inAppNotifications.show("Traking: " + result.track.getDossieId());
    }

    public static Dossie DossieFromTrack(Track track) {
        return new Dossie(track.getDossieId(), FakeMeetings(track.getDossieId()));
    }

    public static List<Meeting> FakeMeetings(String dossieId) {
        ArrayList<Meeting> meetings = new ArrayList<>(20);
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            calendar.add(Calendar.HOUR_OF_DAY, i * 5 * random.nextInt(10));
            meetings.add(new Meeting(dossieId, dossieId, calendar.getTimeInMillis()));
        }

        return meetings;
    }

    @Override
    public void TrackClicked(Track track) {
        Context context = recyclerView.getContext();
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(DetailsFragment.DOSSIE_ID, track.getDossieId());

            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(DetailsFragment.DOSSIE_ID, track.getDossieId());
            context.startActivity(intent);

//            Dossie dossie = userStorage.getDossie(track.getDossieId());
//            Meeting[] meetings = dossie.getMeetings();
//            notifications.showUpdated(meetings[meetings.length - 1]);
        }
    }

    public void onNewDossieFabHandler(View view) {
        showAddTrackDialog();
    }

    public void showUpcomingMeetings(View view) {
        ScheduledMeetings schedule = new ScheduledMeetings(userStorage.getDossies());
        List<Meeting> upcoming = schedule.upcoming(5, 7);
        notifications.showUpcoming(upcoming);
    }

}

class TracksAdapter extends RecyclerView.Adapter<TrackView> {
    Context context;
    TrackView.Handler trackHandler;
    IUserStorage IUserStorage;
    AppDateFormatter formatter;

    public TracksAdapter(Context context,
                         IUserStorage IUserStorage,
                         AppDateFormatter formatter,
                         TrackView.Handler itemHandler) {
        this.context = context;
        this.IUserStorage = IUserStorage;
        this.formatter = formatter;
        this.trackHandler = itemHandler;
    }

    @Override
    public TrackView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.track_view, parent, false);
        return new TrackView(view, formatter, trackHandler);
    }

    @Override
    public void onBindViewHolder(final TrackView itemViewHolder, int position) {
        List<Track> tracks = IUserStorage.getTracks();
        Track track = tracks.get(position);
        Dossie dossie = IUserStorage.getDossie(track.getDossieId());

        List<Meeting> meetings = dossie.getMeetings();
        itemViewHolder.Configure(track, meetings.get(meetings.size() - 1));
    }

    @Override
    public int getItemCount() {
        return IUserStorage.getTracks().size();
    }
}

class TrackView extends RecyclerView.ViewHolder implements View.OnClickListener {
    public interface Handler {
        void TrackClicked(Track track);
    }

    View view;
    Handler clickHandler;
    AppDateFormatter formatter;

    @BindView(R.id.id)
    TextView idView;

    @BindView(R.id.content)
    TextView contentView;

    @BindView(R.id.dossierDate)
    TextView dossierDate;

    public TrackView(View view, AppDateFormatter formatter, Handler handler) {
        super(view);
        this.view = view;
        this.formatter = formatter;
        this.clickHandler = handler;

        ButterKnife.bind(this, view);
    }

    public void Configure(Track track, Meeting meeting) {
        view.setTag(R.string.trackViewTag, track);
        view.setOnClickListener(this);

        idView.setText(track.getDossieId());
        contentView.setText(track.getDossieId());
        dossierDate.setText(formatter.getDay(meeting.getTime()));
    }

    @Override
    public void onClick(View v) {
        Track track = (Track)v.getTag(R.string.trackViewTag);
        clickHandler.TrackClicked(track);
    }
}

class TrackViewLight extends RecyclerView.ViewHolder implements View.OnClickListener {
    public interface Handler {
        void TrackClicked(Track track);
    }

    View view;
    Handler clickHandler;
    AppDateFormatter formatter;

    @BindView(R.id.id)
    TextView idView;

    @BindView(R.id.content)
    TextView contentView;

    @BindView(R.id.dossierDate)
    TextView dossierDate;

    public TrackViewLight(View view, AppDateFormatter formatter, Handler handler) {
        super(view);
        this.view = view;
        this.formatter = formatter;
        this.clickHandler = handler;

        ButterKnife.bind(this, view);
    }

    public void Configure(Track track, Meeting meeting) {
        view.setTag(R.string.trackViewTag, track);
        view.setOnClickListener(this);

        idView.setText(track.getDossieId());
        contentView.setText(track.getDossieId());
        dossierDate.setText(formatter.getDay(meeting.getTime()));
    }

    @Override
    public void onClick(View v) {
        Track track = (Track)v.getTag(R.string.trackViewTag);
        clickHandler.TrackClicked(track);
    }
}