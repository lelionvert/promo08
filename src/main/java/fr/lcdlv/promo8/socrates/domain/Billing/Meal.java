package fr.lcdlv.promo8.socrates.domain.Billing;

import fr.lcdlv.promo8.socrates.domain.CheckIn;
import fr.lcdlv.promo8.socrates.domain.CheckOut;
import fr.lcdlv.promo8.socrates.domain.SocratesDay;

import java.util.Objects;

public class Meal {
    private final SocratesDay day;
    private final MealTime mealTime;

    public Meal(SocratesDay day, MealTime mealTime) {
        this.day = day;
        this.mealTime = mealTime;
    }

    public boolean isSameDay(CheckIn checkIn) {
        return checkIn.isSameDay(day);
    }

    public boolean isSameDay(CheckOut checkOut) {
        return checkOut.isSameDay(day);
    }


    @Override
    public String toString() {
        return "Meal{" +
                "day=" + day +
                ", mealTime=" + mealTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return day == meal.day &&
                mealTime == meal.mealTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, mealTime);
    }

}
