package fr.lcdlv.promo8.socrates.domain;

import fr.lcdlv.promo8.socrates.domain.Billing.BillingRoom;

public class BillingRoomMock implements BillingRoom {

    @Override
    public Price giveRoomPrice(RoomChoice roomChoice) {
        if (roomChoice.equals(RoomChoice.DOUBLE_ROOM)) {
            return Price.valueOf(270);
        }
        return Price.valueOf(370);
    }
}
