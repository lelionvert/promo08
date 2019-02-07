package fr.lcdlv.promo8.socrates.domain;

public class CheckIn {
    private final SocratesDay day;
    private final Hour hour;

    public CheckIn(SocratesDay day, Hour hour) {
        this.day = day;
        this.hour = hour;
    }

    boolean isAfterOrEqual(CheckIn checkIn) {
        return this.day.isAfter(checkIn.day) || this.hour.isAfterOrEqual(checkIn.hour);
    }

    boolean isSameDay(CheckIn checkIn) {
        return this.day.equals(checkIn.day);
    }
}
