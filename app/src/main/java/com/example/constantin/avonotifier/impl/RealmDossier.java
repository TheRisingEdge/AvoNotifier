package com.example.constantin.avonotifier.impl;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmDossier extends RealmObject {
    @PrimaryKey
    private String dossierId;
    private RealmList<RealmMeeting> meetings;
    private String category;
    private String state;
    private String deparment;
    private String obiect;
    private long registered;
    private long modified;

    public RealmDossier() {
        meetings = new RealmList<>();
    }

    public RealmList<RealmMeeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(RealmList<RealmMeeting> meetings) {
        this.meetings = meetings;
    }

    public String getDossierId() {
        return dossierId;
    }

    public void setDossierId(String dossierId) {
        this.dossierId = dossierId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public void setRegistered(long registered) {
        this.registered = registered;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public void setObiect(String obiect) {
        this.obiect = obiect;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getDeparment() {
        return deparment;
    }

    public String getObiect() {
        return obiect;
    }

    public long getRegistered() {
        return registered;
    }

    public long getModified() {
        return modified;
    }
}
