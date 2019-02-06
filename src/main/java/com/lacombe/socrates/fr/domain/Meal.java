package com.lacombe.socrates.fr.domain;

import java.time.DayOfWeek;
import java.util.Objects;

public class Meal {
    private final DayOfWeek dayOfWeek;
    private final CookService dinner;

    public Meal(DayOfWeek dayOfWeek, CookService dinner) {
        this.dayOfWeek = dayOfWeek;
        this.dinner = dinner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return dayOfWeek == meal.dayOfWeek &&
                dinner == meal.dinner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, dinner);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "dayOfWeek=" + dayOfWeek +
                ", dinner=" + dinner +
                '}';
    }
}
