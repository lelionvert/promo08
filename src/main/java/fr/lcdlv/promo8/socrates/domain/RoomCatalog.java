package fr.lcdlv.promo8.socrates.domain;

import java.util.EnumMap;

public class RoomCatalog {
    private EnumMap<RoomChoice, Price> priceByRoomChoice;

    public RoomCatalog() {
        priceByRoomChoice = new EnumMap<RoomChoice, Price>(RoomChoice.class);
        priceByRoomChoice.put(RoomChoice.SINGLE_ROOM, Price.valueOf(370));
        priceByRoomChoice.put(RoomChoice.DOUBLE_ROOM, Price.valueOf(270));
        priceByRoomChoice.put(RoomChoice.TRIPLE_ROOM, Price.valueOf(170));
        priceByRoomChoice.put(RoomChoice.NO_ACCOMODATION, Price.valueOf(0));
    }


    Price getPrice(RoomChoice roomChoice) {
        return priceByRoomChoice.get(roomChoice);
    }
}