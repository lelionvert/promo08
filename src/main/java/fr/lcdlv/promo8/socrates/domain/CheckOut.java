package fr.lcdlv.promo8.socrates.domain;

public class CheckOut {
    private final SocratesDay day;
    private final Hour hour;

    public CheckOut(SocratesDay day, Hour hour) {
        this.hour = hour;
        this.day = day;
    }

    boolean isBeforeOrEqual(CheckOut checkOut) {
        return checkOut.day.isAfter(this.day) || checkOut.hour.isAfterOrEqual(this.hour);
    }
}
