package com.lacombe.socrates.fr.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CountCoversReportByDiet {

    private final List<MealReportByDiet> mealsReportByDiet;

    public CountCoversReportByDiet(MealReportByDiet... mealsReportByDiet) {

        this.mealsReportByDiet = Arrays.asList(mealsReportByDiet);
    }

    public CountCoversReportByDiet(List<MealReportByDiet> mealReportByDiets) {
        this.mealsReportByDiet = mealReportByDiets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountCoversReportByDiet that = (CountCoversReportByDiet) o;
        return Objects.equals(mealsReportByDiet, that.mealsReportByDiet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealsReportByDiet);
    }

    @Override
    public String toString() {
        return "CountCoversReportByDiet{" +
                "mealsReportByDiet=" + mealsReportByDiet +
                '}';
    }
}
