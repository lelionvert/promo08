package fr.lcdlv.promo8.socrates.domain;

public class BillingMealMock implements BillingMeal {

    @Override
    public Price calculateMeals(StayPeriod participant) {
        return Price.valueOf(40 * 6);
    }
}
