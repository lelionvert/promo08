package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static com.lacombe.socrates.fr.domain.StayPeriod.StayPeriodBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest {

    private static final int SINGLE_ROOM_PRICE = 370;
    private static final int NUMBER_OF_MEALS_FOR_FULL_CONFERENCE = 6;
    private static final int MEAL_PRICE = 40;
    private BillingService billingService;


    @Before
    public void setUp() {
        billingService = new BillingService(new RoomCatalogInMemory(), new MealBillingCalculator(Price.of(40)));
    }

    @Test
    @Parameters({"SINGLE, 370", "NO_ACCOMMODATION,0", "DOUBLE,270", "TRIPLE,170"})
    public void given_a_participant_for_full_conference(RoomChoice choice, int roomPrice) {

        Participant participant = new Participant(choice,
                StayPeriodBuilder
                        .from(new Checkin(DayOfWeek.THURSDAY, LocalTime.of(19, 00)))
                        .to(new Checkout(DayOfWeek.SUNDAY, LocalTime.of(14, 00))).build());
        Price totalPrice = billingService.calculateBill(participant);
        assertThat(Price.of(roomPrice + NUMBER_OF_MEALS_FOR_FULL_CONFERENCE * MEAL_PRICE)).isEqualTo(totalPrice);
    }
}
