package com.example.constantin.avonotifier.impl;

import android.content.Context;

import com.example.constantin.avonotifier.logic.Dossie;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.logic.Meeting;
import com.example.constantin.avonotifier.logic.Track;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

public class RealmUserStorage implements IUserStorage {
    Realm realm;

    public RealmUserStorage(Context context) {
        RealmConfiguration config = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
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
    public void addDossier(Dossie dossie) {
        realm.beginTransaction();
        RealmDossier rd = Map(dossie);
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
    public List<Dossie> getDossies() {
        List<Dossie> bucket = new ArrayList<>();
        RealmResults<RealmDossier> rds = realm.where(RealmDossier.class).findAll();
        for (RealmDossier rd: rds) {
            bucket.add(Map(rd));
        }

        return bucket;
    }

    @Override
    public Dossie getDossie(String dossieId) {
        RealmDossier found = realm.where(RealmDossier.class).equalTo("dossierId", dossieId).findFirst();
        Dossie dossier = Map(found);
        return  dossier;
    }

    Dossie Map(RealmDossier rd) {
        List<Meeting> meetings = new ArrayList<>();
        for (RealmMeeting rm : rd.getMeetings()) {
            meetings.add(Map(rm));
        }

        return new Dossie(rd.getDossierId(), meetings);
    }

    Meeting Map(RealmMeeting rm) {
        return new Meeting(rm.getId(), rm.getDossierId(), rm.getTime());
    }

    RealmDossier Map(Dossie dossie) {
        RealmDossier rd = new RealmDossier();
        rd.setDossierId(dossie.getId());


        for(Meeting m: dossie.getMeetings()) {
            rd.getMeetings().add(Map(m));
        }

        return rd;
    }

    RealmMeeting Map(Meeting m) {
        RealmMeeting rm = new RealmMeeting();
        rm.setDossierId(m.getDossieId());
        rm.setTime(m.getTime());
        return rm;
    }
}
