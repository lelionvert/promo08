package com.lacombe.socrates.fr.domain;

public class Registration {
    private final Accomodation accomodationChoice;


    public Registration(Accomodation accomodationChoice) {
        this.accomodationChoice = accomodationChoice;
    }

    public Price getTotalprice() {
        return accomodationChoice.getPrice();
    }
}
