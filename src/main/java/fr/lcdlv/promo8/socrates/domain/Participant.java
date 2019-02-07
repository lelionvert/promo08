package fr.lcdlv.promo8.socrates.domain;

public class Participant {

    private final RoomChoice roomChoice;
    private final StayPeriod stayPeriod;
    private final String email;
    private MealType mealType;

    public Participant(RoomChoice roomChoice, StayPeriod stayPeriod, String email, MealType mealType) {

        this.roomChoice = roomChoice;
        this.stayPeriod = stayPeriod;
        this.email = email;
        this.mealType = mealType;
    }

    RoomChoice getRoomChoice() {
        return roomChoice;
    }


    StayPeriod getStayPeriod() {
        return stayPeriod;
    }

    String getEmail() {
        return email;
    }

    private boolean arriveAfter(CheckIn checkIn) {
        return stayPeriod.startAfter(checkIn);
    }

    private boolean arriveSameDay(CheckIn checkIn) {
        return stayPeriod.arriveSameDay(checkIn);
    }

    MealType getMealType() {
        return mealType;
    }

    private boolean leaveBefore(CheckOut checkOut) {
        return stayPeriod.leaveBefore(checkOut);
    }

    boolean arriveAfterHotMealLimit(CheckIn checkIn) {
        return arriveAfter(checkIn) && arriveSameDay(checkIn);
    }

    boolean hasNoMealOnThursday(CheckIn checkIn, Meal meal) {
        return meal.isSameDay(checkIn) && !arriveSameDay(checkIn);
    }

    boolean hasNoMealOnSunday(CheckOut checkOut, Meal meal) {
        return meal.isSameDay(checkOut) && leaveBefore(checkOut);
    }

    boolean hasColdMeal(CheckIn checkInHotMealLimit, Meal meal) {
        return meal.isSameDay(checkInHotMealLimit) && arriveAfterHotMealLimit(checkInHotMealLimit);
    }
}