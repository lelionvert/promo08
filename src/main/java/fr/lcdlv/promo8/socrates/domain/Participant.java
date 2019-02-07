package fr.lcdlv.promo8.socrates.domain;

public class Participant {

    private final RoomChoice roomChoice;
    private final StayPeriod stayPeriod;
    private final String email;
    private Diet diet;

    public Participant(RoomChoice roomChoice, StayPeriod stayPeriod, String email, Diet diet) {

        this.roomChoice = roomChoice;
        this.stayPeriod = stayPeriod;
        this.email = email;
        this.diet = diet;
    }

    RoomChoice getRoomChoice() {
        return roomChoice;
    }


    StayPeriod getStayPeriod() {
        return stayPeriod;
    }

    public String getEmail() {
        return email;
    }

    public boolean arriveAfter(CheckIn checkInLimit) {
        return stayPeriod.startAfter(checkInLimit);
    }

    public boolean arriveSameDay(CheckIn checkIn) {
        return stayPeriod.arriveSameDay(checkIn);
    }

    public Diet getDiet() {
        return diet;
    }
}