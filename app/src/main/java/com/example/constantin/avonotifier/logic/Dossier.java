package com.example.constantin.avonotifier.logic;

import java.util.List;

public class Dossier {
    String id;
    String obiect;
    String category;
    String depatment;
    String state;
    Time registered;
    Time modified;
    List<Meeting> meetings;

    public Dossier(String id,
                   List<Meeting> meetings,
                   String obiect,
                   Time registered,
                   Time modified,
                   String category,
                   String departament,
                   String stadiu) {
        this.id = id;
        this.meetings = meetings;
        this.obiect = obiect;
        this.registered = registered;
        this.modified = modified;
        this.category = category;
        this.depatment = departament;
        this.state = stadiu;
    }

    public String getId() {
        return id;
    }

    public String getObiect() {
        return obiect;
    }

    public String getCategory() {
        return category;
    }

    public String getDepatment() {
        return depatment;
    }

    public String getState() {
        return state;
    }

    public Time getRegistered() {
        return registered;
    }

    public Time getModified() {
        return modified;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }
}
