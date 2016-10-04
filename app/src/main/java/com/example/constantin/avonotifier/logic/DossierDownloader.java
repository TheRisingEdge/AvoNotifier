package com.example.constantin.avonotifier.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DossierDownloader {
    public class Result {
        public Track track;
        public Dossie dossier;
        public boolean OK;
    }

    public interface Handler {
        void HandleDossierDownload(Result result);
    }

    Set<Handler> subscribers;

    public DossierDownloader() {
        subscribers = new HashSet<>();
    }

    public void AddHandler(Handler handler) {
        subscribers.add(handler);
    }

    public void Track(Track track) {
        for (Handler h : subscribers) {
            Result result = new Result();
            result.track = track;
            result.OK = true;
            result.dossier = DossieFromTrack(track);

            h.HandleDossierDownload(result);
        }
    }

    public static Dossie DossieFromTrack(Track track) {
        return new Dossie(track.getDossieId(), FakeMeetings(track.getDossieId()));
    }

    public static List<Meeting> FakeMeetings(String dossieId) {
        ArrayList<Meeting> meetings = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 1; i++) {
            // calendar.add(Calendar.DATE, i);
            Time time = Time.FromCalendar(calendar);
            String meetingId = dossieId + "-" + i;
            meetings.add(new Meeting(meetingId, dossieId, time));
        }

        return meetings;
    }
}
