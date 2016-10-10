package com.example.constantin.avonotifier.impl;

import com.example.constantin.avonotifier.logic.Dossier;
import com.example.constantin.avonotifier.logic.DownloaderHandler;
import com.example.constantin.avonotifier.logic.IDossierDownloader;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.Time;
import com.example.constantin.avonotifier.logic.Track;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DossierDownloader implements IDossierDownloader {

    Set<DownloaderHandler> subscribers;

    public DossierDownloader() {
        subscribers = new HashSet<>();
    }

    @Override
    public void subscribe(DownloaderHandler handler) {
        subscribers.add(handler);
    }

    @Override
    public void unsubscribe(DownloaderHandler handler) {
        subscribers.remove(handler);
    }

    @Override
    public void download(Track track) {
        for (DownloaderHandler h : subscribers) {
            DownloaderHandler.DownloadResult result = new DownloaderHandler.DownloadResult();
            result.track = track;
            result.OK = true;
            result.dossier = DossieFromTrack(track);

            h.HandleDossierDownload(result);
        }
    }

    static Dossier DossieFromTrack(Track track) {
        return null;
    }

    static List<Meeting> FakeMeetings(String dossieId) {
        ArrayList<Meeting> meetings = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 2; i++) {
            calendar.add(Calendar.DATE, i);
            Time time = Time.FromCalendar(calendar);
            String meetingId = dossieId + "-" + i;
            meetings.add(new Meeting(meetingId, dossieId, time, "", "", ""));
        }

        return meetings;
    }
}
