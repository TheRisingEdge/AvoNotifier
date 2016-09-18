package com.example.constantin.avonotifier.impl;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmDossier extends RealmObject {
    @PrimaryKey
    private String dossierId;
    private RealmList<RealmMeeting> meetings;

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
}
