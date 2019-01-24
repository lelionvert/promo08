package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Registration {
    private final Accomodation accomodationChoice;
    private final CheckIn checkIn;


    public Registration(Accomodation accomodationChoice, CheckIn checkIn) {
        this.accomodationChoice = accomodationChoice;
        this.checkIn = checkIn;
    }


    public Price getTotalprice() {

        int mealPrice = 0;
        if (checkIn.isInTimeSlot(DayOfWeek.THURSDAY, LocalTime.of(21, 00)))
            mealPrice = 40;
        return accomodationChoice.minus(Price.of(mealPrice));
    }


}
