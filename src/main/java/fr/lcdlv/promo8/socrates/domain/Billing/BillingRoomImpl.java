package fr.lcdlv.promo8.socrates.domain.Billing;

import fr.lcdlv.promo8.socrates.domain.Price;
import fr.lcdlv.promo8.socrates.domain.RoomCatalog;
import fr.lcdlv.promo8.socrates.domain.RoomChoice;

public class BillingRoomImpl implements BillingRoom {
    private final RoomCatalog roomCatalog;

    public BillingRoomImpl(RoomCatalog roomCatalog) {
        this.roomCatalog = roomCatalog;
    }

    @Override
    public Price giveRoomPrice(RoomChoice roomChoice) {
        return roomCatalog.getPrice(roomChoice);
    }

}
