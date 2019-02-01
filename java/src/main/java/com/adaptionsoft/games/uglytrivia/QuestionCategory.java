package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.function.Predicate;

public enum QuestionCategory {
    POP("Pop", (place) -> (place == 0 || place == 4 || place == 8)),
    SCIENCE("Science", (place) -> (place == 1 || place == 5 || place == 9)),
    SPORTS("Sports", (place) -> (place == 2 || place == 6 || place == 10)),
    ROCK("Rock", (place) -> true);

    private final String value;
    private final Predicate<Integer> isEligible;

    QuestionCategory(String value, Predicate<Integer> isEligible) {
        this.value = value;
        this.isEligible = isEligible;
    }

    static QuestionCategory fromPlace(int place) {

        return Arrays.stream(QuestionCategory.values()).filter((
                questionCategory -> questionCategory.isEligible.test(place))).findFirst().orElseGet(() -> ROCK);
    }

    public String getValue() {
        return value;
    }


}
