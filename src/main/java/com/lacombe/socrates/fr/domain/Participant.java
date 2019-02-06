package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Participant {
    private final RoomChoice roomChoice;
    private final StayPeriod stayPeriod;
    private final Mail mail;
    private final Diet diet;

    public Participant(RoomChoice roomChoice, StayPeriod stayPeriod, Diet diet) {
        this.roomChoice = roomChoice;
        this.stayPeriod = stayPeriod;
        this.diet = diet;
        this.mail = null;
    }

    public Participant(RoomChoice roomChoice, StayPeriod stayPeriod, Mail mail, Diet diet) {
        this.roomChoice = roomChoice;
        this.stayPeriod = stayPeriod;
        this.mail = mail;
        this.diet = diet;
    }


    public RoomChoice getRoomChoice() {
        return roomChoice;
    }

    boolean periodStartAfter(DayOfWeek anotherDay, LocalTime of) {
        return stayPeriod.checkedInAfter(anotherDay, of);
    }

    public boolean periodEndsBefore(DayOfWeek anotherDay, LocalTime anotherTime) {
        return stayPeriod.checkedOutBefore(anotherDay, anotherTime);
    }

    public Mail getMail() {
        return mail;
    }


    public boolean hasArrivalOnDay(DayOfWeek day) {
        return stayPeriod.checkedInDay(day);
    }

    public boolean hasArrivalTimeAfter(LocalTime anotherTime) {
        return stayPeriod.checkedInTimeAfter(anotherTime);
    }

    public boolean hasDiet(Diet otherDiet) {
        return diet.equals(otherDiet);
    }
}
