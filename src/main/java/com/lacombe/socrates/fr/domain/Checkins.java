package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Checkins {

    public static final LocalTime BEGIN_EVENING = LocalTime.of(21, 00);
    private List<CheckIn> checkIns;

    public Checkins(CheckIn... checkIns) {
        this.checkIns = Arrays.asList(checkIns);
    }


    public long getNumberOfCheckinsFor(DayOfWeek day, LocalTime beginSlotTime) {
        return checkIns.stream().filter(checkIn -> checkIn.isInTimeSlot(day, beginSlotTime)).count();
    }
}
