package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.ArrayList;

import static com.lacombe.socrates.fr.domain.CookService.LUNCH;
import static com.lacombe.socrates.fr.domain.Diet.VEGETARIAN;
import static com.lacombe.socrates.fr.domain.RoomChoice.NO_ACCOMMODATION;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.LocalTime.of;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class CountCoversReportTest {

    @Mock
    private ParticipantRegister participantRegister;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void given_no_participants_should_return_empty_report() {
        when(participantRegister.getAllParticipant()).thenReturn(new ArrayList<>());
        CountCoversReport countCoversReport = new CountCoversReport();
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        CountCoversReport result = socrates.countCoversReport();
        assertThat(countCoversReport).isEqualTo(result);
    }

    @Test
    public void given_one_vegeterian_participant_for_one_meal_should_return_number_of_covers_for_vegeterians_for_the_meal() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN)));

        CountCoversReport countCoversReport = new CountCoversReport(new MealCoverReport(new Meal(THURSDAY, LUNCH), VEGETARIAN, 1));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));

        CountCoversReport result = socrates.countCoversReportForMeal(new Meal(THURSDAY, LUNCH));

        verify(participantRegister).getAllParticipant();
        assertThat(countCoversReport).isEqualTo(result);
    }

    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "PESCATARIAN"})
    public void given_two_vegeterians_participant_for_one_meal_should_return_two__covers_for_vegeterians_for_the_meal(Diet diet) {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), diet),
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), diet)));

        CountCoversReport countCoversReport = new CountCoversReport(new MealCoverReport(new Meal(THURSDAY, LUNCH), diet, 2));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));

        CountCoversReport result = socrates.countCoversReportForMeal(new Meal(THURSDAY, LUNCH));

        verify(participantRegister).getAllParticipant();
        assertThat(countCoversReport).isEqualTo(result);
    }
}
