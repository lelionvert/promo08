package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Socrates {
    private final ParticipantRegister participantRegister;
    private final DayOfWeek limitArrivalDayForColdMeal;
    private final LocalTime limitArrivalTimeForColdMeal;
    private final ColdMeals coldMeals;


    public Socrates(ParticipantRegister participantRegister, DayOfWeek limitArrivalDayForColdMeal, LocalTime limitArrivalTimeForColdMeal, ColdMeals coldMeals) {

        this.participantRegister = participantRegister;
        this.limitArrivalDayForColdMeal = limitArrivalDayForColdMeal;
        this.limitArrivalTimeForColdMeal = limitArrivalTimeForColdMeal;
        this.coldMeals = coldMeals;
    }


    public ColdMealListing determineColdMealslisting() {
        return coldMeals.determineColdMealslisting(limitArrivalDayForColdMeal, limitArrivalTimeForColdMeal, participantRegister.getAllParticipant(), limitArrivalDayForColdMeal);
    }

    public MealReportByDiet getMealReport(Meal meal) {
        List<Participant> allParticipant = participantRegister.getAllParticipant();
        ColdMealListing coldMealListing = coldMeals.determineColdMealslisting(allParticipant, meal, limitArrivalDayForColdMeal, limitArrivalTimeForColdMeal);

        List<Participant> allParticipantForMeal = allParticipant
                .stream()
                .filter(participant -> !coldMeals.hasColdMeal(limitArrivalDayForColdMeal, limitArrivalTimeForColdMeal, participant, meal.getDay()))
                .collect(toList());

        HashMap<Diet, Long> coversByDiet = new HashMap<>();
        for (Diet diet : Diet.values()) {
            Long nbParticipantsPerDiet = getNbParticipantsForDiet(allParticipantForMeal, diet);
            if (nbParticipantsPerDiet > 0) {
                coversByDiet.put(diet, nbParticipantsPerDiet);
            }
        }
        return new MealReportByDiet(meal, coversByDiet, coldMealListing.size());
    }

    // TODO Move to participantRegister
    private Long getNbParticipantsForDiet(List<Participant> allParticipant, Diet diet) {
        return allParticipant
                .stream()
                .filter(participant -> participant.hasDiet(diet))
                .count();
    }

    public CountCoversReportByDiet countCoversReportByDiet(List<Meal> meals) {
        return new CountCoversReportByDiet(meals.stream().map(this::getMealReport).collect(toList()));
    }
}
