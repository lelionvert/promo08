package com.adaptionsoft.games.uglytrivia;

public class ConsolePrinter implements Printer {
    @Override
    public void displayLine(String line) {
        System.out.println(line);
    }
}
