package com.adaptionsoft.games.uglytrivia;

public class Player {

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    static Player valueOf(String name) {
        return new Player(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
