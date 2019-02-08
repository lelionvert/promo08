package fr.lcdlv.promo8.socrates.domain;

import com.sun.javafx.collections.UnmodifiableListSet;
import fr.lcdlv.promo8.socrates.domain.Billing.*;
import fr.lcdlv.promo8.socrates.domain.ColdMeal.ColdMealChecker;
import org.junit.Test;

import java.util.*;

import static fr.lcdlv.promo8.socrates.domain.SocratesDay.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SocratesTest {
    private static final StayPeriod stayPeriod;
    private static final CheckIn lateCheckIn = new CheckIn(THURSDAY, Hour.valueOf(21));
    private static final CheckOut earlyCheckOut = new CheckOut(SUNDAY, Hour.valueOf(11));
    private static final CheckIn checkInLimit = new CheckIn(THURSDAY, Hour.valueOf(21));
    private final EnumMap<MealType, Integer> coverByDiet = new EnumMap<>(MealType.class);

    static {
        CheckIn checkIn = new CheckIn(THURSDAY, Hour.valueOf(18));
        CheckOut checkOut = new CheckOut(SUNDAY, Hour.valueOf(15));
        stayPeriod = new StayPeriod(checkIn, checkOut);
    }

    private void fillEnumMap() {
        coverByDiet.put(MealType.VEGETARIAN, 0);
        coverByDiet.put(MealType.OMNIVORE, 0);
        coverByDiet.put(MealType.PESCATARIAN, 0);
        coverByDiet.put(MealType.VEGAN, 0);
        coverByDiet.put(MealType.COLDMEAL, 0);
    }

    @Test
    public void given_3_late_participants_should_return_a_list_of_3_emails() {
        StayPeriod stayPeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.VEGETARIAN)
        );
        UnmodifiableListSet<String> expectedEmails = new UnmodifiableListSet<>(Arrays.asList("thomas@email.fr", "stephen@email.fr", "steeve@email.fr"));
        CheckIn checkInLimit = new CheckIn(THURSDAY, Hour.valueOf(21));
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        ColdMealChecker coldMealChecker = socrates.giveColdMealsChecker();
        assertThat(coldMealChecker.getEmails()).isEqualTo(expectedEmails);
    }

    @Test
    public void given_3_late_participants_should_return_a_counter_of_3_meals() {
        StayPeriod stayPeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.VEGETARIAN)
        );
        CheckIn checkInLimit = new CheckIn(THURSDAY, Hour.valueOf(21));
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));

        ColdMealChecker coldMealChecker = socrates.giveColdMealsChecker();
        assertThat(3).isEqualTo(coldMealChecker.count());
    }


    @Test
    public void given_3_friday_late_participants_should_return_a_counter_of_0_meals() {

        CheckIn checkIn = new CheckIn(FRIDAY, Hour.valueOf(22));
        StayPeriod stayPeriod = new StayPeriod(checkIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.VEGETARIAN)
        );
        CheckIn checkInLimit = new CheckIn(THURSDAY, Hour.valueOf(21));
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));

        ColdMealChecker coldMealChecker = socrates.giveColdMealsChecker();
        assertThat(coldMealChecker.count()).isEqualTo(0);
    }

    @Test
    public void given_3_vegetarian_participants_should_return_a_count_of_3_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.VEGETARIAN, 3);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.VEGETARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        List<MealReport> report = socrates.giveFullWeekMealsReport();

        //Assert
        assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void given_3_omnivore_participants_should_return_a_count_of_3_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.OMNIVORE, 3);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.OMNIVORE)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        List<MealReport> report = socrates.giveFullWeekMealsReport();

        //Assert
        assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void given_3_pescatarian_participants_should_return_a_count_of_3_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 3);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.PESCATARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.PESCATARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        List<MealReport> report = socrates.giveFullWeekMealsReport();

        //Assert
        assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void given_1__participant_of_each_diet_should_return_a_count_of_1_for_all_meals() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 1);
        coverByDiet.put(MealType.OMNIVORE, 1);
        coverByDiet.put(MealType.VEGETARIAN, 1);
        List<MealReport> expectedReport = getMealReportsForTests(coverByDiet);

        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        List<MealReport> report = socrates.giveFullWeekMealsReport();

        //Assert
        assertThat(report).isEqualTo(expectedReport);
    }

    private List<MealReport> getMealReportsForTests(EnumMap<MealType, Integer> coverByDiet) {
        MealReport thursdayDinnerReport = new MealReport(new Meal(THURSDAY, MealTime.DINNER), new NumberOfMealsByDiet(coverByDiet));
        MealReport fridayLunchReport = new MealReport(new Meal(FRIDAY, MealTime.LUNCH), new NumberOfMealsByDiet(coverByDiet));
        MealReport fridayDinnerReport = new MealReport(new Meal(FRIDAY, MealTime.DINNER), new NumberOfMealsByDiet(coverByDiet));
        MealReport saturdayLunchReport = new MealReport(new Meal(SATURDAY, MealTime.LUNCH), new NumberOfMealsByDiet(coverByDiet));
        MealReport saturdayDinnerReport = new MealReport(new Meal(SATURDAY, MealTime.DINNER), new NumberOfMealsByDiet(coverByDiet));
        MealReport sundayLunchReport = new MealReport(new Meal(SUNDAY, MealTime.LUNCH), new NumberOfMealsByDiet(coverByDiet));
        return new ArrayList<>(Arrays.asList(thursdayDinnerReport, fridayLunchReport, fridayDinnerReport, saturdayLunchReport, saturdayDinnerReport, sundayLunchReport));
    }


    @Test
    public void should_give_report_for_one_normal_meal() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 1);
        coverByDiet.put(MealType.OMNIVORE, 1);
        coverByDiet.put(MealType.VEGETARIAN, 1);
        Meal fridayLunch = new Meal(FRIDAY, MealTime.LUNCH);
        MealReport fridayLunchReport = new MealReport(fridayLunch, new NumberOfMealsByDiet(coverByDiet));
        //Act
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        MealReport report = socrates.giveMealReport(fridayLunch);
        //Assert
        assertThat(report).isEqualTo(fridayLunchReport);
    }

    @Test
    public void should_give_report_for_thursday_diner() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 1);
        coverByDiet.put(MealType.OMNIVORE, 0);
        coverByDiet.put(MealType.VEGETARIAN, 1);
        coverByDiet.put(MealType.COLDMEAL, 1);
        Meal thursdayDinner = new Meal(THURSDAY, MealTime.DINNER);
        MealReport thursdayDinnerReport = new MealReport(thursdayDinner, new NumberOfMealsByDiet(coverByDiet));
        //Act
        StayPeriod latePeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, latePeriod, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        MealReport report = socrates.giveMealReport(thursdayDinner);
        //Assert
        assertThat(report).isEqualTo(thursdayDinnerReport);
    }

    @Test
    public void should_give_report_for_friday_diner() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 1);
        coverByDiet.put(MealType.OMNIVORE, 1);
        coverByDiet.put(MealType.VEGETARIAN, 1);
        coverByDiet.put(MealType.COLDMEAL, 0);
        Meal thursdayDinner = new Meal(FRIDAY, MealTime.DINNER);
        MealReport fridayLunchReport = new MealReport(thursdayDinner, new NumberOfMealsByDiet(coverByDiet));
        //Act
        StayPeriod latePeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, latePeriod, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        MealReport report = socrates.giveMealReport(thursdayDinner);
        //Assert
        assertThat(report).isEqualTo(fridayLunchReport);
    }

    @Test
    public void should_give_report_for_thursday_diner_with_one_participant_arriving_friday() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 1);
        coverByDiet.put(MealType.OMNIVORE, 0);
        coverByDiet.put(MealType.VEGETARIAN, 1);
        coverByDiet.put(MealType.COLDMEAL, 0);
        Meal thursdayDinner = new Meal(THURSDAY, MealTime.DINNER);
        MealReport thursdayDinnerReport = new MealReport(thursdayDinner, new NumberOfMealsByDiet(coverByDiet));
        //Act
        StayPeriod fridayToSunday = new StayPeriod(new CheckIn(SocratesDay.FRIDAY, Hour.valueOf(12)), earlyCheckOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, fridayToSunday, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        MealReport report = socrates.giveMealReport(thursdayDinner);
        //Assert
        assertThat(report).isEqualTo(thursdayDinnerReport);
    }

    @Test
    public void should_give_report_for_sunday_lunch_with_one_participant_leaving_saturday() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 1);
        coverByDiet.put(MealType.OMNIVORE, 0);
        coverByDiet.put(MealType.VEGETARIAN, 1);
        coverByDiet.put(MealType.COLDMEAL, 0);
        Meal sundayLunchMeal = new Meal(SocratesDay.SUNDAY, MealTime.LUNCH);
        MealReport sundayLunchReport = new MealReport(sundayLunchMeal, new NumberOfMealsByDiet(coverByDiet));
        //Act
        StayPeriod thursdayToSaturday = new StayPeriod(lateCheckIn, new CheckOut(SocratesDay.SATURDAY, Hour.valueOf(18)));
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, thursdayToSaturday, "thomas@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.PESCATARIAN)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        MealReport report = socrates.giveMealReport(sundayLunchMeal);
        //Assert
        assertThat(report).isEqualTo(sundayLunchReport);
    }

    @Test
    public void should_give_report_for_sunday_lunch_with_one_participant_arriving_thursday_23_and_leaving_saturday() {
        // Arrange
        fillEnumMap();
        coverByDiet.put(MealType.PESCATARIAN, 0);
        coverByDiet.put(MealType.OMNIVORE, 0);
        coverByDiet.put(MealType.VEGETARIAN, 0);
        coverByDiet.put(MealType.COLDMEAL, 1);
        Meal sundayLunchMeal = new Meal(SocratesDay.THURSDAY, MealTime.DINNER);
        MealReport thursdayDinnerReport = new MealReport(sundayLunchMeal, new NumberOfMealsByDiet(coverByDiet));
        //Act
        StayPeriod thursdayToSaturday = new StayPeriod(lateCheckIn, new CheckOut(SocratesDay.SATURDAY, Hour.valueOf(18)));
        List<Participant> participants = Collections.singletonList(
                new Participant(RoomChoice.SINGLE_ROOM, thursdayToSaturday, "thomas@email.fr", MealType.OMNIVORE));
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        MealReport report = socrates.giveMealReport(sundayLunchMeal);
        //Assert
        assertThat(report).isEqualTo(thursdayDinnerReport);
    }
}
