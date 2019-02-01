package com.adaptionsoft.games.uglytrivia;

public class Player {

    private final String name;
    private int coins;
    private boolean inPenaltyBox;

    private Player(String name) {
        this.name = name;
        coins = 0;
        inPenaltyBox = false;
    }

    static Player valueOf(String name) {
        return new Player(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoin() {
        this.coins++;
    }

    public void putInPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }


    boolean hasCoins(int numberOfCoins) {
        return getCoins() == numberOfCoins;
    }
}
