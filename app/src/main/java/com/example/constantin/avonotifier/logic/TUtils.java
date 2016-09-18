package com.example.constantin.avonotifier.logic;

class DayKey {
    public MTime mTime;

    public DayKey(MTime meetingTime) {
        this.mTime = meetingTime;
    }

    @Override
    public int hashCode() {
        return 10000 * mTime.year +
               100 * mTime.month +
               mTime.day;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof DayKey)) {
            return false;
        }

        DayKey other = (DayKey)o;
        return mTime.day == other.mTime.day &&
               mTime.month == other.mTime.month &&
               mTime.year == other.mTime.year;
    }
}
