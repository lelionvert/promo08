package com.adaptionsoft.games.uglytrivia;

public class Player {

    private final String name;
    private int coins;
    private boolean inPenaltyBox;
    private Place place;

    private Player(String name) {
        this.name = name;
        coins = 0;
        inPenaltyBox = false;
        place = Place.createPlace(0);
    }

    static Player valueOf(String name) {
        return new Player(name);
    }

    @Override
    public String toString() {
        return name;
    }

    int getCoins() {
        return coins;
    }

    void addCoin() {
        this.coins++;
    }

    void putInPenaltyBox() {
        this.inPenaltyBox = true;
    }

    boolean isInPenaltyBox() {
        return inPenaltyBox;
    }


    boolean hasCoins(int numberOfCoins) {
        return getCoins() == numberOfCoins;
    }

    void changePlace(int placeNumber) {
        place = Place.createPlace(placeNumber);
    }

    int getPlaceNumber() {
        return place.getNumber();
    }
}
