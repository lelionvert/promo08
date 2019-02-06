package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static java.util.stream.Collectors.toList;

public class ColdMealProvider {
    private final ParticipantRegister participantRegister;
    private final DayOfWeek limitArrivalDayForColdMeal;
    private final LocalTime limitArrivalTimeForColdMeal;


    public ColdMealProvider(ParticipantRegister participantRegister, DayOfWeek limitArrivalDayForColdMeal, LocalTime limitArrivalTimeForColdMeal) {

        this.participantRegister = participantRegister;
        this.limitArrivalDayForColdMeal = limitArrivalDayForColdMeal;
        this.limitArrivalTimeForColdMeal = limitArrivalTimeForColdMeal;
    }


    public ColdMealListing determineColdMealslisting() {
        return new ColdMealListing(participantRegister.getAllParticipant().stream()
                .filter(participant -> participant.hasArrivalOnDay(limitArrivalDayForColdMeal) && participant.hasArrivalTimeAfter(limitArrivalTimeForColdMeal))
                .map(Participant::getMail).collect(toList()));
    }

}
