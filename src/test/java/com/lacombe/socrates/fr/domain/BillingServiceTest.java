package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.lacombe.socrates.fr.domain.StayPeriod.StayPeriodBuilder.from;
import static java.time.DayOfWeek.*;
import static java.time.LocalTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class BillingServiceTest {

    private static final int MEALS_PRICE = 240;
    private static final int ROOM_PRICE = 370;
    public static final int NO_MEAL_PRICE = 0;
    public static final int NO_ROOM_PRICE = 0;
    private BillingService billingService;
    @Mock
    private RoomCatalog roomCatalog;
    @Mock
    private MealBillingService mealBillingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mealBillingService.calculatePrice(ArgumentMatchers.any(Participant.class))).thenReturn(Price.of(NO_MEAL_PRICE));
        when(roomCatalog.getPrice(any(RoomChoice.class))).thenReturn(Price.of(NO_ROOM_PRICE));
        billingService = new BillingService(roomCatalog, mealBillingService);
    }

    @Test
    public void with_room_price_provided_and_without_meal_price_should_return_room_price() {
        when(roomCatalog.getPrice(RoomChoice.SINGLE)).thenReturn(Price.of(ROOM_PRICE));
        Price price = billingService.calculateBill(new Participant(RoomChoice.SINGLE, from(null).to(null).build()));
        assertThat(price).isEqualTo(Price.of(ROOM_PRICE));
    }


    @Test
    public void without_room_price_and_with_meals_price_should_give_meals_price() {

        final Checkin checkin = new Checkin(THURSDAY, of(19, 00));
        final Checkout checkout = new Checkout(SUNDAY, of(14, 00));
        Participant participant = new Participant(RoomChoice.NO_ACCOMMODATION, from(checkin).to(checkout).build());
        when(mealBillingService.calculatePrice(participant)).thenReturn(Price.of(MEALS_PRICE));
        Price price = billingService.calculateBill(participant);
        assertThat(price).isEqualTo(Price.of(MEALS_PRICE));
    }

    @Test
    void with_room_price_and_meal_price_should_return_total() {
        final Checkin checkin = new Checkin(FRIDAY, of(19, 00));
        final Checkout checkout = new Checkout(SUNDAY, of(14, 00));
        Participant participant = new Participant(RoomChoice.SINGLE, from(checkin).to(checkout).build());
        when(roomCatalog.getPrice(RoomChoice.SINGLE)).thenReturn(Price.of(ROOM_PRICE));
        when(mealBillingService.calculatePrice(participant)).thenReturn(Price.of(MEALS_PRICE));
        Price price = billingService.calculateBill(participant);
        assertThat(price).isEqualTo(Price.of(MEALS_PRICE + ROOM_PRICE));
    }
}
