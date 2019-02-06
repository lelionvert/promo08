package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Socrates {
    private final DayOfWeek limitDay;
    private final LocalTime limitTime;

    public Socrates(DayOfWeek limitDay, LocalTime limitTime) {
        this.limitDay = limitDay;
        this.limitTime = limitTime;
    }

    DayOfWeek getLimitDay() {
        return limitDay;
    }

    LocalTime getLimitTime() {
        return limitTime;
    }
}
