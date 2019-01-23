package com.lacombe.socrates.fr.domain;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.THURSDAY;

public class CheckinRepository {

    public static final LocalTime BEGIN_EVENING = LocalTime.of(21, 00);
    private List<CheckIn> checkIns;

    public CheckinRepository(CheckIn... checkIns) {
        this.checkIns = Arrays.asList(checkIns);
    }


    public int getColdMeals() {
        return (int) checkIns.stream().filter(checkIn -> checkIn.isInTimeSlot(THURSDAY, BEGIN_EVENING)).count();
    }
}
