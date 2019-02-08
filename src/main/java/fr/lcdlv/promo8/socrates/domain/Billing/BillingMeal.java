package fr.lcdlv.promo8.socrates.domain.Billing;

import fr.lcdlv.promo8.socrates.domain.Price;
import fr.lcdlv.promo8.socrates.domain.StayPeriod;

public interface BillingMeal {
    Price calculateMeals(StayPeriod participant);
}
