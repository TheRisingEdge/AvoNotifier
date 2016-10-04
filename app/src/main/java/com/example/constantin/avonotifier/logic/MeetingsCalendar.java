package com.example.constantin.avonotifier.logic;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingsCalendar {
    List<Meeting> NO_MEETINGS = new ArrayList<>();
    HashMap<DayKey, List<Meeting>> calendar;

    public MeetingsCalendar(Collection<Meeting> meetings) {
        calendar = new HashMap<>();
        AddMeetings(meetings);
    }

    public void AddMeetings(Collection<Meeting> meetings) {
         for (Meeting meeting: meetings) {
            DayKey day = new DayKey(meeting.getMeetingTime());
            List<Meeting> meetingsThisDay = calendar.containsKey(day) ? calendar.get(day): new ArrayList<Meeting>();

            meetingsThisDay.add(meeting);
            calendar.put(day, meetingsThisDay);
        }
    }

    public List<CalendarDay> daysWith(int minCount, int maxCount) {
        List<CalendarDay> bucket = new ArrayList<>();

        for (Map.Entry<DayKey, List<Meeting>> kv: calendar.entrySet()) {
            DayKey day = kv.getKey();
            int meetingsThisDay = kv.getValue().size();
            if (minCount <= meetingsThisDay && meetingsThisDay < maxCount) {
                Time time = day.time;
                CalendarDay calendarDay = CalendarDay.from(time.year, time.month, time.day);
                bucket.add(calendarDay);
            }
        }

        return bucket;
    }

    public List<Meeting> meetingsInDay(Time time) {
        DayKey day = new DayKey(time);
        return calendar.containsKey(day) ? calendar.get(day): NO_MEETINGS;
    }
}
