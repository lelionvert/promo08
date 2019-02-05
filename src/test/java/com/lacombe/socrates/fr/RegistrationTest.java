package com.lacombe.socrates.fr;

import com.lacombe.socrates.fr.domain.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static com.lacombe.socrates.fr.domain.StayPeriod.StayPeriodBuilder.from;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest {

    private static final int SINGLE_ROOM_PRICE = 370;
    private static final int NUMBER_OF_MEALS_FOR_FULL_CONFERENCE = 6;
    private static final int MEAL_PRICE = 40;
    private RoomCatalog roomCatalog = new RoomCatalog() {
        @Override
        public Price getPrice(RoomChoice choice) {
            return Price.of(370);
        }

    };
    BillingService billingService = new BillingService(roomCatalog, new MealBillingCalculator(Price.of(40)));

    @Test
    public void given_a_participant_for_full_conference() {

        RoomChoice roomChoice = RoomChoice.SINGLE;
        Checkin checkin = new Checkin(DayOfWeek.THURSDAY, LocalTime.of(19, 00));
        Checkout checkout = new Checkout(DayOfWeek.SUNDAY, LocalTime.of(14, 00));
        Participant participant = new Participant(roomChoice, from(checkin).to(checkout).build());
        Price totalPrice = billingService.calculateBill(participant);
        assertThat(Price.of(SINGLE_ROOM_PRICE + NUMBER_OF_MEALS_FOR_FULL_CONFERENCE * MEAL_PRICE)).isEqualTo(totalPrice);
    }
}
