package fr.lcdlv.promo8.socrates.domain;

public class Hour {
    private final int hour;

    private Hour(int hour) {
        this.hour = hour;
    }

    public static Hour valueOf(int hour) {
        return new Hour(hour);
    }

    boolean isAfterOrEqual(Hour hour) {
        return this.hour >= hour.hour;
    }
}
