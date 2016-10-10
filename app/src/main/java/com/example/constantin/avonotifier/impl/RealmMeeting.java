package com.example.constantin.avonotifier.impl;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmMeeting extends RealmObject {
    @PrimaryKey
    private String id;

    private String dossierId;
    private long time;
    private String complet;
    private String solution;
    private String summary;

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

    public String getComplet() {
        return complet;
    }

    public String getSolution() {
        return solution;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setComplet(String complet) {
        this.complet = complet;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
