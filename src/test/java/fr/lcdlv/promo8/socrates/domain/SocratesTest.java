package fr.lcdlv.promo8.socrates.domain;

import com.sun.javafx.collections.UnmodifiableListSet;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class SocratesTest {
    private static StayPeriod stayPeriod;
    private static CheckIn lateCheckIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
    private static CheckOut earlyCheckOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11));
    private static CheckIn checkInLimit = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
    private EnumMap<Diet, Integer> coverByDiet = new EnumMap<Diet, Integer>(Diet.class);

    static {
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(18));
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(15));
        stayPeriod = new StayPeriod(checkIn, checkOut);
    }

    private void fillEnumMap() {
        coverByDiet.put(Diet.VEGETARIAN, 0);
        coverByDiet.put(Diet.OMNIVORE, 0);
        coverByDiet.put(Diet.PESCATARIAN, 0);
        coverByDiet.put(Diet.VEGAN, 0);
    }

    @Test
    public void given_3_late_participants_should_return_a_list_of_3_emails() {
        StayPeriod stayPeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.VEGETARIAN)
        );
        UnmodifiableListSet<String> expectedEmails = new UnmodifiableListSet<String>(Arrays.asList("thomas@email.fr", "stephen@email.fr", "steeve@email.fr"));
        CheckIn checkInLimit = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
        Socrates socrates = new Socrates(participants, checkInLimit);
        ColdMealChecker coldMealChecker = socrates.giveColdMeals();
        Assertions.assertThat(coldMealChecker.getEmails()).isEqualTo(expectedEmails);
    }

    @Test
    public void given_3_late_participants_should_return_a_counter_of_3_meals() {
        StayPeriod stayPeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.VEGETARIAN)
        );
        CheckIn checkInLimit = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
        Socrates socrates = new Socrates(participants, checkInLimit);

        ColdMealChecker coldMealChecker = socrates.giveColdMeals();
        Assertions.assertThat(3).isEqualTo(coldMealChecker.count());
    }


    @Test
    public void given_3_friday_late_participants_should_return_a_counter_of_0_meals() {

        CheckIn checkIn = new CheckIn(SocratesDay.FRIDAY, Hour.valueOf(22));
        StayPeriod stayPeriod = new StayPeriod(checkIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.VEGETARIAN)
        );
        CheckIn checkInLimit = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
        Socrates socrates = new Socrates(participants, checkInLimit);

        ColdMealChecker coldMealChecker = socrates.giveColdMeals();
        Assertions.assertThat(coldMealChecker.count()).isEqualTo(0);
    }

    @Test
    public void given_3_vegetarian_participants_should_return_a_count_of_3_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(Diet.VEGETARIAN, 3);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.VEGETARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit);
        List<MealReport> report = socrates.giveMealsReport();

        //Assert
        Assertions.assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void given_3_omnivore_participants_should_return_a_count_of_3_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(Diet.OMNIVORE, 3);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.OMNIVORE)
        );
        Socrates socrates = new Socrates(participants, checkInLimit);
        List<MealReport> report = socrates.giveMealsReport();

        //Assert
        Assertions.assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void given_3_pescatarian_participants_should_return_a_count_of_3_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(Diet.PESCATARIAN, 3);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.PESCATARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.PESCATARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit);
        List<MealReport> report = socrates.giveMealsReport();

        //Assert
        Assertions.assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void given_1__participant_of_each_diet_should_return_a_count_of_1_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(Diet.PESCATARIAN, 1);
        coverByDiet.put(Diet.OMNIVORE, 1);
        coverByDiet.put(Diet.VEGETARIAN, 1);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", Diet.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", Diet.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", Diet.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit);
        List<MealReport> report = socrates.giveMealsReport();

        //Assert
        Assertions.assertThat(report).isEqualTo(expectedReport);
    }

    private List<MealReport> getMealReportsForTests(EnumMap<Diet, Integer> coverByDiet) {
        MealReport thursdayDinnerReport = new MealReport(new Meal(SocratesDay.THURSDAY, MealTime.DINNER), coverByDiet);
        MealReport fridayLunchReport = new MealReport(new Meal(SocratesDay.FRIDAY, MealTime.LUNCH), coverByDiet);
        MealReport fridayDinnerReport = new MealReport(new Meal(SocratesDay.FRIDAY, MealTime.DINNER), coverByDiet);
        MealReport saturdayLunchReport = new MealReport(new Meal(SocratesDay.SATURDAY, MealTime.LUNCH), coverByDiet);
        MealReport saturdayDinnerReport = new MealReport(new Meal(SocratesDay.SATURDAY, MealTime.DINNER), coverByDiet);
        MealReport sundayLunchReport = new MealReport(new Meal(SocratesDay.SUNDAY, MealTime.LUNCH), coverByDiet);
        return new ArrayList<MealReport>(Arrays.asList(thursdayDinnerReport, fridayLunchReport, fridayDinnerReport, saturdayLunchReport, saturdayDinnerReport, sundayLunchReport));
    }
}
