package com.example.constantin.avonotifier.impl;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;

import com.example.constantin.avonotifier.logic.Event;
import com.example.constantin.avonotifier.logic.ICalendarScheduler;

import java.util.Calendar;

public class GoogleCalenderScheduler implements ICalendarScheduler {
    Context context;

    public GoogleCalenderScheduler(Context context) {
        this.context = context;
    }

    @Override
    public void open() {
        Calendar now = Calendar.getInstance();
        long milis = now.getTimeInMillis();

        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");

        ContentUris.appendId(builder, milis);
        Uri uri = builder.build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @Override
    public void schedule() {
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, "My House Party");
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Beach House");
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "A Pig Roast on the Beach");
        context.startActivity(calIntent);
    }

    @Override
    public void schedule(Event event) {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = TranslateEvent(event);
    }

    ContentValues TranslateEvent(Event event) {
        int yearInt = 0;
        int monthInt = 0;
        int dayInt = 0;
        int calendarId = 0;

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(yearInt, monthInt - 1, dayInt, 7, 30);

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID,  calendarId);
        values.put(CalendarContract.Events.TITLE, event.getTitle());
        values.put(CalendarContract.Events.DESCRIPTION, event.getDescription());
        values.put(CalendarContract.Events.EVENT_LOCATION, event.getLocation());
        values.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
        values.put(CalendarContract.Events.DTEND, beginTime.getTimeInMillis());
        values.put(CalendarContract.Events.ALL_DAY, 0);
        values.put(CalendarContract.Events.RRULE, "FREQ=YEARLY");
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "India");
        // status: 0~ tentative; 1~ confirmed; 2~ canceled
        // l_event.put("eventStatus", 1);
        return  values;
    }
}
