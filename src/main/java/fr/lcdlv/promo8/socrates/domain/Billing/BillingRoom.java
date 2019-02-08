package fr.lcdlv.promo8.socrates.domain.Billing;

import fr.lcdlv.promo8.socrates.domain.Price;
import fr.lcdlv.promo8.socrates.domain.RoomChoice;

public interface BillingRoom {
    Price giveRoomPrice(RoomChoice roomChoice);
}
