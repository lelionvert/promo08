package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.lacombe.socrates.fr.domain.CookService.DINNER;
import static com.lacombe.socrates.fr.domain.CookService.LUNCH;
import static com.lacombe.socrates.fr.domain.Diet.VEGETARIAN;
import static com.lacombe.socrates.fr.domain.RoomChoice.NO_ACCOMMODATION;
import static java.time.DayOfWeek.*;
import static java.time.LocalTime.of;
import static org.assertj.core.api.Assertions.assertThat;

public class CountingCoversByDietAcceptanceTest {

    private Socrates socrates;


    @Test
    void given_a_participant_with_vegeterian_diet_should_return_a_cover_vegetarian_by_meal() {
        StayPeriod stayPeriod = StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(20, 00)))
                .to(new Checkout(SUNDAY, of(23, 00))).build();

        List<Participant> participants = Arrays.asList(new Participant(NO_ACCOMMODATION, stayPeriod, new Mail("vegetarian@gmail.fr"), VEGETARIAN));
        ParticipantRegister participantRegister = new ParticipantRegisterInMemory(participants);

        socrates = new Socrates(participantRegister, DayOfWeek.THURSDAY, LocalTime.of(21, 00));

        CountCoversReport result = socrates.countCoversReport();
        CountCoversReport countCoversReport = new CountCoversReport(new MealCoverReport(new Meal(DayOfWeek.THURSDAY, DINNER), Diet.VEGETARIAN, 1),
                new MealCoverReport(new Meal(FRIDAY, LUNCH), Diet.VEGETARIAN, 1),
                new MealCoverReport(new Meal(FRIDAY, DINNER), Diet.VEGETARIAN, 1),
                new MealCoverReport(new Meal(SATURDAY, LUNCH), Diet.VEGETARIAN, 1),
                new MealCoverReport(new Meal(SATURDAY, DINNER), Diet.VEGETARIAN, 1),
                new MealCoverReport(new Meal(SUNDAY, LUNCH), Diet.VEGETARIAN, 1));
        assertThat(result).isEqualTo(countCoversReport);
    }
}
