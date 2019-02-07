package fr.lcdlv.promo8.socrates.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingRoomTest {
    @Test
    public void given_a_single_room_should_return_a_price_of_370() {
        Price expected = Price.valueOf(370);
        BillingRoomMock billingRoom = new BillingRoomMock();
        Price roomPrice = billingRoom.giveRoomPrice(RoomChoice.SINGLE_ROOM);
        assertThat(expected).isEqualTo(roomPrice);
    }

    @Test
    public void given_a_double_room_should_return_a_price_of_270() {
        Price expected = Price.valueOf(270);
        BillingRoomImpl billingRoom = new BillingRoomImpl(new RoomCatalog());
        Price roomPrice = billingRoom.giveRoomPrice(RoomChoice.DOUBLE_ROOM);
        assertThat(expected).isEqualTo(roomPrice);
    }
}
