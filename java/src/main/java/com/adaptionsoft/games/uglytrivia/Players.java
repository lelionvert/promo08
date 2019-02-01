package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList<Player> players;
    public int currentPlayerIndex = 0;

    public Players() {
        players = new ArrayList();
    }

    void addNewPlayer(Player playerName) {
        players.add(playerName);
    }

    public int howManyPlayers() {
        return players.size();
    }

    boolean isInPenaltyBox(int currentPlayerIndex) {
        return players.get(currentPlayerIndex).isInPenaltyBox();
    }

    void putPlayerInPenaltyBox(int currentPlayerIndex) {
        players.get(currentPlayerIndex).putInPenaltyBox();
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == howManyPlayers()) currentPlayerIndex = 0;

    }
}