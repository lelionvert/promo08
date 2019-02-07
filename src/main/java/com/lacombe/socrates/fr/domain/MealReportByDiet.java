package com.lacombe.socrates.fr.domain;

import java.util.Map;
import java.util.Objects;

public class MealReportByDiet {
    private final Meal meal;
    private final Map<Diet, Long> coversByDiet;
    private final int nbColdMeals;

    public MealReportByDiet(Meal meal, Map<Diet, Long> coversByDiet) {
        this(meal, coversByDiet, 0);
    }

    public MealReportByDiet(Meal meal, Map<Diet, Long> coversByDiet, int nbColdMeals) {
        this.meal = meal;
        this.coversByDiet = coversByDiet;
        this.nbColdMeals = nbColdMeals;
    }

    @Override
    public String toString() {
        return "MealReportByDiet{" +
                "meal=" + meal +
                ", coversByDiet=" + coversByDiet +
                ", nbColdMeals=" + nbColdMeals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealReportByDiet that = (MealReportByDiet) o;
        return nbColdMeals == that.nbColdMeals &&
                Objects.equals(meal, that.meal) &&
                Objects.equals(coversByDiet, that.coversByDiet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, coversByDiet, nbColdMeals);
    }

}
