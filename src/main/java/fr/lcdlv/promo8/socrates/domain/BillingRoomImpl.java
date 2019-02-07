package fr.lcdlv.promo8.socrates.domain;

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
