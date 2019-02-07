package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import com.lacombe.socrates.fr.domain.StayPeriod.StayPeriodBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static com.lacombe.socrates.fr.domain.RoomChoice.NO_ACCOMMODATION;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.LocalTime.of;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ColdMealAcceptanceTest {

    @Mock
    private ParticipantRegister participantRegister;
    private Socrates socrates;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        socrates = new Socrates(participantRegister, THURSDAY, of(21, 00), new ColdMeals());
    }


    @Test
    void given_no_participant_should_return_empty_cold_meal_listing() {
        when(participantRegister.getAllParticipant()).thenReturn(new ArrayList<>());
        ColdMealListing coldMealListing = new ColdMealListing(new ArrayList<>());

        ColdMealListing result = socrates.determineColdMealslisting();

        assertThat(coldMealListing).isEqualTo(result);
    }

    @Test
    void given_a_participant_with_a_arrival_in_limit_day_and_after_limit_time_for_cold_meal_should_be_in_cold_meal_listing() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriodBuilder.from(new Checkin(THURSDAY, of(22, 00)))
                                .to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), Diet.VEGETARIAN)));

        ColdMealListing coldMealListing = new ColdMealListing(asList(new Mail("toto@gmail.com")));
        ColdMealListing result = socrates.determineColdMealslisting();

        assertThat(coldMealListing).isEqualTo(result);
    }

    @Test
    void given_a_participant_with_a_arrival_after_limit_day_should_not_in_cold_meal_listing() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriodBuilder.from(new Checkin(FRIDAY, of(22, 00)))
                                .to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), Diet.VEGETARIAN)));

        ColdMealListing coldMealListing = new ColdMealListing(asList());
        ColdMealListing result = socrates.determineColdMealslisting();

        assertThat(coldMealListing).isEqualTo(result);
    }

    @Test
    void given_a_participant_with_a_arrival_time_before_limit_time_should_not_in_cold_meal_listing() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), Diet.VEGETARIAN)));

        ColdMealListing coldMealListing = new ColdMealListing(asList());
        ColdMealListing result = socrates.determineColdMealslisting();

        assertThat(coldMealListing).isEqualTo(result);
    }

    @Test
    void name() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("tio@gmail.com"), Diet.VEGETARIAN),
                new Participant(NO_ACCOMMODATION,
                        StayPeriodBuilder.from(new Checkin(THURSDAY, of(21, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), Diet.VEGETARIAN),
                new Participant(NO_ACCOMMODATION,
                        StayPeriodBuilder.from(new Checkin(FRIDAY, of(22, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("rara@gmail.com"), Diet.VEGETARIAN)));

        ColdMealListing coldMealListing = new ColdMealListing(asList(new Mail("toto@gmail.com")));
        ColdMealListing result = socrates.determineColdMealslisting();

        assertThat(coldMealListing).isEqualTo(result);

    }
}