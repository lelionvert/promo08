package com.lacombe.socrates.fr;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Checkout {
    private final DayOfWeek day;
    private final LocalTime time;

    public Checkout(DayOfWeek day, LocalTime time) {
        this.day = day;
        this.time = time;
    }

    public boolean isBefore(DayOfWeek anotherDay, LocalTime anotherTime) {
        if (day.equals(anotherDay))
            return time.isBefore(anotherTime);
        return day.compareTo(anotherDay) < 0;
    }
}
