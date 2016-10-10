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
import com.example.constantin.avonotifier.logic.Dossier;
import com.example.constantin.avonotifier.logic.DossieNotifications;
import com.example.constantin.avonotifier.logic.DownloaderHandler;
import com.example.constantin.avonotifier.logic.IAppMessages;
import com.example.constantin.avonotifier.logic.ICalendarScheduler;
import com.example.constantin.avonotifier.logic.IDossierDownloader;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.logic.Time;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.MeetingsCalendar;
import com.example.constantin.avonotifier.logic.Track;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TracksActivity extends AppCompatActivity
        implements DownloaderHandler, TrackTappedHandler, OnDateSelectedListener {
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

    @BindView(R.id.selectedDay)
    TextView selectedDay;

    TracksAdapter recyclerAdapter;

    IUserStorage userStorage;
    MeetingsCalendar meetingsCalendar;
    IDossierDownloader dossierDownloader;
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
        dossierDownloader = Globals.dossierDownloader;
        formatter = Globals.dateFormatter;
        meetingsCalendar = Globals.meetingsCalendar;

        inAppNotifications = new ToastMessages(getApplicationContext());
        calendarNotifications = new ToastMessages(getApplicationContext(), Gravity.TOP);
        notifications = new DossieNotifications(this, DetailsActivity.class, formatter);
        calendar = new GoogleCalenderScheduler(this);

        mTwoPane = findViewById(R.id.item_detail_container) != null;

        initRecyclerView();
        initCalendar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dossierDownloader.subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dossierDownloader.unsubscribe(this);
    }

    private void initCalendar() {
        calendarView.setSaveEnabled(true);
        calendarView.setSelectedDate(Calendar.getInstance());

        UpdateCalendarDecorations();

        calendarView.setOnDateChangedListener(this);
    }

    private void UpdateCalendarDecorations() {
        List<CalendarDay> daysWithOneDot = meetingsCalendar.daysWith(1, 2);
        List<CalendarDay> daysWithTwoDots = meetingsCalendar.daysWith(2, 3);
        List<CalendarDay> daysWithThreeDots = meetingsCalendar.daysWith(3, Integer.MAX_VALUE);

        DotsDecorator oneDotDecorator = new DotsDecorator(daysWithOneDot, new DotSpan(5, Color.WHITE));
        DotsDecorator twoDotDecorator = new DotsDecorator(daysWithTwoDots, new TwoDotsSpan(5, Color.WHITE));
        DotsDecorator threeDotDecorator = new DotsDecorator(daysWithThreeDots, new ThreeDotsSpan(5, Color.WHITE));

        calendarView.removeDecorators();
        calendarView.addDecorator(oneDotDecorator);
        calendarView.addDecorator(twoDotDecorator);
        calendarView.addDecorator(threeDotDecorator);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay day, boolean selected) {
        Calendar c = day.getCalendar();
        Time time = Time.FromCalendar(c);
        List<Meeting> meetings = meetingsCalendar.meetingsInDay(time);

        selectedDayMeetings.removeAllViews();
        selectedDay.setText(formatter.getDay(c.getTimeInMillis()));

        LayoutInflater inflater = LayoutInflater.from(selectedDayMeetings.getContext());
        for(Meeting meeting: meetings) {
            View view = inflater.inflate(R.layout.track_view_light, null, false);

            Track track = new Track(meeting.getDossieId());
            TrackViewLight trackView = new TrackViewLight(view, formatter, TracksActivity.this);
            trackView.Configure(track, meeting);

            selectedDayMeetings.addView(view);
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
    public void HandleDossierDownload(DownloaderHandler.DownloadResult result) {
        userStorage.addTrack(result.track);
        userStorage.addDossier(result.dossier);

        meetingsCalendar.AddMeetings(result.dossier.getMeetings());
        UpdateCalendarDecorations();

        List<Track> tracks = userStorage.getTracks();
        recyclerAdapter.notifyItemChanged(tracks.size() - 1);
        inAppNotifications.show("Traking: " + result.track.getDossieId());
    }

    @Override
    public void TrackTapped(Track track) {
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
        }
    }

    public void onNewDossieFabHandler(View view) {
        showAddTrackDialog();
    }
}

class TracksAdapter extends RecyclerView.Adapter<TrackView> {
    Context context;
    TrackTappedHandler trackHandler;
    IUserStorage IUserStorage;
    AppDateFormatter formatter;

    public TracksAdapter(Context context,
                         IUserStorage IUserStorage,
                         AppDateFormatter formatter,
                         TrackTappedHandler itemHandler) {
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
        Dossier dossie = IUserStorage.getDossier(track.getDossieId());

        List<Meeting> meetings = dossie.getMeetings();
        Meeting meeting = meetings.size() > 0 ? meetings.get(0): null;
        itemViewHolder.Configure(track, dossie, meeting);
    }

    @Override
    public int getItemCount() {
        return IUserStorage.getTracks().size();
    }
}

interface TrackTappedHandler {
    void TrackTapped(Track track);
}

class TrackView extends RecyclerView.ViewHolder implements View.OnClickListener {
    View view;
    TrackTappedHandler clickHandler;
    AppDateFormatter formatter;

    @BindView(R.id.id)
    TextView idView;

    @BindView(R.id.content)
    TextView contentView;

    @BindView(R.id.location)
    TextView location;

    @BindView(R.id.stadiu)
    TextView stadiu;

    @BindView(R.id.dossierDate)
    TextView dossierDate;

    @BindView(R.id.dossierDay)
    TextView dossierDay;


    public TrackView(View view, AppDateFormatter formatter, TrackTappedHandler handler) {
        super(view);
        this.view = view;
        this.formatter = formatter;
        this.clickHandler = handler;

        ButterKnife.bind(this, view);
    }

    public void Configure(Track track, Dossier dossier, Meeting meeting) {
        view.setTag(R.string.trackViewTag, track);
        view.setOnClickListener(this);

        if (meeting != null) {
            idView.setText(dossier.getId());
            contentView.setText(dossier.getObiect());
            location.setText(dossier.getCategory()); // todo: fix
            stadiu.setText(dossier.getState());
            dossierDate.setText(formatter.getDay(meeting.getMeetingTime().inMillis));
            dossierDay.setText(formatter.getDayName(meeting.getMeetingTime().inMillis));
        }
    }

    @Override
    public void onClick(View v) {
        Track track = (Track)v.getTag(R.string.trackViewTag);
        clickHandler.TrackTapped(track);
    }
}

class TrackViewLight implements View.OnClickListener {
    View view;
    TrackTappedHandler clickHandler;
    AppDateFormatter formatter;

    @BindView(R.id.dossierId)
    TextView idView;

    @BindView(R.id.content)
    TextView contentView;

    @BindView(R.id.dossierDate)
    TextView dossierDate;

    public TrackViewLight(View view, AppDateFormatter formatter, TrackTappedHandler handler) {
        this.view = view;
        this.formatter = formatter;
        this.clickHandler = handler;

        ButterKnife.bind(this, view);
    }

    public void Configure(Track track, Meeting meeting) {
        view.setTag(R.string.trackViewTag, track);
        view.setOnClickListener(this);

        idView.setText(meeting.getDossieId());
        contentView.setText(meeting.getId());
        dossierDate.setText(formatter.getDay(meeting.getMeetingTime().inMillis));
    }

    @Override
    public void onClick(View v) {
        Track track = (Track)v.getTag(R.string.trackViewTag);
        clickHandler.TrackTapped(track);
    }
}