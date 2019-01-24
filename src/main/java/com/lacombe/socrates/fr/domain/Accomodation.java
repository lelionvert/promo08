package com.lacombe.socrates.fr.domain;

public enum Accomodation {
    SIMPLE(Price.of(610)),
    DOUBLE(Price.of(510)),
    TRIPLE(Price.of(410)),
    NO_ACCOMODATION(Price.of(240));

    private final Price price;

    private Accomodation(Price price) {
        this.price = price;
    }


    public Price minus(Price other) {
        return this.price.minus(other);
    }
}
