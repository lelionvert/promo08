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
import org.mockito.stubbing.OngoingStubbing;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lacombe.socrates.fr.domain.CookService.DINNER;
import static com.lacombe.socrates.fr.domain.CookService.LUNCH;
import static com.lacombe.socrates.fr.domain.Diet.VEGETARIAN;
import static com.lacombe.socrates.fr.domain.RoomChoice.NO_ACCOMMODATION;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.LocalTime.of;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CountCoversReportTest {

    public static final DayOfWeek FIRST_DAY_CONFERENCE = THURSDAY;
    public static final DayOfWeek NOT_FIRST_DAY = FRIDAY;
    private final Participant aVegetarianGuy = aParticipant(VEGETARIAN);
    @Mock
    private ParticipantRegister participantRegister;
    private Socrates socrates;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        socrates = new Socrates(participantRegister, FIRST_DAY_CONFERENCE, LocalTime.of(21, 00), new ColdMeals());
    }

    private Participant aParticipant(Diet diet) {
        return new Participant(NO_ACCOMMODATION,
                StayPeriod.StayPeriodBuilder.from(new Checkin(FIRST_DAY_CONFERENCE, of(20, 00))).
                        to(new Checkout(NOT_FIRST_DAY, of(23, 00))).build(),
                new Mail("toto@gmail.com"), diet);
    }

    @Test
    public void given_a_vegetarian_participant_should_return_a_meal_report_for_1_cover_vegetarian() {
        givenParticipantsWithDiet(aVegetarianGuy);
        Meal meal = new Meal(FIRST_DAY_CONFERENCE, LUNCH);
        MealReportByDiet result = this.socrates.getMealReport(meal);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);
        MealReportByDiet mealReportByDiet = new MealReportByDiet(meal, coversByDiet);
        assertThat(result).isEqualTo(mealReportByDiet);
    }

    private OngoingStubbing<List<Participant>> givenParticipantsWithDiet(Participant... aVegetarianGuy) {
        return when(participantRegister.getAllParticipant()).thenReturn(asList(
                aVegetarianGuy));
    }

    @Test
    public void given_two_vegeterian_participants_should_return_a_meal_report_for_2_covers_vegetarian() {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                aVegetarianGuy,
                aVegetarianGuy));
        Meal meal = new Meal(FIRST_DAY_CONFERENCE, LUNCH);
        MealReportByDiet result = this.socrates.getMealReport(meal);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 2L);
        MealReportByDiet countCoverReport = new MealReportByDiet(meal, coversByDiet);
        verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReport);
    }

    @Test
    public void given_no_participant_should_return_report_with_0_cover() {
        Socrates socrates = this.socrates;
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(Arrays.asList(new Meal(FIRST_DAY_CONFERENCE, DINNER)));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FIRST_DAY_CONFERENCE, DINNER), coversByDiet));
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "OMNIVORE", "PESCATARIAN"})
    public void given_one_participant_for_the_first_day_and_present_should_return_report_with_1_cover_for_this_diet(Diet diet) {
        givenParticipantsWithDiet(aParticipant(diet));
        CountCoversReportByDiet result = this.socrates.countCoversReportByDiet(Arrays.asList(new Meal(FIRST_DAY_CONFERENCE, DINNER)));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(diet, 1L);
        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FIRST_DAY_CONFERENCE, DINNER), coversByDiet));
        verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "OMNIVORE", "PESCATARIAN"})
    public void given_a_participant_for_second_day_and_one_meal_should_give_report_for_meal_with_cover_for_diet(Diet diet) {
        givenParticipantsWithDiet(aParticipant(diet));
        List<Meal> meals = Arrays.asList(new Meal(NOT_FIRST_DAY, DINNER));
        CountCoversReportByDiet result = this.socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(diet, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(NOT_FIRST_DAY, DINNER), coversByDiet));
        verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualToIgnoringGivenFields(countCoverReportByDiet, "nbColdMeals");
    }

    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "OMNIVORE", "PESCATARIAN"})
    public void given_a_participant_and_more_meals_should_give_report_for_meals_with_cover_for_diet(Diet diet) {
        givenParticipantsWithDiet(aParticipant(diet));
        List<Meal> meals = Arrays.asList(new Meal(NOT_FIRST_DAY, DINNER), new Meal(NOT_FIRST_DAY, LUNCH));
        CountCoversReportByDiet result = this.socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(diet, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(NOT_FIRST_DAY, DINNER), coversByDiet), new MealReportByDiet(new Meal(NOT_FIRST_DAY, LUNCH), coversByDiet));
        verify(participantRegister, times(meals.size())).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }



    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "OMNIVORE", "PESCATARIAN"})
    public void given_participants_after_limit_arrival_and_before_limit_arrival_should_return_a_cover_and_a_cold_meal(Diet diet) {

        givenParticipantsWithDiet(aParticipantAfterLimitCheckin(diet), aParticipantAfterLimitCheckin(diet), aParticipant(diet));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(diet, 1L);
        MealReportByDiet mealReportByDiet = new MealReportByDiet(new Meal(FIRST_DAY_CONFERENCE, DINNER), coversByDiet, 2);
        MealReportByDiet result = socrates.getMealReport(new Meal(FIRST_DAY_CONFERENCE, DINNER));
        assertThat(result).isEqualTo(mealReportByDiet);
    }

    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "OMNIVORE", "PESCATARIAN"})
    public void name(Diet diet) {

        givenParticipantsWithDiet(aParticipantAfterLimitCheckin(diet), aParticipantAfterLimitCheckin(diet), aParticipant(diet));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(diet, 3L);
        MealReportByDiet mealReportByDiet = new MealReportByDiet(new Meal(NOT_FIRST_DAY, DINNER), coversByDiet, 0);
        MealReportByDiet result = socrates.getMealReport(new Meal(NOT_FIRST_DAY, DINNER));
        assertThat(result).isEqualTo(mealReportByDiet);
    }

    @Test
    @Parameters({"VEGETARIAN", "VEGAN", "OMNIVORE", "PESCATARIAN"})
    public void given_participant_arrival_after_first_day_should_return_no_cover_for_him_on_the_first_day(Diet diet) {

        givenParticipantsWithDiet(new Participant(NO_ACCOMMODATION,
                StayPeriod.StayPeriodBuilder.from(new Checkin(NOT_FIRST_DAY, of(20, 00))).
                        to(new Checkout(NOT_FIRST_DAY, of(23, 00))).build(),
                new Mail("toto@gmail.com"), diet));
        MealReportByDiet mealReportByDiet = new MealReportByDiet(new Meal(NOT_FIRST_DAY, DINNER), new HashMap<>(), 0);
        MealReportByDiet result = socrates.getMealReport(new Meal(FIRST_DAY_CONFERENCE, DINNER));
        assertThat(result).isEqualToComparingOnlyGivenFields(mealReportByDiet, "nbColdMeals");
    }

    private Participant aParticipantAfterLimitCheckin(Diet diet) {
        return new Participant(NO_ACCOMMODATION,
                StayPeriod.StayPeriodBuilder.from(new Checkin(FIRST_DAY_CONFERENCE, of(22, 00))).
                        to(new Checkout(NOT_FIRST_DAY, of(23, 00))).build(),
                new Mail("toto@gmail.com"), diet);
    }
}
