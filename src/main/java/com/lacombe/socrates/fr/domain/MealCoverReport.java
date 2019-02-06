package com.lacombe.socrates.fr.domain;

import java.util.Objects;

public class MealCoverReport {
    private final Meal meal;

    private final Diet diet;
    private final long nbOfCovers;


    public MealCoverReport(Meal meal, Diet diet, long nbOfCovers) {
        this.meal = meal;
        this.diet = diet;
        this.nbOfCovers = nbOfCovers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealCoverReport that = (MealCoverReport) o;
        return nbOfCovers == that.nbOfCovers &&
                Objects.equals(meal, that.meal) &&
                diet == that.diet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, diet, nbOfCovers);
    }


    @Override
    public String toString() {
        return "MealCoverReport{" +
                "meal=" + meal +
                ", diet=" + diet +
                ", nbOfCovers=" + nbOfCovers +
                '}';
    }
}
