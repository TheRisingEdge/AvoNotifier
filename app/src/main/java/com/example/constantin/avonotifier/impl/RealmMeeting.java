package com.example.constantin.avonotifier.impl;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmMeeting extends RealmObject {
    @PrimaryKey
    private String id;

    private String dossierId;
    private long time;

    public RealmMeeting() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDossierId() {
        return dossierId;
    }

    public void setDossierId(String dossierId) {
        this.dossierId = dossierId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
