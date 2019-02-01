package com.adaptionsoft.games.uglytrivia;

public class Place {
    private final int number;

    private Place(int number) {
        this.number = number;
    }

    public static Place createPlace(int number) {
        return new Place(number);
    }

    public int getNumber() {
        return number;
    }
}
