package fr.lcdlv.promo8.socrates.domain;

import fr.lcdlv.promo8.socrates.domain.Billing.BillingMeal;

public class BillingMealMock implements BillingMeal {

    @Override
    public Price calculateMeals(StayPeriod participant) {
        return Price.valueOf(40 * 6);
    }
}
