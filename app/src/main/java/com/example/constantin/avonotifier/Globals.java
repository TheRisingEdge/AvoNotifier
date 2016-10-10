package com.example.constantin.avonotifier;

import com.example.constantin.avonotifier.logic.AppDateFormatter;
import com.example.constantin.avonotifier.logic.IDossierDownloader;
import com.example.constantin.avonotifier.logic.IUserStorage;
import com.example.constantin.avonotifier.logic.MeetingsCalendar;

public class Globals {
    public static IDossierDownloader dossierDownloader;
    public static IUserStorage userStorage;
    public static AppDateFormatter dateFormatter;
    public static MeetingsCalendar meetingsCalendar;
}
