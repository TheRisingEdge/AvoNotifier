package com.example.constantin.avonotifier.logic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppDateFormatter {
    SimpleDateFormat dateFormatter;
    SimpleDateFormat hourFormatter;

    SimpleDateFormat dayNameFormatter;
    SimpleDateFormat monthNameFormatter;
    SimpleDateFormat dayNumberFormatter;

    public AppDateFormatter(Locale locale) {
        dateFormatter = new SimpleDateFormat("d MMM EEEE", locale);
        hourFormatter = new SimpleDateFormat("hh:mm", locale);

        dayNameFormatter = new SimpleDateFormat("EEEE", locale);
        monthNameFormatter = new SimpleDateFormat("MMMM", locale);
        dayNumberFormatter = new SimpleDateFormat("d", locale);
    }

    public String getHour(long millis) {
        Date date = new Date();
        date.setTime(millis);
        return hourFormatter.format(date);
    }

    public String getDay(long millis) {
        Date date = new Date();
        date.setTime(millis);
        return dateFormatter.format(date);
    }

    public String getDayName(long millis) {
        Date date = new Date();
        date.setTime(millis);
        return dayNameFormatter.format(date);
    }

    public String getDayNumber(long millis) {
        Date date = new Date();
        date.setTime(millis);
        return dayNumberFormatter.format(date);
    }

    public String getMonthName(long millis) {
        Date date = new Date();
        date.setTime(millis);
        return monthNameFormatter.format(date);
    }
}
