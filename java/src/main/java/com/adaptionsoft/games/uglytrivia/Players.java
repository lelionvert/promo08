package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList<Player> players;
    private int currentPlayerIndex = 0;

    Players() {
        players = new ArrayList<>();
    }

    void addNewPlayer(Player playerName) {
        players.add(playerName);
    }

    public int howManyPlayers() {
        return players.size();
    }

    boolean isInPenaltyBox() {
        return players.get(this.currentPlayerIndex).isInPenaltyBox();
    }

    void putPlayerInPenaltyBox() {
        players.get(this.currentPlayerIndex).putInPenaltyBox();
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == howManyPlayers()) currentPlayerIndex = 0;
    }

    boolean currentPlayerHasCoins(int numberOfCoins) {
        return getCurrentPlayer().hasCoins(numberOfCoins);
    }

    void moveCurrentPlayerTo(int placeNumber) {
        getCurrentPlayer().changePlace(placeNumber);
    }

    int getCurrentPlayerPlaceNumber() {
        return getCurrentPlayer().getPlaceNumber();
    }

    void addCoinToCurrentPlayer() {
        getCurrentPlayer().addCoin();
    }
}