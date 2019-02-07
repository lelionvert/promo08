package com.lacombe.socrates.fr;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Checkin {
    private final DayOfWeek day;
    private final LocalTime time;

    public Checkin(DayOfWeek day, LocalTime of) {
        this.day = day;
        time = of;
    }

    public boolean isAfter(DayOfWeek anotherDay, LocalTime of) {
        if (isOnDay(anotherDay)) {
            return isAfterTime(of);
        }
        return (day.compareTo(anotherDay) > 0);
    }

    public boolean isOnDay(DayOfWeek anotherDay) {
        return day.equals(anotherDay);
    }

    public boolean isAfterTime(LocalTime anotherTime) {
        return time.equals(anotherTime) || time.isAfter(anotherTime);
    }
}
