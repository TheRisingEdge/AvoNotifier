package com.example.constantin.avonotifier.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TimeOrderedMeetings implements Comparator<Meeting> {
    List<Meeting> meetings;

    public TimeOrderedMeetings(List<Meeting> meetings) {
        this.meetings = new ArrayList<>(meetings);
        Collections.sort(this.meetings, this);
    }

    @Override
    public int compare(Meeting lhs, Meeting rhs) {
        return (int)(lhs.getTime() - rhs.getTime());
    }

    public List<Meeting> list() {
        return meetings;
    }
}

public class ScheduledMeetings {
    List<Dossie> dossies;

    public ScheduledMeetings(List<Dossie> dossies) {
        this.dossies = dossies;
    }

    public List<Meeting> all() {
        List<Meeting> bucket = new ArrayList<>();
        for (Dossie dossie: dossies) {
            List<Meeting> meetings = dossie.getMeetings();
            bucket.addAll(meetings);
        }

        return bucket;
    }

    public List<Meeting> upcoming(int maxCount, int daysFromNow) {
        List<Meeting> fromAllDossies = all();
        TimeOrderedMeetings allUpcoming = new TimeOrderedMeetings(fromAllDossies);
        List<Meeting> asList = allUpcoming.list();
        return asList.subList(0, Math.min(maxCount, asList.size()));
    }
}
