package fr.lcdlv.promo8.socrates.domain;

import fr.lcdlv.promo8.socrates.domain.Billing.Meal;
import fr.lcdlv.promo8.socrates.domain.Billing.MealType;

public class Participant {

    private final RoomChoice roomChoice;
    private final StayPeriod stayPeriod;
    private final String email;
    private final MealType mealType;

    Participant(RoomChoice roomChoice, StayPeriod stayPeriod, String email, MealType mealType) {

        this.roomChoice = roomChoice;
        this.stayPeriod = stayPeriod;
        this.email = email;
        this.mealType = mealType;
    }

    public RoomChoice getRoomChoice() {
        return roomChoice;
    }

    public StayPeriod getStayPeriod() {
        return stayPeriod;
    }

    String getEmail() {
        return email;
    }

    MealType getMealType() {
        return mealType;
    }

    private boolean arriveAfter(CheckIn checkIn) {
        return stayPeriod.startAfter(checkIn);
    }

    private boolean arriveSameDay(CheckIn checkIn) {
        return stayPeriod.arriveSameDay(checkIn);
    }

    private boolean leaveBefore(CheckOut checkOut) {
        return stayPeriod.leaveBefore(checkOut);
    }

    boolean arriveAfterHotMealLimit(CheckIn checkIn) {
        return arriveAfter(checkIn) && arriveSameDay(checkIn);
    }

    boolean hasMealOnThursday(CheckIn checkIn, Meal meal) {
        return !(meal.isSameDay(checkIn) && !arriveSameDay(checkIn));
    }

    boolean hasMealOnSunday(CheckOut checkOut, Meal meal) {
        return !(meal.isSameDay(checkOut) && leaveBefore(checkOut));
    }

    boolean hasColdMeal(CheckIn checkInHotMealLimit, Meal meal) {
        return meal.isSameDay(checkInHotMealLimit) && arriveAfterHotMealLimit(checkInHotMealLimit);
    }
}