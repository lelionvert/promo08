package fr.lcdlv.promo8.socrates.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingServiceTest {

    @Test
    public void should_return_a_price_of_610_when_given_a_single_room_for_full_conference() {
        CheckIn checkIn = null;
        CheckOut checkOut = null;
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        Participant participant = new Participant(RoomChoice.SINGLE_ROOM, stayPeriod, "email@email.fr", Diet.VEGETARIAN);
        BillingMealMock billingMeal = new BillingMealMock();
        BillingRoomMock billingRoom = new BillingRoomMock();
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price totalPrice = billingService.total(participant);
        assertThat(Price.valueOf(610)).isEqualTo(totalPrice);
    }

    @Test
    public void should_return_a_price_of_510_when_given_a_double_room_for_full_conference() {
        CheckIn checkIn = null;
        CheckOut checkOut = null;
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        Participant participant = new Participant(RoomChoice.DOUBLE_ROOM, stayPeriod, "email@email.fr", Diet.VEGETARIAN);
        BillingMealMock billingMeal = new BillingMealMock();
        BillingRoomMock billingRoom = new BillingRoomMock();
        BillingService billingService = new BillingService(billingMeal, billingRoom);
        Price totalPrice = billingService.total(participant);
        assertThat(Price.valueOf(510)).isEqualTo(totalPrice);
    }
}
