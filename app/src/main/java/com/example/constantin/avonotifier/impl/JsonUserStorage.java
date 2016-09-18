//package com.example.constantin.avonotifier.impl;
//
//import com.example.constantin.avonotifier.logic.Dossie;
//import com.example.constantin.avonotifier.logic.IUserStorage;
//import com.example.constantin.avonotifier.logic.Meeting;
//import com.example.constantin.avonotifier.logic.Track;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Random;
//
//public class JsonUserStorage implements IUserStorage {
//    List<Track> tracks;
//    List<Dossie> dossiers;
//
//    public JsonUserStorage(List<Track> tracks, List<Dossie> dossies) {
//        this.tracks = tracks;
//        this.dossiers = dossies;
//    }
//
//    public static IUserStorage FromStorage(StoragePOJO storage) {
//        List<Dossie> dossiers = new ArrayList<>(storage.dossiers.size());
//        List<Track> tracks = new ArrayList<>(storage.dossiers.size());
//
//        for (DossierPOJO dp : storage.dossiers) {
//            Dossie d = new Dossie(dp.id, FakeMeetings(dp.id));
//            dossiers.add(d);
//
//            Track track = new Track(dp.id);
//            tracks.add(track);
//        }
//
//        return new JsonUserStorage(tracks, dossiers);
//    }
//
//    public static StoragePOJO ToStorage(IUserStorage IUserStorage) {
//        StoragePOJO storage = new StoragePOJO();
//        for (Dossie dossie : IUserStorage.getDossies()) {
//            DossierPOJO dpojo = new DossierPOJO();
//            dpojo.id = dossie.getId();
//
//            for (Meeting meeting : dossie.getMeetings()) {
//                MeetingPOJO mpojo = new MeetingPOJO();
//                mpojo.id = meeting.getId();
//
//                dpojo.meetings.add(mpojo);
//            }
//
//            storage.dossiers.add(dpojo);
//        }
//
//        return storage;
//    }
//
//    public static IUserStorage Mock() {
//        ArrayList<Track> tracks = new ArrayList<>(50);
//        ArrayList<Dossie> dossies = new ArrayList<>(50);
//
//        Random r = new Random();
//
//        for (int i = 0; i < 50; i++) {
//            int i1 = r.nextInt(1000);
//            int i2 = r.nextInt(1000);
//            int i3 = r.nextInt(1000);
//
//            String did = i1 + "/" + i2 + "/" + i3;
//            tracks.add(new Track(did));
//            dossies.add(FakeDossie(did));
//        }
//
//        return new JsonUserStorage(tracks, dossies);
//    }
//
//    public static Dossie FakeDossie(String dossieId) {
//        return new Dossie(dossieId, FakeMeetings(dossieId));
//    }
//
//    public static Dossie DossieFromTrack(Track track) {
//        return new Dossie(track.getDossieId(), FakeMeetings(track.getDossieId()));
//    }
//
//    public static Meeting[] FakeMeetings(String dossieId) {
//        ArrayList<Meeting> meetings = new ArrayList<>(20);
//        Calendar calendar = Calendar.getInstance();
//        Random random = new Random();
//
//        for (int i = 0; i < 20; i++) {
//            calendar.add(Calendar.HOUR_OF_DAY, i * 5 * random.nextInt(10));
//            meetings.add(new Meeting(dossieId, dossieId, calendar.getTimeInMillis()));
//        }
//
//        return meetings.toArray(new Meeting[meetings.size()]);
//    }
//
//    @Override
//    public void addTrack(Track track) {
//        tracks.add(track);
//    }
//
//    @Override
//    public List<Track> getTracks() {
//        return tracks;
//    }
//
//    @Override
//    public void addDossier(Dossie dossie) {
//        dossiers.add(dossie);
//    }
//
//    @Override
//    public Dossie getDossie(String dossieId) {
//        return FakeDossie(dossieId);
//    }
//
//    @Override
//    public List<Dossie> getDossies() {
//        return dossiers;
//    }
//}

