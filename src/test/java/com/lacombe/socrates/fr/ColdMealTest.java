package com.lacombe.socrates.fr;

import com.lacombe.socrates.fr.domain.CheckinRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ColdMealTest {
    @Test
    public void given_no_checkin_should_have_no_cold_meals() {
        CheckinRepository checkinRepository = new CheckinRepository();
        int numberOfMeals = checkinRepository.getColdMeals();
        assertThat(numberOfMeals).isEqualTo(0);
    }
}
