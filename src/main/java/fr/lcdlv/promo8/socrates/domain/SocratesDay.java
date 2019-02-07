package fr.lcdlv.promo8.socrates.domain;

public enum SocratesDay {
    THURSDAY(1, 1),
    FRIDAY(2, 2),
    SATURDAY(3, 2),
    SUNDAY(4, 1);

    private final int dayIndex;
    private final int numberOfMeals;

    SocratesDay(int dayIndex, int numberOfMeals) {
        this.dayIndex = dayIndex;
        this.numberOfMeals = numberOfMeals;
    }

    public boolean isAfter(SocratesDay day) {
        return this.dayIndex > day.dayIndex;
    }

    public int getNumberOfMeals() {
        return numberOfMeals;
    }
}
