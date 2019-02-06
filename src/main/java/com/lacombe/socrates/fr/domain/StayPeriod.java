package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class StayPeriod {
    private final Checkin checkin;
    private final Checkout checkout;

    private StayPeriod(Checkin checkin, Checkout checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public boolean checkedInAfter(DayOfWeek anotherDay, LocalTime anotherTime) {
        return checkin.isAfter(anotherDay, anotherTime);

    }

    public boolean checkedOutBefore(DayOfWeek anotherDay, LocalTime anotherTime) {
        return checkout.isBefore(anotherDay, anotherTime);
    }

    public boolean checkedInDay(DayOfWeek day) {
        return checkin.isOnDay(day);
    }

    public boolean checkedInTimeAfter(LocalTime anotherTime) {
        return checkin.isAfterTime(anotherTime);
    }


    public static final class StayPeriodBuilder {
        private Checkin checkin;
        private Checkout checkout;

        private StayPeriodBuilder(Checkin checkin) {

            this.checkin = checkin;
        }

        public static StayPeriodBuilder from(Checkin checkin) {
            return new StayPeriodBuilder(checkin);
        }


        public StayPeriodBuilder to(Checkout checkout) {
            this.checkout = checkout;
            return this;
        }

        public StayPeriod build() {
            return new StayPeriod(checkin, checkout);
        }
    }
}
