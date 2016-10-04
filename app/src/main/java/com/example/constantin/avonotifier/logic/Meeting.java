package com.example.constantin.avonotifier.logic;

public class Meeting {
    String id;
    String dossierId;
    Time meetingTime;

    public Meeting(String id, String dossierId, Time time) {
        this.id = id;
        this.dossierId = dossierId;
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

    public String getDocument() {
        return "OCJP";
    }

    public String getSolution() {
        return "lorem donor ipsum sir";
    }

    public String getReview() {
        return "lorem ipsum sir donor";
    }
}
