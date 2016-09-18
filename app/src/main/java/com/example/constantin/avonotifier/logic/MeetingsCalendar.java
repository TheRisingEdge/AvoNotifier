package com.example.constantin.avonotifier.logic;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingsCalendar {
    HashMap<DayKey, Integer> dayEventsCount;
    HashMap<DayKey, List<Meeting>> dayMeetings;
    List<Meeting> NO_MEETINGS;

    public MeetingsCalendar(Collection<Meeting> meetings) {
        this.dayEventsCount = new HashMap<>();
        this.dayMeetings = new HashMap<>();
        this.NO_MEETINGS = new ArrayList<>();

        for (Meeting m: meetings) {
            DayKey day = new DayKey(m.getMeetingTime());
            List<Meeting> meetingsThisDay = dayMeetings.containsKey(day) ? dayMeetings.get(day): new ArrayList<Meeting>();

            meetingsThisDay.add(m);
            dayMeetings.put(day, meetingsThisDay);
            dayEventsCount.put(day, meetingsThisDay.size());
        }
    }

    public List<CalendarDay> daysWith(int countMeetings) {
        List<CalendarDay> bucket = new ArrayList<>();
        boolean collectAll = countMeetings == -1;

        List<Map.Entry<DayKey, Integer>> entries = new ArrayList<>(dayEventsCount.entrySet());
        for (Map.Entry<DayKey, Integer> kv: entries) {
            DayKey dayKey = kv.getKey();
            int meetingsCount = kv.getValue();

            if (collectAll || meetingsCount == countMeetings) {
                MTime mtime = dayKey.mTime;
                bucket.add(CalendarDay.from(mtime.year, mtime.month, mtime.day));
                dayEventsCount.remove(kv.getKey());
            }
        }

        return bucket;
    }

    public List<Meeting> meetingsInDay(MTime mtime) {
        DayKey key = new DayKey(mtime);
        return dayMeetings.containsKey(key) ? dayMeetings.get(key): NO_MEETINGS;
    }
}
