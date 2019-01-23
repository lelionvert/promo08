package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class CheckinRepository {

    private List<Checkin> checkins;

    public CheckinRepository(Checkin... checkins) {
        this.checkins = Arrays.asList(checkins);
    }

    public int getColdMeals() {
        return (int) checkins.stream().filter(checkin -> checkin.isInTimeSlot(DayOfWeek.THURSDAY)).count();
    }
}
