package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static com.lacombe.socrates.fr.domain.Diet.VEGAN;
import static com.lacombe.socrates.fr.domain.Diet.VEGETARIAN;
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
        return new ColdMealListing(participantRegister.getAllParticipant().stream()
                .filter(participant -> participant.hasArrivalOnDay(limitArrivalDayForColdMeal))
                .filter(participant -> participant.hasArrivalTimeAfter(limitArrivalTimeForColdMeal))
                .map(Participant::getMail)
                .collect(toList()));
    }

    public CountCoversReport countCoversReport() {
        return new CountCoversReport();
    }

    public CountCoversReport countCoversReportForMeal(Meal meal) {

        List<Participant> participants = participantRegister.getAllParticipant();

        long nbVegeterians = participants.stream().filter(participant -> participant.hasDiet(VEGETARIAN)).count();
        MealCoverReport vegetarianReport = new MealCoverReport(meal, VEGETARIAN, nbVegeterians);

        long nbVegans = participants.stream().filter(participant -> participant.hasDiet(VEGAN)).count();
        MealCoverReport veganReport = new MealCoverReport(meal, VEGAN, nbVegans);

        if (nbVegeterians > 0)
            return new CountCoversReport(vegetarianReport);
        return new CountCoversReport(veganReport);
    }
}
