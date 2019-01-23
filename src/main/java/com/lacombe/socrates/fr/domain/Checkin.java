package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;

public class Checkin {


    private final DayOfWeek day;

    public Checkin(DayOfWeek day) {
        this.day = day;
    }


    public boolean isInTimeSlot(DayOfWeek day) {

        return this.day.equals(day);
    }
}
