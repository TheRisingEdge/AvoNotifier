package com.example.constantin.avonotifier;

import com.example.constantin.avonotifier.logic.DossieFetcher;
import com.example.constantin.avonotifier.logic.AppDateFormatter;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.impl.JsonStorage;

public class Globals {
    public static JsonStorage jsonStorage;
    public static DossieFetcher dossieTracker;
    public static IUserStorage userStorage;
    public static AppDateFormatter dateFormatter;
}
