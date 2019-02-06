package com.lacombe.socrates.fr.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CountCoversReport {
    private final List<MealCoverReport> mealCoverReport;

    public CountCoversReport(MealCoverReport... mealCoverReport) {

        this.mealCoverReport = Arrays.asList(mealCoverReport);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountCoversReport that = (CountCoversReport) o;
        return Objects.equals(mealCoverReport, that.mealCoverReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealCoverReport);
    }

    @Override
    public String toString() {
        return "CountCoversReport{" +
                "mealCoverReport=" + mealCoverReport +
                '}';
    }
}
