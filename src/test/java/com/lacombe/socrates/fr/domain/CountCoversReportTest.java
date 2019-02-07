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
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CountCoversReportTest {

    private final Participant aVegetarianGuy = aParticipant(VEGETARIAN);
    @Mock
    private ParticipantRegister participantRegister;
    private Socrates socrates;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        socrates = new Socrates(participantRegister, THURSDAY, LocalTime.of(21, 00));
    }


    @Test
    public void given_no_participants_should_return_empty_report() {
        when(participantRegister.getAllParticipant()).thenReturn(new ArrayList<>());
        CountCoversReport countCoversReport = new CountCoversReport();
        Socrates socrates = this.socrates;
        CountCoversReport result = socrates.countCoversReport();
        assertThat(countCoversReport).isEqualTo(result);
    }

    @Test
    public void given_one_vegeterian_participant_for_one_meal_should_return_number_of_covers_for_vegeterians_for_the_meal() {
        givenParticipantsWithDiet(aVegetarianGuy);

        CountCoversReport countCoversReport = new CountCoversReport(new MealCoverReport(new Meal(THURSDAY, LUNCH), VEGETARIAN, 1));

        CountCoversReport result = this.socrates.countCoversReportForMeal(new Meal(THURSDAY, LUNCH));

        verify(participantRegister).getAllParticipant();
        assertThat(countCoversReport).isEqualTo(result);
    }

    @Test
    @Parameters({"VEGETARIAN"})
    public void given_two_vegeterians_participant_for_one_meal_should_return_two__covers_for_vegeterians_for_the_meal(Diet diet) {
        when(participantRegister.getAllParticipant()).thenReturn(asList(
                aParticipant(diet),
                aParticipant(diet)));

        CountCoversReport countCoversReport = new CountCoversReport(new MealCoverReport(new Meal(THURSDAY, LUNCH), diet, 2));

        CountCoversReport result = this.socrates.countCoversReportForMeal(new Meal(THURSDAY, LUNCH));

        verify(participantRegister).getAllParticipant();
        assertThat(countCoversReport).isEqualTo(result);
    }

    private Participant aParticipant(Diet diet) {
        return new Participant(NO_ACCOMMODATION,
                StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00))).
                        to(new Checkout(FRIDAY, of(23, 00))).build(),
                new Mail("toto@gmail.com"), diet);
    }

    @Test
    public void given_a_vegetarian_participant_should_return_a_meal_report_for_1_cover_vegetarian() {
        givenParticipantsWithDiet(aVegetarianGuy);
        Meal meal = new Meal(THURSDAY, LUNCH);
        MealReportByDiet result = this.socrates.getMealReport(meal);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);
        MealReportByDiet countCoverReport = new MealReportByDiet(meal, coversByDiet);
        assertThat(result).isEqualTo(countCoverReport);
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
        Meal meal = new Meal(THURSDAY, LUNCH);
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
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(Arrays.asList(new Meal(THURSDAY, DINNER)));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(THURSDAY, DINNER), coversByDiet));
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_one_participant_should_return_report_with_1_cover_vegetarian() {
        givenParticipantsWithDiet(aVegetarianGuy);
        Socrates socrates = this.socrates;
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(Arrays.asList(new Meal(THURSDAY, DINNER)));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);
        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(THURSDAY, DINNER), coversByDiet));
        verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_a_vegetarian_participant_and_one_meal_should_give_report_for_meal_with_cover_for_vegetarian() {
        givenParticipantsWithDiet(aVegetarianGuy);
        Socrates socrates = this.socrates;
        List<Meal> meals = Arrays.asList(new Meal(FRIDAY, DINNER));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FRIDAY, DINNER), coversByDiet));
        verify(participantRegister).getAllParticipant();
        assertThat(result).isEqualToIgnoringGivenFields(countCoverReportByDiet, "nbColdMeals");
    }

    @Test
    public void given_a_vegetarian_participant_and_more_meals_should_give_report_for_meals_with_cover_for_vegetarian() {
        givenParticipantsWithDiet(aVegetarianGuy);
        Socrates socrates = this.socrates;
        List<Meal> meals = Arrays.asList(new Meal(FRIDAY, DINNER), new Meal(FRIDAY, LUNCH));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGETARIAN, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FRIDAY, DINNER), coversByDiet), new MealReportByDiet(new Meal(FRIDAY, LUNCH), coversByDiet));
        verify(participantRegister, times(meals.size())).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_a_vegan_participant_and_a_more_meals_should_give_report_for_meals_with_cover_for_vegan() {
        givenParticipantsWithDiet(aParticipant(VEGAN));
        Socrates socrates = this.socrates;
        List<Meal> meals = Arrays.asList(new Meal(FRIDAY, DINNER), new Meal(FRIDAY, LUNCH));
        CountCoversReportByDiet result = socrates.countCoversReportByDiet(meals);
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(VEGAN, 1L);

        CountCoversReportByDiet countCoverReportByDiet = new CountCoversReportByDiet(new MealReportByDiet(new Meal(FRIDAY, DINNER), coversByDiet), new MealReportByDiet(new Meal(FRIDAY, LUNCH), coversByDiet));
        verify(participantRegister, times(meals.size())).getAllParticipant();
        assertThat(result).isEqualTo(countCoverReportByDiet);
    }

    @Test
    public void given_participants_after_limit_arrival_and_before_limit_arrival_should_return_a_cover_and_a_cold_meal() {

        Diet diet = VEGAN;
        givenParticipantsWithDiet(aParticipantAfterLimitCheckin(diet), aParticipantAfterLimitCheckin(diet), aParticipant(diet));
        Map<Diet, Long> coversByDiet = new HashMap<>();
        coversByDiet.put(diet, 1L);
        MealReportByDiet mealReportByDiet = new MealReportByDiet(new Meal(THURSDAY, DINNER), coversByDiet, 2);
        MealReportByDiet result = socrates.getMealReport(new Meal(THURSDAY, DINNER));
        assertThat(result).isEqualTo(mealReportByDiet);
    }

    @Test
    public void nn() {

        Diet diet = VEGAN;
        givenParticipantsWithDiet(new Participant(NO_ACCOMMODATION,
                StayPeriod.StayPeriodBuilder.from(new Checkin(FRIDAY, of(20, 00))).
                        to(new Checkout(FRIDAY, of(23, 00))).build(),
                new Mail("toto@gmail.com"), diet));
        MealReportByDiet mealReportByDiet = new MealReportByDiet(new Meal(FRIDAY, DINNER), new HashMap<>(), 0);
        MealReportByDiet result = socrates.getMealReport(new Meal(THURSDAY, DINNER));
        assertThat(result).isEqualToComparingOnlyGivenFields(mealReportByDiet, "nbColdMeals");
    }

    private Participant aParticipantAfterLimitCheckin(Diet diet) {
        return new Participant(NO_ACCOMMODATION,
                StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(22, 00))).
                        to(new Checkout(FRIDAY, of(23, 00))).build(),
                new Mail("toto@gmail.com"), diet);
    }
}
