package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static com.lacombe.socrates.fr.domain.StayPeriod.StayPeriodBuilder;
import static com.lacombe.socrates.fr.domain.StayPeriod.StayPeriodBuilder.from;

class MealBillingCalculatorTest {

    private static final int MEAL_PRICE = 40;
    private static final int ALL_MEALS = 6;
    public static final int ONE_MISSED_MEAL = 5;
    private MealBillingCalculator mealBillingCalculator;

    @BeforeEach
    void setUp() {
        mealBillingCalculator = new MealBillingCalculator(Price.of(MEAL_PRICE));
    }

    @Test
    void with_all_meals_should_return_price_for_all_meals() {
        final Checkin checkin = new Checkin(DayOfWeek.THURSDAY, LocalTime.of(18, 00));
        final Checkout checkout = new Checkout(DayOfWeek.SUNDAY, LocalTime.of(14, 00));
        Participant participant = new Participant(RoomChoice.NO_ACCOMMODATION,
                from(checkin).to(checkout).build(), Diet.VEGETARIAN);
        Price price = mealBillingCalculator.calculatePrice(participant);
        Assertions.assertThat(price).isEqualTo(Price.of(ALL_MEALS * MEAL_PRICE));
    }

    @Test
    void with_one_missed_meal_should_return_all_meals_price_minus_one_meal() {
        Participant participant = new Participant(RoomChoice.NO_ACCOMMODATION,
                StayPeriodBuilder
                        .from(new Checkin(DayOfWeek.THURSDAY, LocalTime.of(21, 00)))
                        .to(new Checkout(DayOfWeek.SUNDAY, LocalTime.of(14, 00)))
                        .build(), Diet.VEGETARIAN);
        Price price = mealBillingCalculator.calculatePrice(participant);
        Assertions.assertThat(price).isEqualTo(Price.of(ONE_MISSED_MEAL * MEAL_PRICE));
    }

    @Test
    void name() {
        Participant participant = new Participant(RoomChoice.NO_ACCOMMODATION,
                StayPeriodBuilder
                        .from(new Checkin(DayOfWeek.THURSDAY, LocalTime.of(19, 00)))
                        .to(new Checkout(DayOfWeek.SUNDAY, LocalTime.of(10, 00)))
                        .build(), Diet.VEGETARIAN);
        Price price = mealBillingCalculator.calculatePrice(participant);
        Assertions.assertThat(price).isEqualTo(Price.of(ONE_MISSED_MEAL * MEAL_PRICE));
    }
}