package com.example.constantin.avonotifier.logic;

import java.util.Calendar;

public class Meeting {
    String id;
    String dossieId;
    MTime meetingTime;
    long time;

    public Meeting(String id, String dossieId, long time) {
        this.id = id;
        this.dossieId = dossieId;
        this.time = time;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        this.meetingTime = new MTime(year, month, day);
    }

    public String getDossieId() {
        return dossieId;
    }

    public String getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public MTime getMeetingTime() {
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
