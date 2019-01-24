package com.lacombe.socrates.fr;

import com.lacombe.socrates.fr.domain.CheckIn;
import com.lacombe.socrates.fr.domain.CheckIns;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalTime;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ColdMealTest {


    private static final LocalTime BEGIN_EVENING = LocalTime.of(21, 0);

    @Test
    public void given_no_checkin_should_have_no_cold_meals() {
        CheckIns checkins = new CheckIns();
        long numberOfMeals = checkins.getNumberOfCheckInsFor(THURSDAY, BEGIN_EVENING);
        assertThat(numberOfMeals).isEqualTo(0);
    }


    @Test
    public void given_a_checking_on_thursday_and_on_another_day_should_give_1_cold_meal() {
        CheckIn checkInOnThursdayEvening = new CheckIn(THURSDAY, LocalTime.of(21, 0));
        CheckIn checkInOnFriday = new CheckIn(FRIDAY, LocalTime.of(21, 0));
        CheckIns checkins = new CheckIns(checkInOnThursdayEvening, checkInOnFriday);
        long numberOfMeals = checkins.getNumberOfCheckInsFor(THURSDAY, BEGIN_EVENING);
        assertThat(numberOfMeals).isEqualTo(1);
    }

    @Test
    @Parameters({"21,0", "22,15", "23,30", "23,59"})
    public void given_a_checking_on_thursday_evening_should_give_1_cold_meal(int hour, int minutes) {
        CheckIn checkInOnThursdayEvening = new CheckIn(THURSDAY, LocalTime.of(hour, minutes));
        CheckIns checkins = new CheckIns(checkInOnThursdayEvening);
        long numberOfMeals = checkins.getNumberOfCheckInsFor(THURSDAY, BEGIN_EVENING);
        assertThat(numberOfMeals).isEqualTo(1);
    }

    @Test
    public void given_a_checking_before_thursday_evening_should_give_0_cold_meal() {
        CheckIn checkInOnThursdayEvening = new CheckIn(THURSDAY, LocalTime.of(19, 0));
        CheckIns checkins = new CheckIns(checkInOnThursdayEvening);
        long numberOfMeals = checkins.getNumberOfCheckInsFor(THURSDAY, BEGIN_EVENING);
        assertThat(numberOfMeals).isEqualTo(0);
    }


    @Test
    public void given_a_checking_after_the_end_of_thursday_evening_should_give_0_cold_meal() {
        CheckIn checkInOnThursdayEvening = new CheckIn(FRIDAY, LocalTime.of(0, 0));
        CheckIns checkins = new CheckIns(checkInOnThursdayEvening);
        long numberOfMeals = checkins.getNumberOfCheckInsFor(THURSDAY, BEGIN_EVENING);
        assertThat(numberOfMeals).isEqualTo(0);
    }
}
