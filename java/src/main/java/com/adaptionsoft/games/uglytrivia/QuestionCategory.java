package com.adaptionsoft.games.uglytrivia;

public enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String value;

    QuestionCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
