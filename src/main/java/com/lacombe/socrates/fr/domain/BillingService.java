package com.lacombe.socrates.fr.domain;

public class BillingService {
    private final RoomCatalog roomCatalog;
    private final MealBillingService mealBillingService;

    public BillingService(RoomCatalog roomCatalog, MealBillingService mealBillingService) {
        this.roomCatalog = roomCatalog;
        this.mealBillingService = mealBillingService;
    }

    public Price calculateBill(Participant participant) {

        Price roomPrice = calculateRoomPrice(participant.getRoomChoice());
        Price mealsPrice = mealBillingService.calculatePrice(participant);
        return roomPrice.add(mealsPrice);
    }

    private Price calculateRoomPrice(RoomChoice single) {
        return roomCatalog.getPrice(single);
    }
}
