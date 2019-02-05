package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class MealBillingCalculator implements MealBillingService {

    private final Price mealPrice;
    public static final int MAX_NUMBER_OF_MEALS = 6;

    public MealBillingCalculator(Price mealPrice) {
        this.mealPrice = mealPrice;
    }

    @Override
    public Price calculatePrice(Participant participant) {
        int missedMeals = 0;
        DayOfWeek thursday = DayOfWeek.THURSDAY;
        LocalTime of = LocalTime.of(20, 59);
        if (participant.periodStartAfter(thursday, of))
            missedMeals += 1;
        if (participant.periodEndsBefore(DayOfWeek.SUNDAY, LocalTime.of(11, 00)))
            missedMeals += 1;
        return mealPrice.multiplyBy(MAX_NUMBER_OF_MEALS - missedMeals);
    }

}
