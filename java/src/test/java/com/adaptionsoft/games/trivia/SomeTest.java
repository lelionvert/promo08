package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SomeTest {

	@Test
	public void given_a_player_game_should_have_one_player() {
		Game game = new Game(new ConsolePrinter());
		boolean addedPlayer = game.addPlayer("Tom");
		assertEquals(1, game.howManyPlayers());
	}

	@Test
	public void name() {
		StringPrinter stringPrinter = new StringPrinter();
		GameRunner.runGame(new RandomMock(1), stringPrinter);
		String output = stringPrinter.getOutput();
		String expected = "Chet was added\n" +
				"They are player number 1\n" +
				"Pat was added\n" +
				"They are player number 2\n" +
				"Sue was added\n" +
				"They are player number 3\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 2\n" +
				"The category is Sports\n" +
				"Sports Question 0\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 1 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 2\n" +
				"Pat's new location is 2\n" +
				"The category is Sports\n" +
				"Sports Question 1\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 1 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 2\n" +
				"Sue's new location is 2\n" +
				"The category is Sports\n" +
				"Sports Question 2\n" +
				"Answer was corrent!!!!\n" +
				"Sue now has 1 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 4\n" +
				"The category is Pop\n" +
				"Pop Question 0\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 2 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 2\n" +
				"Pat's new location is 4\n" +
				"The category is Pop\n" +
				"Pop Question 1\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 2 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 2\n" +
				"Sue's new location is 4\n" +
				"The category is Pop\n" +
				"Pop Question 2\n" +
				"Answer was corrent!!!!\n" +
				"Sue now has 2 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 6\n" +
				"The category is Sports\n" +
				"Sports Question 3\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 3 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 2\n" +
				"Pat's new location is 6\n" +
				"The category is Sports\n" +
				"Sports Question 4\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 3 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 2\n" +
				"Sue's new location is 6\n" +
				"The category is Sports\n" +
				"Sports Question 5\n" +
				"Answer was corrent!!!!\n" +
				"Sue now has 3 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 8\n" +
				"The category is Pop\n" +
				"Pop Question 3\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 4 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 2\n" +
				"Pat's new location is 8\n" +
				"The category is Pop\n" +
				"Pop Question 4\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 4 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 2\n" +
				"Sue's new location is 8\n" +
				"The category is Pop\n" +
				"Pop Question 5\n" +
				"Answer was corrent!!!!\n" +
				"Sue now has 4 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 10\n" +
				"The category is Sports\n" +
				"Sports Question 6\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 5 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 2\n" +
				"Pat's new location is 10\n" +
				"The category is Sports\n" +
				"Sports Question 7\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 5 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 2\n" +
				"Sue's new location is 10\n" +
				"The category is Sports\n" +
				"Sports Question 8\n" +
				"Answer was corrent!!!!\n" +
				"Sue now has 5 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 0\n" +
				"The category is Pop\n" +
				"Pop Question 6\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 6 Gold Coins.\n";
		assertEquals(expected, output);
	}

	public class RandomMock extends Random {
		private final int rollValue;

		public RandomMock(int rollValue) {

			this.rollValue = rollValue;
		}

		@Override
		public int nextInt(int bound) {
			return rollValue;
		}
	}
}

