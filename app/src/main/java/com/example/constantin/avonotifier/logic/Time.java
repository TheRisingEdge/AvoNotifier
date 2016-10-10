package com.example.constantin.avonotifier.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Time {
    public int year;
    public int month;
    public int day;
    public long inMillis;

    public Time(long inMillis, int year, int month, int day) {
        this.inMillis = inMillis;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Time FromCalendar(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        return new Time(calendar.getTimeInMillis(), year, month, day);
    }

    public static Time FromDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return FromCalendar(c);
    }

    public static Time FromMillis(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return FromCalendar(c);
    }
}

