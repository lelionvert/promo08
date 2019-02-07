package fr.lcdlv.promo8.socrates.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
    private static final StayPeriod stayPeriod;
    private static final CheckIn lateCheckIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(23));
    private static final CheckOut earlyCheckOut = new CheckOut(SocratesDay.SATURDAY, Hour.valueOf(21));
    private static final CheckIn checkInLimit = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
    private static final CheckOut checkOutLimit = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11));

    static {
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(18));
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(15));
        stayPeriod = new StayPeriod(checkIn, checkOut);
    }

    @Test
    public void a_participant_with_a_single_room_for_a_full_week_end_should_have_a_price_of_610() {
        // Arrange
        Participant participant = new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "email@email.fr", MealType.VEGETARIAN);
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        BillingRoomMock billingRoom = new BillingRoomMock();
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price expectedPrice = Price.valueOf(610);
        //  Act
        Price total = billingService.total(participant);
        // Assert
        assertThat(expectedPrice).isEqualTo(total);
    }

    @Test
    public void a_participant_with_a_double_room_for_a_full_week_end_should_have_a_price_of_510() {
        // Arrange
        Participant participant = new Participant(RoomChoice.DOUBLE_ROOM, stayPeriod, "email@email.fr", MealType.VEGETARIAN);
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        BillingRoomImpl billingRoom = new BillingRoomImpl(new RoomCatalog());
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price expectedPrice = Price.valueOf(510);
        //  Act
        Price total = billingService.total(participant);
        // Assert
        assertThat(expectedPrice).isEqualTo(total);
    }


    @Test
    public void a_participant_with_a_single_room_when_arriving_friday_should_have_a_price_of_570() {
        // Arrange
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(15));
        StayPeriod stayPeriod = new StayPeriod(lateCheckIn, checkOut);
        Participant participant = new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "email@email.fr", MealType.VEGETARIAN);
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        BillingRoomImpl billingRoom = new BillingRoomImpl(new RoomCatalog());
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price expectedPrice = Price.valueOf(570);
        //  Act
        Price total = billingService.total(participant);
        // Assert
        assertThat(expectedPrice).isEqualTo(total);
    }

    @Test
    public void a_participant_with_a_single_room_when_leaving_saturday_should_have_a_price_of_570() {
        // Arrange
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(18));
        StayPeriod stayPeriod = new StayPeriod(checkIn, earlyCheckOut);
        Participant participant = new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "email@email.fr", MealType.VEGETARIAN);
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        BillingRoomImpl billingRoom = new BillingRoomImpl(new RoomCatalog());
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price expectedPrice = Price.valueOf(570);
        //  Act
        Price total = billingService.total(participant);
        // Assert
        assertThat(expectedPrice).isEqualTo(total);
    }

    @Test
    public void a_participant_with_a_single_room_when_arriving_friday_and_leaving_saturday_should_have_a_price_of_530() {
        // Arrange
        StayPeriod stayPeriod = new StayPeriod(lateCheckIn, earlyCheckOut);
        Participant participant = new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "email@email.fr", MealType.VEGETARIAN);
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        BillingRoomImpl billingRoom = new BillingRoomImpl(new RoomCatalog());
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price expectedPrice = Price.valueOf(530);
        //  Act
        Price total = billingService.total(participant);
        // Assert
        assertThat(expectedPrice).isEqualTo(total);
    }

    @Test
    public void given_participants_should_return_a_list_of_obly_2late_participants_emails() {
        StayPeriod stayPeriod_late = new StayPeriod(lateCheckIn, earlyCheckOut);
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(18));
        CheckOut checkOut = new CheckOut(SocratesDay.THURSDAY, Hour.valueOf(22));
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod_late, "thomas@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod_late, "stephen@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "marie@email.fr", MealType.VEGETARIAN)

        );
        List<String> expectedEmails = Arrays.asList("thomas@email.fr", "stephen@email.fr");
        Socrates socrates = new Socrates(participants, checkInLimit, new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)));
        ColdMealChecker coldMealChecker = new ColdMealChecker(expectedEmails);
        Assertions.assertThat(coldMealChecker).isEqualTo(socrates.giveColdMealsChecker());
    }

    @Test
    public void give_full_meal_report() {
        // Arrange
        List<Participant> participants = Arrays.asList(
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "steeve@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "bertillon@email.fr", MealType.VEGETARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "palpatine@email.fr", MealType.PESCATARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "andre@email.fr", MealType.PESCATARIAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "luke@email.fr", MealType.VEGAN),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "audrey@email.fr", MealType.VEGAN),
                new Participant(RoomChoice.SINGLE_ROOM, new StayPeriod(lateCheckIn, earlyCheckOut), "marie@email.fr", MealType.OMNIVORE),
                new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "julie@email.fr", MealType.OMNIVORE)
        );
        Socrates socrates = new Socrates(participants, checkInLimit, checkOutLimit);
        EnumMap<MealType, Integer> enumDietCount = new EnumMap<>(MealType.class);
        enumDietCount.put(MealType.VEGETARIAN, 2);
        enumDietCount.put(MealType.PESCATARIAN, 2);
        enumDietCount.put(MealType.OMNIVORE, 2);
        enumDietCount.put(MealType.VEGAN, 2);
        enumDietCount.put(MealType.COLDMEAL, 0);
        EnumMap<MealType, Integer> enumDietCountThursday = new EnumMap<>(MealType.class);
        enumDietCountThursday.put(MealType.VEGETARIAN, 2);
        enumDietCountThursday.put(MealType.PESCATARIAN, 2);
        enumDietCountThursday.put(MealType.VEGAN, 2);
        enumDietCountThursday.put(MealType.OMNIVORE, 1);
        enumDietCountThursday.put(MealType.COLDMEAL, 1);
        EnumMap<MealType, Integer> enumDietCountSunday = new EnumMap<>(enumDietCountThursday);
        enumDietCountSunday.put(MealType.COLDMEAL, 0);
        List<MealReport> expectedReport = new ArrayList<>();
        expectedReport.add(new MealReport(new Meal(SocratesDay.THURSDAY, MealTime.DINNER), new NumberOfMealsByDiet(enumDietCountThursday)));
        expectedReport.add(new MealReport(new Meal(SocratesDay.FRIDAY, MealTime.LUNCH), new NumberOfMealsByDiet(enumDietCount)));
        expectedReport.add(new MealReport(new Meal(SocratesDay.FRIDAY, MealTime.DINNER), new NumberOfMealsByDiet(enumDietCount)));
        expectedReport.add(new MealReport(new Meal(SocratesDay.SATURDAY, MealTime.LUNCH), new NumberOfMealsByDiet(enumDietCount)));
        expectedReport.add(new MealReport(new Meal(SocratesDay.SATURDAY, MealTime.DINNER), new NumberOfMealsByDiet(enumDietCount)));
        expectedReport.add(new MealReport(new Meal(SocratesDay.SUNDAY, MealTime.LUNCH), new NumberOfMealsByDiet(enumDietCountSunday)));
        Assertions.assertThat(expectedReport).isEqualTo(socrates.giveFullWeekMealsReport());
    }
}
