package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static com.lacombe.socrates.fr.domain.CookService.DINNER;
import static com.lacombe.socrates.fr.domain.CookService.LUNCH;
import static com.lacombe.socrates.fr.domain.Diet.VEGAN;
import static com.lacombe.socrates.fr.domain.Diet.VEGETARIAN;
import static java.time.DayOfWeek.*;
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

        List<Participant> participants = participantRegister.getAllParticipant();

        long nbVegeterians = participants.stream().filter(participant -> participant.hasDiet(VEGETARIAN)).count();
        if (nbVegeterians > 0) {
            Diet diet = VEGETARIAN;
            return new CountCoversReport(getMealCoverReport(nbVegeterians, new Meal(THURSDAY, DINNER), diet),
                    getMealCoverReport(nbVegeterians, new Meal(FRIDAY, LUNCH), diet),
                    getMealCoverReport(nbVegeterians, new Meal(FRIDAY, DINNER), diet),
                    getMealCoverReport(nbVegeterians, new Meal(SATURDAY, LUNCH), diet),
                    getMealCoverReport(nbVegeterians, new Meal(SATURDAY, DINNER), diet),
                    getMealCoverReport(nbVegeterians, new Meal(SUNDAY, LUNCH), diet));
        }

        else return new CountCoversReport();
    }

    private MealCoverReport getMealCoverReport(long nbVegeterians, Meal meal, Diet diet) {
        MealCoverReport mealCoverReport = new MealCoverReport(meal, diet, nbVegeterians);
        return mealCoverReport;
    }

    public CountCoversReport countCoversReportForMeal(Meal meal) {

        List<Participant> participants = participantRegister.getAllParticipant();

        long nbVegeterians = participants.stream().filter(participant -> participant.hasDiet(VEGETARIAN)).count();
        MealCoverReport vegetarianReport = getMealCoverReport(nbVegeterians, meal, VEGETARIAN);

        long nbVegans = participants.stream().filter(participant -> participant.hasDiet(VEGAN)).count();
        MealCoverReport veganReport = getMealCoverReport(nbVegans, meal, VEGAN);

        if (nbVegeterians > 0)
            return new CountCoversReport(vegetarianReport);
        return new CountCoversReport(veganReport);
    }
}
