
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Printer;

import java.util.Random;


public class GameRunner {

	public static void main(String[] args) {
        Random rand = new Random();
        runGame(rand, new ConsolePrinter());
    }

    public static void runGame(Random rand, Printer printer) {
        Game aGame = new Game(printer);

		aGame.addPlayer("Chet");
		aGame.addPlayer("Pat");
		aGame.addPlayer("Sue");

        boolean notAWinner;
        do {

			aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}

        } while (notAWinner);
	}
}
