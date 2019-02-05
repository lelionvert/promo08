package com.lacombe.socrates.fr.domain;

import java.util.Objects;

public class Price {
    private int value;

    private Price(int value) {
        this.value = value;
    }

    public static Price of(int price) {
        return new Price(price);
    }

    public Price minus(Price other) {
        return Price.of(value - other.value);
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
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

    public Price add(Price other) {
        return Price.of(value + other.value);
    }

    public Price multiplyBy(int scalar) {
        return Price.of(value * scalar);
    }
}
