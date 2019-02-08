package fr.lcdlv.promo8.socrates.domain.Billing;

import fr.lcdlv.promo8.socrates.domain.Participant;
import fr.lcdlv.promo8.socrates.domain.Price;

public class BillingService {
    private final BillingMeal billingMeal;
    private final BillingRoom billingRoom;

    public BillingService(BillingMeal billingMeal, BillingRoom billingRoom) {

        this.billingMeal = billingMeal;
        this.billingRoom = billingRoom;
    }

    public Price total(Participant participant) {
        Price mealPrice = billingMeal.calculateMeals(participant.getStayPeriod());
        Price roomPrice = billingRoom.giveRoomPrice(participant.getRoomChoice());
        return mealPrice.add(roomPrice);
    }
}
