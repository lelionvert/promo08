package fr.lcdlv.promo8.socrates.domain;

import java.util.EnumMap;
import java.util.Objects;

public class MealReport {
    private final EnumMap<MealType, Integer> numberOfCoversByDiet;
    private final Meal meal;

    public MealReport(Meal meal, EnumMap<MealType, Integer> numberOfCoversByDiet) {

        this.meal = meal;
        this.numberOfCoversByDiet = numberOfCoversByDiet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealReport that = (MealReport) o;
        return Objects.equals(numberOfCoversByDiet, that.numberOfCoversByDiet) &&
                Objects.equals(meal, that.meal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfCoversByDiet, meal);
    }

    @Override
    public String toString() {
        return "MealReport{" +
                "numberOfCoversByDiet=" + numberOfCoversByDiet +
                ", meal=" + meal +
                '}';
    }
}
