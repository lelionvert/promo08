package fr.lcdlv.promo8.socrates.domain.Billing;

import fr.lcdlv.promo8.socrates.domain.CheckIn;
import fr.lcdlv.promo8.socrates.domain.CheckOut;
import fr.lcdlv.promo8.socrates.domain.Price;
import fr.lcdlv.promo8.socrates.domain.StayPeriod;

public class BillingMealImpl implements BillingMeal {

    private static final int MAX_NUMBER_OF_MEAL = 6;

    private final CheckIn checkInLimit;
    private final CheckOut checkOutLimit;
    private final Price unitPriceByMeal;

    public BillingMealImpl(CheckIn checkInLimit, CheckOut checkOutLimit, Price unitPriceByMeal) {
        this.unitPriceByMeal = unitPriceByMeal;
        this.checkInLimit = checkInLimit;
        this.checkOutLimit = checkOutLimit;
    }

    @Override
    public Price calculateMeals(StayPeriod stayPeriod) {
        int numberOfMeal = MAX_NUMBER_OF_MEAL;
        if (stayPeriod.startAfter(checkInLimit))
            numberOfMeal--;
        if (stayPeriod.leaveBefore(checkOutLimit))
            numberOfMeal--;
        return unitPriceByMeal.multiplyBy(numberOfMeal);
    }

}
