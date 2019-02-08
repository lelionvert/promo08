package fr.lcdlv.promo8.socrates.domain.Billing;

import java.util.Objects;

public class MealReport {
    private final Meal meal;
    private final NumberOfMealsByDiet numberOfMealsByDiet;


    public MealReport(Meal meal, NumberOfMealsByDiet numberOfMealsByDiet) {
        this.meal = meal;
        this.numberOfMealsByDiet = numberOfMealsByDiet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealReport that = (MealReport) o;
        return Objects.equals(meal, that.meal) &&
                Objects.equals(numberOfMealsByDiet, that.numberOfMealsByDiet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, numberOfMealsByDiet);
    }

    @Override
    public String toString() {
        return "MealReport{" +
                "meal=" + meal +
                ", numberOfMealsByDiet=" + numberOfMealsByDiet +
                '}';
    }
}
