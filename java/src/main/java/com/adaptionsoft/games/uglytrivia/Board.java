package com.adaptionsoft.games.uglytrivia;

public class Board {
    private int[] places = new int[6];

    public Board() {
    }

    void initPlace(int index) {
        places[index] = 0;
    }

    int getPlace(int currentPlayer) {
        return places[currentPlayer];
    }

    void moveCurrentPlayer(int roll, int indexPlayer) {
        places[indexPlayer] = getPlace(indexPlayer) + roll;
        if (getPlace(indexPlayer) > 11) places[indexPlayer] = getPlace(indexPlayer) - 12;
    }
}