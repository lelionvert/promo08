package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList<Player> players;


    public Players() {
        players = new ArrayList();
    }

    void addNewPlayer(Player playerName) {
        players.add(playerName);
    }

    public int howManyPlayers() {
        return players.size();
    }

    Player getPlayer(int index) {
        return players.get(index);
    }

    boolean isInPenaltyBox(int currentPlayerIndex) {
        return getPlayer(currentPlayerIndex).isInPenaltyBox();
    }

    void putPlayerInPenaltyBox(int currentPlayerIndex) {
        getPlayer(currentPlayerIndex).putInPenaltyBox();
    }
}