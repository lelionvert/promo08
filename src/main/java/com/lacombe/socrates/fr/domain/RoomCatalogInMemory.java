package com.lacombe.socrates.fr.domain;

import java.util.HashMap;
import java.util.Map;

public class RoomCatalogInMemory implements RoomCatalog {
    private final Map<RoomChoice, Price> prices;

    public RoomCatalogInMemory() {
        prices = new HashMap<>();
        prices.put(RoomChoice.SINGLE, Price.of(370));
        prices.put(RoomChoice.NO_ACCOMMODATION, Price.of(0));
        prices.put(RoomChoice.DOUBLE, Price.of(270));
        prices.put(RoomChoice.TRIPLE, Price.of(170));

    }

    @Override
    public Price getPrice(RoomChoice choice) {
        return prices.get(choice);
    }
}
