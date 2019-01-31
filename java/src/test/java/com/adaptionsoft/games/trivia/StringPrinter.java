package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Printer;

public class StringPrinter implements Printer {
    private final StringBuilder builder = new StringBuilder();

    public String getOutput() {
        return builder.toString();
    }

    @Override
    public void displayLine(String line) {
        builder.append(line).append('\n');
    }


}
