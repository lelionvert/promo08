package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class CheckIns {

    private List<CheckIn> checkIns;

    public CheckIns(CheckIn... checkIns) {
        this.checkIns = Arrays.asList(checkIns);
    }


    public long getNumberOfCheckInsFor(DayOfWeek day, LocalTime beginSlotTime) {
        return checkIns.stream().filter(checkIn -> checkIn.isInTimeSlot(day, beginSlotTime)).count();
    }
}
