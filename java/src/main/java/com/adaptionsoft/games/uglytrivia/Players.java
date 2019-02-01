package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList<String> players;

    public Players() {
        players = new ArrayList();
    }

    void addNewPlayer(String playerName) {
        players.add(playerName);
    }

    public int howManyPlayers() {
        return players.size();
    }

    String getPlayer(int index) {
        return players.get(index);
    }
}