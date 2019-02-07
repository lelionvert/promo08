package fr.lcdlv.promo8.socrates.domain;

public class BillingMealImpl implements BillingMeal {

    private static final int MAX_NUMBER_OF_MEAL = 6;

    private final CheckIn checkInLimit;
    private final CheckOut checkOutLimit;
    private final Price unitPriceByMeal;

    BillingMealImpl(CheckIn checkInLimit, CheckOut checkOutLimit, Price unitPriceByMeal) {
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
