package com.example.constantin.avonotifier;

import android.app.Application;

import com.example.constantin.avonotifier.impl.RealmUserStorage;
import com.example.constantin.avonotifier.logic.DossieFetcher;
import com.example.constantin.avonotifier.logic.AppDateFormatter;

import java.util.Locale;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Globals.dossieTracker = new DossieFetcher();
        Globals.dateFormatter = new AppDateFormatter(new Locale("ro"));
        Globals.userStorage = new RealmUserStorage(getApplicationContext());
    }

    public void onSuspend() {
    }
}
