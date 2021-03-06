package com.example.constantin.avonotifier.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.constantin.avonotifier.Globals;
import com.example.constantin.avonotifier.R;
import com.example.constantin.avonotifier.logic.Dossier;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.AppDateFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {
    public static final String DOSSIE_ID = "dossieId";
    IUserStorage userStorage;
    AppDateFormatter dateFormatter;

    Dossier dossie;

    public DetailsFragment() {
        userStorage = Globals.userStorage;
        dateFormatter = Globals.dateFormatter;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Bundle args = getArguments();
        String dossieId = args.containsKey(DOSSIE_ID) ? args.getString(DOSSIE_ID): "";
        dossie = userStorage.getDossier(dossieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View rootView = inflater.inflate(R.layout.dossie_view, container, false);
        DossieDetailsView dossieView = new DossieDetailsView(rootView, dossie, dateFormatter, inflater);
        return rootView;
    }
}

class DossieDetailsView {
    @BindView(R.id.dossieRegistered)
    TextView registered;

    @BindView(R.id.dossieModified)
    TextView modified;

    @BindView(R.id.dossieTarget)
    TextView target;

    @BindView(R.id.dossieSection)
    TextView section;

    @BindView(R.id.dossieState)
    TextView state;

    @BindView(R.id.dossieMeetings)
    LinearLayout dossieMeetings;

    public DossieDetailsView(View view, Dossier dossie, AppDateFormatter formatter, LayoutInflater inflater)  {
        ButterKnife.bind(this, view);

        registered.setText(formatter.getDay(dossie.getRegistered().inMillis));
        modified.setText(formatter.getDay(dossie.getModified().inMillis));
        target.setText(dossie.getObiect());
        section.setText(dossie.getDepatment());
        state.setText(dossie.getState());

        for (Meeting m: dossie.getMeetings()) {
            addMeeting(m, formatter, inflater);
        }
    }

    void addMeeting(Meeting m, AppDateFormatter formatter, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.meeting_view, dossieMeetings, false);
        MeetingView meetingView = new MeetingView(view, m, formatter);
        dossieMeetings.addView(view);
    }
}

class MeetingView {
    @BindView(R.id.dossierMeetingDate)
    TextView date;

    @BindView(R.id.dossierMeetingDay)
    TextView day;

    @BindView(R.id.dossieMeetingHour)
    TextView hour;

    @BindView(R.id.dossieMeetingDocument)
    TextView document;

    @BindView(R.id.dossieMeetingSolution)
    TextView solution;

    @BindView(R.id.dossieMeetingReview)
    TextView review;

    public MeetingView(View view, Meeting meeting, AppDateFormatter formatter) {
        ButterKnife.bind(this, view);

        date.setText(formatter.getDay(meeting.getMeetingTime().inMillis));
        day.setText(formatter.getDayName(meeting.getMeetingTime().inMillis));
        hour.setText(formatter.getHour(meeting.getMeetingTime().inMillis));
        document.setText(meeting.getComplet()); // todo: fix
        solution.setText(meeting.getSolution());
        review.setText(meeting.getSummary());
    }
}

