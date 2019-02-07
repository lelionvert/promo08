package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ColdMeals {
    public boolean hasColdMeal(DayOfWeek limitArrivalDayForColdMeal, LocalTime limitArrivalTimeForColdMeal, Participant participant, DayOfWeek day) {
        return day == limitArrivalDayForColdMeal
                && participant.hasArrivalOnDay(limitArrivalDayForColdMeal)
                && participant.hasArrivalTimeAfter(limitArrivalTimeForColdMeal);
    }

    ColdMealListing determineColdMealslisting(DayOfWeek limitArrivalDayForColdMeal, LocalTime limitArrivalTimeForColdMeal, List<Participant> participants, DayOfWeek day) {
        return new ColdMealListing(participants.stream()
                .filter(participant -> hasColdMeal(limitArrivalDayForColdMeal, limitArrivalTimeForColdMeal, participant, day))
                .map(Participant::getMail)
                .collect(toList()));
    }

    ColdMealListing determineColdMealslisting(List<Participant> participants, Meal meal, DayOfWeek limitArrivalDayForColdMeal, LocalTime limitArrivalTimeForColdMeal) {
        if (meal.isDay(limitArrivalDayForColdMeal))
            return determineColdMealslisting(limitArrivalDayForColdMeal, limitArrivalTimeForColdMeal, participants, meal.getDay());
        return new ColdMealListing(new ArrayList<>());
    }
}
