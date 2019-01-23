package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class CheckinRepository {

    private List<CheckIn> checkIns;

    public CheckinRepository(CheckIn... checkIns) {
        this.checkIns = Arrays.asList(checkIns);
    }

    public int getColdMeals() {
        return (int) checkIns.stream().filter(checkIn -> checkIn.isInTimeSlot(DayOfWeek.THURSDAY, LocalTime.of(21, 00))).count();
    }
}
