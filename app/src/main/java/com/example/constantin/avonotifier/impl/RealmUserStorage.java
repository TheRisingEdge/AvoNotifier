package com.example.constantin.avonotifier.impl;

import android.content.Context;

import com.example.constantin.avonotifier.logic.Dossier;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.Time;
import com.example.constantin.avonotifier.logic.Track;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RealmUserStorage implements IUserStorage {
    Realm realm;
    Calendar calendar;

    public RealmUserStorage(Context context) {
        RealmConfiguration config = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
        calendar = Calendar.getInstance();
    }

    @Override
    public void addTrack(Track track) {
        realm.beginTransaction();
        RealmTrack rt = new RealmTrack();
        rt.setDossierId(track.getDossieId());
        realm.insert(rt);
        realm.commitTransaction();
    }

    @Override
    public void addDossier(Dossier dossier) {
        realm.beginTransaction();
        RealmDossier rd = Map(dossier);
        realm.insertOrUpdate(rd);
        realm.commitTransaction();
    }

    @Override
    public List<Track> getTracks() {
        List<Track> bucket = new ArrayList<>();

        RealmResults<RealmTrack> tracks = realm.where(RealmTrack.class).findAll();
        for (RealmTrack rt: tracks) {
            Track t = new Track(rt.getDossierId());
            bucket.add(t);
        }

        return bucket;
    }

    @Override
    public List<Dossier> getDossiers() {
        List<Dossier> bucket = new ArrayList<>();
        RealmResults<RealmDossier> rds = realm.where(RealmDossier.class).findAll();
        for (RealmDossier rd: rds) {
            bucket.add(Map(rd));
        }

        return bucket;
    }

    @Override
    public Dossier getDossier(String dossierId) {
        RealmDossier found = realm.where(RealmDossier.class).equalTo("dossierId", dossierId).findFirst();
        return Map(found);
    }

    Dossier Map(RealmDossier rd) {
        List<Meeting> meetings = new ArrayList<>();
        for (RealmMeeting rm : rd.getMeetings()) {
            meetings.add(Map(rm));
        }

        String obiect = rd.getObiect();
        Time registered = Time.FromMillis(rd.getRegistered());
        Time modified = Time.FromMillis(rd.getModified());
        String category = rd.getCategory();
        String department = rd.getDeparment();
        String stadiu = rd.getState();

        return new Dossier(rd.getDossierId(), meetings, obiect, registered, modified, category, department, stadiu);
    }

    Meeting Map(RealmMeeting rm) {
        calendar.clear();
        calendar.setTimeInMillis(rm.getTime());
        return new Meeting(rm.getId(),
                           rm.getDossierId(),
                           Time.FromCalendar(calendar),
                           rm.getComplet(),
                           rm.getSolution(),
                           rm.getSummary());
    }

    RealmDossier Map(Dossier dossie) {
        RealmDossier rd = new RealmDossier();
        rd.setDossierId(dossie.getId());
        rd.setCategory(dossie.getCategory());
        rd.setDeparment(dossie.getDepatment());
        rd.setRegistered(dossie.getRegistered().inMillis);
        rd.setModified(dossie.getModified().inMillis);
        rd.setObiect(dossie.getObiect());
        rd.setState(dossie.getState());

        for(Meeting m: dossie.getMeetings()) {
            rd.getMeetings().add(Map(m));
        }

        return rd;
    }

    RealmMeeting Map(Meeting m) {
        RealmMeeting rm = new RealmMeeting();
        rm.setId(m.getId());
        rm.setDossierId(m.getDossieId());
        rm.setTime(m.getMeetingTime().inMillis);
        rm.setComplet(m.getComplet());
        rm.setSolution(m.getSolution());
        rm.setSummary(m.getSummary());
        return rm;
    }
}
