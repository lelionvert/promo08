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

    static QuestionCategory fromPlace(int place) {
        QuestionCategory currentCategory = ROCK;
        if (place % 4 == 0) currentCategory = POP;
        if (place % 4 == 1) currentCategory = SCIENCE;
        if (place % 4 == 2) currentCategory = SPORTS;
        return currentCategory;
    }

    public String getValue() {
        return value;
    }
}
