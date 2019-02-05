package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CheckIn {


    private final DayOfWeek day;
    private final LocalTime arrivalTime;

    public CheckIn(DayOfWeek day, LocalTime arrivalTime) {
        this.day = day;
        this.arrivalTime = arrivalTime;
    }


    boolean isInTimeSlot(DayOfWeek day, LocalTime beginSlot) {
        return this.day.equals(day) && arrivalTime.isAfter(beginSlot.minusMinutes(1));
    }

}
