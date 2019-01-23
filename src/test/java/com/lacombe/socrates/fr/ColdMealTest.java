package com.lacombe.socrates.fr;

import com.lacombe.socrates.fr.domain.Checkin;
import com.lacombe.socrates.fr.domain.CheckinRepository;
import org.junit.Test;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static org.assertj.core.api.Assertions.assertThat;


public class ColdMealTest {

    @Test
    public void given_no_checkin_should_have_no_cold_meals() {
        CheckinRepository checkinRepository = new CheckinRepository();
        int numberOfMeals = checkinRepository.getColdMeals();
        assertThat(numberOfMeals).isEqualTo(0);
    }

    @Test
    public void given_a_checking_on_thursday_should_give_1_cold_meal() {
        Checkin checkinOnThursdayEvening = new Checkin(THURSDAY);
        CheckinRepository checkinRepository = new CheckinRepository(checkinOnThursdayEvening);
        int numberOfMeals = checkinRepository.getColdMeals();
        assertThat(numberOfMeals).isEqualTo(1);
    }

    @Test
    public void given_a_checking_on_thursday_and_on_another_day_should_give_1_cold_meal() {
        Checkin checkinOnThursdayEvening = new Checkin(THURSDAY);
        Checkin checkinOnFriday = new Checkin(FRIDAY);
        CheckinRepository checkinRepository = new CheckinRepository(checkinOnThursdayEvening, checkinOnFriday);
        int numberOfMeals = checkinRepository.getColdMeals();
        assertThat(numberOfMeals).isEqualTo(1);
    }
}
