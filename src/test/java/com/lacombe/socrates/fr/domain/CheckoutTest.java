package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkout;
import org.junit.jupiter.api.Test;

import static java.time.DayOfWeek.*;
import static java.time.LocalTime.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutTest {
    @Test
    void given_checkout_day_before_another_day_should_return_true() {
        boolean result = new Checkout(FRIDAY, of(14, 00)).isBefore(SATURDAY, of(14, 00));
        assertTrue(result);
    }

    @Test
    void given_checkout_day_after_another_day_should_return_false() {
        boolean result = new Checkout(SUNDAY, of(14, 00)).isBefore(SATURDAY, of(14, 00));
        assertFalse(result);
    }

    @Test
    void given_checkout_day_the_same_day_and_time_before_should_return_true() {
        boolean result = new Checkout(SUNDAY, of(14, 00)).isBefore(SUNDAY, of(15, 00));
        assertTrue(result);
    }
}