package fr.lcdlv.promo8.socrates.domain;

public enum SocratesDay {
    THURSDAY(1),
    FRIDAY(2),
    SATURDAY(3),
    SUNDAY(4);

    private final int dayIndex;

    SocratesDay(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public boolean isAfter(SocratesDay day) {
        return this.dayIndex > day.dayIndex;
    }

}
