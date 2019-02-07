package fr.lcdlv.promo8.socrates.domain;

public class BillingMealImpl implements BillingMeal {

    public static final int MAX_NUMBER_OF_MEAL = 6;

    private CheckIn checkInLimit;
    private CheckOut checkOutLimit;
    private Price unitPriceByMeal;

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
