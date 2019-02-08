package fr.lcdlv.promo8.socrates.domain;

import fr.lcdlv.promo8.socrates.domain.Billing.BillingMealImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingMealTest {
    @Test
    public void should_give_a_price_of_240_for_a_full_conference() {

        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(14));
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(14));
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        assertThat(Price.valueOf(240)).isEqualTo(billingMeal.calculateMeals(stayPeriod));
    }

    @Test
    public void should_give_a_price_of_200_for_an_arrival_days_after_thursday() {
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        CheckIn checkIn = new CheckIn(SocratesDay.FRIDAY, Hour.valueOf(14));
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(14));
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        assertThat(Price.valueOf(200)).isEqualTo(billingMeal.calculateMeals(stayPeriod));
    }

    @Test
    public void should_give_a_price_of_200_for_an_arrival_on_thursday_hours_after_21() {
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21));
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(14));
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        assertThat(Price.valueOf(200)).isEqualTo(billingMeal.calculateMeals(stayPeriod));
    }

    @Test
    public void should_give_a_price_of_200_for_a_leaving_on_saturday() {
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(20));
        CheckOut checkOut = new CheckOut(SocratesDay.SATURDAY, Hour.valueOf(14));
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        assertThat(Price.valueOf(200)).isEqualTo(billingMeal.calculateMeals(stayPeriod));
    }

    @Test
    public void should_give_a_price_of_200_for_a_leaving_on_sunday_before_11() {
        BillingMealImpl billingMeal = new BillingMealImpl(new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(21)), new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11)), Price.valueOf(40));
        CheckIn checkIn = new CheckIn(SocratesDay.THURSDAY, Hour.valueOf(20));
        CheckOut checkOut = new CheckOut(SocratesDay.SUNDAY, Hour.valueOf(11));
        StayPeriod stayPeriod = new StayPeriod(checkIn, checkOut);
        assertThat(Price.valueOf(200)).isEqualTo(billingMeal.calculateMeals(stayPeriod));
    }
}
