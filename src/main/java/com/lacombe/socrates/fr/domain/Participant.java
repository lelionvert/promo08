package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Participant {
    private final RoomChoice roomChoice;
    private final StayPeriod stayPeriod;

    public Participant(RoomChoice roomChoice, StayPeriod stayPeriod) {
        this.roomChoice = roomChoice;
        this.stayPeriod = stayPeriod;
    }

    public RoomChoice getRoomChoice() {
        return roomChoice;
    }

    boolean periodStartAfter(DayOfWeek anotherDay, LocalTime of) {
        return stayPeriod.checkedInAfter(anotherDay, of);
    }

    public boolean periodEndsBefore(DayOfWeek anotherDay, LocalTime anotherTime) {
        return stayPeriod.checkedOutBefore(anotherDay, anotherTime);
    }
}
