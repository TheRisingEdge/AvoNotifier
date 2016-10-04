package com.example.constantin.avonotifier.logic;

class DayKey {
    public Time time;

    public DayKey(Time meetingTime) {
        this.time = meetingTime;
    }

    @Override
    public int hashCode() {
        return 10000 * time.year +
               100 * time.month +
               time.day;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof DayKey)) {
            return false;
        }

        DayKey other = (DayKey)o;
        return time.day == other.time.day &&
               time.month == other.time.month &&
               time.year == other.time.year;
    }
}
