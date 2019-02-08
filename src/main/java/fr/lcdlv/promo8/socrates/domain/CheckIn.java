package fr.lcdlv.promo8.socrates.domain;

public class CheckIn {
    private final SocratesDay day;
    private final Hour hour;

    CheckIn(SocratesDay day, Hour hour) {
        this.day = day;
        this.hour = hour;
    }

    boolean isAfterOrEqual(CheckIn checkIn) {
        return this.day.isAfter(checkIn.day) || this.hour.isAfterOrEqual(checkIn.hour);
    }

    boolean isSameDay(CheckIn checkIn) {
        return isSameDay(checkIn.day);
    }

    public boolean isSameDay(SocratesDay otherDay) {
        return this.day.equals(otherDay);
    }
}
