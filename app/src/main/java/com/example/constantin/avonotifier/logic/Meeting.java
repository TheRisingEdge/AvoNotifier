package com.example.constantin.avonotifier.logic;

public class Meeting {
    String id;
    String dossierId;
    String complet;
    String solution;
    String summary;
    Time meetingTime;

    public Meeting(String id,
                   String dossierId,
                   Time time,
                   String complet,
                   String solution,
                   String summary) {
        this.id = id;
        this.dossierId = dossierId;
        this.complet = complet;
        this.solution = solution;
        this.summary = summary;
        this.meetingTime = time;
    }

    public String getDossieId() {
        return dossierId;
    }

    public String getId() {
        return id;
    }

    public Time getMeetingTime() {
        return meetingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Meeting)
        {
            Meeting other = (Meeting)o;
            return id.equals(other.id);
        }

        return  false;
    }

    public String getComplet() {
        return complet;
    }

    public String getDossierId() {
        return dossierId;
    }

    public String getSolution() {
        return solution;
    }

    public String getSummary() {
        return summary;
    }
}
