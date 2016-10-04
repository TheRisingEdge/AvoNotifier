package com.example.constantin.avonotifier;

import android.app.Application;

import com.example.constantin.avonotifier.impl.RealmUserStorage;
import com.example.constantin.avonotifier.logic.AllMeetings;
import com.example.constantin.avonotifier.logic.DossierDownloader;
import com.example.constantin.avonotifier.logic.AppDateFormatter;
import com.example.constantin.avonotifier.logic.MeetingsCalendar;

import java.util.Locale;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Globals.dossieTracker = new DossierDownloader();
        Globals.dateFormatter = new AppDateFormatter(new Locale("ro"));
        Globals.userStorage = new RealmUserStorage(getApplicationContext());

        AllMeetings allMeetings = new AllMeetings(Globals.userStorage.getDossies());
        Globals.meetingsCalendar = new MeetingsCalendar(allMeetings.all());
    }

    public void onSuspend() {
    }
}
