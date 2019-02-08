package fr.lcdlv.promo8.socrates.domain;

import java.util.Objects;

public class Price {
    private final int value;

    private Price(int value) {
        this.value = value;
    }

    public static Price valueOf(int value) {
        return new Price(value);
    }

    public Price multiplyBy(int numberOfMeal) {
        return valueOf(numberOfMeal * value);
    }

    private int getValue() {
        return value;
    }

    public Price add(Price otherPrice) {
        return valueOf(otherPrice.getValue() + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}

