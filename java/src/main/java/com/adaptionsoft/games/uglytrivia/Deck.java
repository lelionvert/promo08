package com.adaptionsoft.games.uglytrivia;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class Deck {
    private Map<QuestionCategory, LinkedList<String>> questions;

    Deck() {
        questions = new EnumMap<QuestionCategory, LinkedList<String>>(QuestionCategory.class);
        for (QuestionCategory category : QuestionCategory.values()) {
            initializeQuestions(category);
        }

    }

    private void initializeQuestions(QuestionCategory questionCategory) {
        questions.put(questionCategory, new LinkedList<String>());
        for (int i = 0; i < 50; i++) {
            questions.get(questionCategory).addLast(questionCategory.getValue() + " Question " + i);
        }
    }

    String drawQuestion(QuestionCategory questionCategory) {

        return questions.get(questionCategory).removeFirst();
    }
}