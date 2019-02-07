package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Socrates {
    private final ParticipantRegister participantRegister;
    private final DayOfWeek limitArrivalDayForColdMeal;
    private final LocalTime limitArrivalTimeForColdMeal;


    public Socrates(ParticipantRegister participantRegister, DayOfWeek limitArrivalDayForColdMeal, LocalTime limitArrivalTimeForColdMeal) {

        this.participantRegister = participantRegister;
        this.limitArrivalDayForColdMeal = limitArrivalDayForColdMeal;
        this.limitArrivalTimeForColdMeal = limitArrivalTimeForColdMeal;
    }


    public ColdMealListing determineColdMealslisting() {
        List<Participant> participants = participantRegister.getAllParticipant();
        return determineColdMealslisting(participants);
    }

    private ColdMealListing determineColdMealslisting(List<Participant> participants) {
        return new ColdMealListing(participants.stream()
                .filter(participant -> hasColdMeal(participant, limitArrivalDayForColdMeal))
                .map(Participant::getMail)
                .collect(toList()));
    }

    private boolean hasColdMeal(Participant participant, DayOfWeek day) {
        return day == limitArrivalDayForColdMeal
                && participant.hasArrivalOnDay(limitArrivalDayForColdMeal)
                && participant.hasArrivalTimeAfter(limitArrivalTimeForColdMeal);
    }

    private ColdMealListing determineColdMealslisting(List<Participant> participants, Meal meal) {

        if (meal.isDay(limitArrivalDayForColdMeal))
            return new ColdMealListing(participants.stream()
                    .filter(participant -> participant.hasArrivalOnDay(limitArrivalDayForColdMeal))
                    .filter(participant -> participant.hasArrivalTimeAfter(limitArrivalTimeForColdMeal))
                    .map(Participant::getMail)
                    .collect(toList()));

        return new ColdMealListing(new ArrayList<>());
    }

    public MealReportByDiet getMealReport(Meal meal) {
        List<Participant> allParticipant = participantRegister.getAllParticipant();
        ColdMealListing coldMealListing = determineColdMealslisting(allParticipant, meal);

        List<Participant> allParticipantForMeal = allParticipant
                .stream()
                .filter(participant -> !hasColdMeal(participant, meal.getDay()))
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
