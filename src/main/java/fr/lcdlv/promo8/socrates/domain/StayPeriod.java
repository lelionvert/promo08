package fr.lcdlv.promo8.socrates.domain;

public class StayPeriod {
    private final CheckIn checkIn;
    private final CheckOut checkOut;

    StayPeriod(CheckIn checkIn, CheckOut checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public boolean startAfter(CheckIn checkIn) {
        return this.checkIn.isAfterOrEqual(checkIn);
    }

    public boolean leaveBefore(CheckOut checkOut) {
        return this.checkOut.isBeforeOrEqual(checkOut);
    }

    boolean arriveSameDay(CheckIn otherCheckIn) {
        return checkIn.isSameDay(otherCheckIn);
    }
}
