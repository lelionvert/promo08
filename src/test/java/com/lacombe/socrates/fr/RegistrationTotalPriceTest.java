package com.lacombe.socrates.fr;

import com.lacombe.socrates.fr.domain.Accomodation;
import com.lacombe.socrates.fr.domain.Price;
import com.lacombe.socrates.fr.domain.Registration;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(JUnitParamsRunner.class)
public class RegistrationTotalPriceTest {

    @Test
    @Parameters({"NO_ACCOMODATION, 240", "TRIPLE, 410", "DOUBLE,510", "SIMPLE,610"})
    public void given_a_registration_for_all_days_should_return_accomodation_price(Accomodation accomodation, int price) {
        Registration registration = new Registration(accomodation);
        Price totalPrice = registration.getTotalprice();
        assertThat(totalPrice).isEqualTo(Price.of(price));

    }


}
