package com.lacombe.socrates.fr;

import org.junit.jupiter.api.Test;

import static java.time.DayOfWeek.*;
import static java.time.LocalTime.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckinTest {


    @Test
    void given_checkin_after_another_day_should_return_true() {
        boolean result = new Checkin(FRIDAY, of(12, 00)).isAfter(THURSDAY, of(12, 00));
        assertTrue(result);
    }

    @Test
    void given_checkin_before_another_day_should_return_false() {

        boolean result = new Checkin(FRIDAY, of(12, 00)).isAfter(SATURDAY, of(12, 00));
        assertFalse(result);
    }

    @Test
    void given_checkin_same_day_and_time_after_should_return_true() {
        boolean result = new Checkin(FRIDAY, of(12, 00)).isAfter(FRIDAY, of(11, 00));
        assertTrue(result);
    }

    @Test
    void given_checkin_same_day_and_time_before_should_return_false() {
        boolean result = new Checkin(FRIDAY, of(12, 00)).isAfter(FRIDAY, of(13, 00));
        assertFalse(result);
    }
}