package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.*;

import static com.lacombe.socrates.fr.domain.CookService.DINNER;
import static com.lacombe.socrates.fr.domain.CookService.LUNCH;
import static com.lacombe.socrates.fr.domain.Diet.VEGAN;
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
    @Parameters({"VEGETARIAN"})
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

    @Test
    public void given_a_vegetarian_participant_should_return_a_meal_report_for_1_cover_vegetarian() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN)));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        Meal meal = new Meal(THURSDAY, LUNCH);
        MealReportByDiet result = socrates.getMealReport(meal);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);
        MealReportByDiet countCoverReport = new MealReportByDiet(meal, coversByDiet);
        assertThat(result).isEqualTo(countCoverReport);
    }

    @Test
    public void given_two_vegeterian_participants_should_return_a_meal_report_for_2_covers_vegetarian() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN),
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN)));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        Meal meal = new Meal(THURSDAY, LUNCH);
        MealReportByDiet result = socrates.getMealReport(meal);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 2L);
        MealReportByDiet countCoverReport = new MealReportByDiet(meal, coversByDiet);
        Mockito.verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReport);
    }

    @Test
    public void given_no_participant_should_return_report_with_0_cover() {
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(Arrays.asList(new Meal(THURSDAY, DINNER)));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(THURSDAY, DINNER), coversByDiet));
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_one_participant_should_return_report_with_1_cover_vegetarian() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN)));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(Arrays.asList(new Meal(THURSDAY, DINNER)));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);
        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(THURSDAY, DINNER), coversByDiet));
        Mockito.verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_a_vegetarian_participant_and_one_meal_should_give_report_for_meal_with_cover_for_vegetarian() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN)));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        List<Meal> meals = Arrays.asList(new Meal(FRIDAY, DINNER));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FRIDAY, DINNER), coversByDiet));
        Mockito.verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_a_vegetarian_participant_and_more_meals_should_give_report_for_meals_with_cover_for_vegetarian() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGETARIAN)));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        List<Meal> meals = Arrays.asList(new Meal(FRIDAY, DINNER), new Meal(FRIDAY, LUNCH));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FRIDAY, DINNER), coversByDiet), new MealReportByDiet(new Meal(FRIDAY, LUNCH), coversByDiet));
        Mockito.verify(participantRegister, Mockito.times(meals.size())).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_a_vegan_participant_and_a_more_meals_should_give_report_for_meals_with_cover_for_vegan() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                new Participant(NO_ACCOMMODATION,
                        StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                                to(new Checkout(FRIDAY, of(23, 00))).build(),
                        new Mail("toto@gmail.com"), VEGAN)));
        Socrates socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(16, 00));
        List<Meal> meals = Arrays.asList(new Meal(FRIDAY, DINNER), new Meal(FRIDAY, LUNCH));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGAN, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FRIDAY, DINNER), coversByDiet), new MealReportByDiet(new Meal(FRIDAY, LUNCH), coversByDiet));
        Mockito.verify(participantRegister, Mockito.times(meals.size())).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }
}
