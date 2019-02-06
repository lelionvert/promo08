package com.lacombe.socrates.fr.domain;

import com.lacombe.socrates.fr.Checkin;
import com.lacombe.socrates.fr.Checkout;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.THURSDAY;
import static java.time.LocalTime.of;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ColdMealTest {

    private ColdMealProvider coldMealProvider;


    @Before
    public void setUp() {
        ParticipantRegister participantRegister = new ParticipantRegisterInMemory(Arrays.asList(
                new Participant(RoomChoice.NO_ACCOMMODATION, StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(22, 00))).to(new Checkout(DayOfWeek.SUNDAY, of(12, 00))).build(), new Mail("toto@gmail.com")),
                new Participant(RoomChoice.NO_ACCOMMODATION, StayPeriod.StayPeriodBuilder.from(new Checkin(THURSDAY, of(22, 00))).to(new Checkout(DayOfWeek.SUNDAY, of(12, 00))).build(), new Mail("blop@lacombe.fr"))
        ));
        coldMealProvider = new ColdMealProvider(participantRegister, THURSDAY, of(21, 00));

    }

    @Test
    public void given_participants_with_arrival_after_limit_should_return_a_listing_with_their_mails() {
        ColdMealListing result = coldMealProvider.determineColdMealslisting();
        List<Mail> mails = Arrays.asList(new Mail("toto@gmail.com"), new Mail("blop@lacombe.fr"));
        ColdMealListing coldMealListing = new ColdMealListing(mails);
        assertThat(result).isEqualTo(coldMealListing);
    }


}
