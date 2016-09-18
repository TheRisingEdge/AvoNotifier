package com.example.constantin.avonotifier.logic;

import java.util.List;

public class Dossie {
    String id;
    List<Meeting> meetings;

    public Dossie(String id, List<Meeting> meetings) {
        this.id = id;
        this.meetings = meetings;
    }

    public String getId() {
        return id;
    }

    public List<Meeting> getMeetings() {
        return  meetings;
    }

    public long getDate() {
        return System.currentTimeMillis();
    }

    public long getRegistered() {
        return System.currentTimeMillis();
    }

    public long getModified() {
        return System.currentTimeMillis();
    }

    public String getTarget() {
        return "target";
    }

    public String getSection() {
        return "Penal";
    }

    public String getState() {
        return "Recurs";
    }
}
