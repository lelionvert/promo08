package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.ConsolePrinter;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SomeTest {

	public static final String EXPECTED_WITH_ROLL_VALUE_OF_SIX =
			"Chet was added\n" +
					"They are player number 1\n" +
					"Pat was added\n" +
					"They are player number 2\n" +
					"Sue was added\n" +
					"They are player number 3\n" +
					"Chet is the current player\n" +
					"They have rolled a 4\n" +
					"Chet's new location is 4\n" +
					"The category is Pop\n" +
					"Pop Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 1 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 1\n" +
					"Pat's new location is 1\n" +
					"The category is Science\n" +
					"Science Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 1 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 3\n" +
					"Sue's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Sue now has 1 Gold Coins.\n" +
					"Chet is the current player\n" +
					"They have rolled a 5\n" +
					"Chet's new location is 9\n" +
					"The category is Science\n" +
					"Science Question 1\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 2 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 2\n" +
					"Pat's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 1\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 2 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 4\n" +
					"Sue's new location is 7\n" +
					"The category is Rock\n" +
					"Rock Question 2\n" +
					"Answer was corrent!!!!\n" +
					"Sue now has 2 Gold Coins.\n" +
					"Chet is the current player\n" +
					"They have rolled a 1\n" +
					"Chet's new location is 10\n" +
					"The category is Sports\n" +
					"Sports Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 3 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 3\n" +
					"Pat's new location is 6\n" +
					"The category is Sports\n" +
					"Sports Question 1\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 3 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 5\n" +
					"Sue's new location is 0\n" +
					"The category is Pop\n" +
					"Pop Question 1\n" +
					"Question was incorrectly answered\n" +
					"Sue was sent to the penalty box\n" +
					"Chet is the current player\n" +
					"They have rolled a 2\n" +
					"Chet's new location is 0\n" +
					"The category is Pop\n" +
					"Pop Question 2\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 4 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 4\n" +
					"Pat's new location is 10\n" +
					"The category is Sports\n" +
					"Sports Question 2\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 4 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 1\n" +
					"Sue is getting out of the penalty box\n" +
					"Sue's new location is 1\n" +
					"The category is Science\n" +
					"Science Question 2\n" +
					"Answer was correct!!!!\n" +
					"Sue now has 3 Gold Coins.\n" +
					"Chet is the current player\n" +
					"They have rolled a 3\n" +
					"Chet's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 3\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 5 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 5\n" +
					"Pat's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 4\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 5 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 2\n" +
					"Sue is not getting out of the penalty box\n" +
					"Chet is the current player\n" +
					"They have rolled a 4\n" +
					"Chet's new location is 7\n" +
					"The category is Rock\n" +
					"Rock Question 5\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 6 Gold Coins.\n";
	private static final String EXPECTED_WITH_ROLL_VALUE_OF_ONE =
			"Chet was added\n" +
					"They are player number 1\n" +
					"Pat was added\n" +
					"They are player number 2\n" +
					"Sue was added\n" +
					"They are player number 3\n" +
					"Chet is the current player\n" +
					"They have rolled a 3\n" +
					"Chet's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 1 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 5\n" +
					"Pat's new location is 5\n" +
					"The category is Science\n" +
					"Science Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 1 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 2\n" +
					"Sue's new location is 2\n" +
					"The category is Sports\n" +
					"Sports Question 0\n" +
					"Question was incorrectly answered\n" +
					"Sue was sent to the penalty box\n" +
					"Chet is the current player\n" +
					"They have rolled a 4\n" +
					"Chet's new location is 7\n" +
					"The category is Rock\n" +
					"Rock Question 1\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 2 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 1\n" +
					"Pat's new location is 6\n" +
					"The category is Sports\n" +
					"Sports Question 1\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 2 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 3\n" +
					"Sue is getting out of the penalty box\n" +
					"Sue's new location is 5\n" +
					"The category is Science\n" +
					"Science Question 1\n" +
					"Answer was correct!!!!\n" +
					"Sue now has 1 Gold Coins.\n" +
					"Chet is the current player\n" +
					"They have rolled a 5\n" +
					"Chet's new location is 0\n" +
					"The category is Pop\n" +
					"Pop Question 0\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 3 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 2\n" +
					"Pat's new location is 8\n" +
					"The category is Pop\n" +
					"Pop Question 1\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 3 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 4\n" +
					"Sue is not getting out of the penalty box\n" +
					"Chet is the current player\n" +
					"They have rolled a 1\n" +
					"Chet's new location is 1\n" +
					"The category is Science\n" +
					"Science Question 2\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 4 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 3\n" +
					"Pat's new location is 11\n" +
					"The category is Rock\n" +
					"Rock Question 2\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 4 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 5\n" +
					"Sue is getting out of the penalty box\n" +
					"Sue's new location is 10\n" +
					"The category is Sports\n" +
					"Sports Question 2\n" +
					"Question was incorrectly answered\n" +
					"Sue was sent to the penalty box\n" +
					"Chet is the current player\n" +
					"They have rolled a 2\n" +
					"Chet's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 3\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 5 Gold Coins.\n" +
					"Pat is the current player\n" +
					"They have rolled a 4\n" +
					"Pat's new location is 3\n" +
					"The category is Rock\n" +
					"Rock Question 4\n" +
					"Answer was corrent!!!!\n" +
					"Pat now has 5 Gold Coins.\n" +
					"Sue is the current player\n" +
					"They have rolled a 1\n" +
					"Sue is getting out of the penalty box\n" +
					"Sue's new location is 11\n" +
					"The category is Rock\n" +
					"Rock Question 5\n" +
					"Answer was correct!!!!\n" +
					"Sue now has 2 Gold Coins.\n" +
					"Chet is the current player\n" +
					"They have rolled a 3\n" +
					"Chet's new location is 6\n" +
					"The category is Sports\n" +
					"Sports Question 3\n" +
					"Answer was corrent!!!!\n" +
					"Chet now has 6 Gold Coins.\n";

	@Test
    public void gameIsPlayableWithTwoPlayers() {
        Game game = new Game(new ConsolePrinter());
        game.add("toto");
        game.add("titi");
        assertTrue(game.isPlayable());
    }

    @Test
    public void gameIsNotPlayableWithOnePlayer() {
        Game game = new Game(new ConsolePrinter());
        game.add("Thomas");
        Assert.assertFalse(game.isPlayable());
    }

    @Test
    public void given_a_player_game_should_have_one_player() {
        Game game = new Game(new ConsolePrinter());
        boolean addedPlayer = game.add("Tom");
		assertEquals(1, game.players.howManyPlayers());
    }

	@Test
	public void gameRunner_with__fixed_roll_value_of_1() {
		StringPrinter stringPrinter = new StringPrinter();
		GameRunner.runGame(new RandomMock(1), stringPrinter);
		String output = stringPrinter.getOutput();
		assertEquals(EXPECTED_WITH_ROLL_VALUE_OF_ONE, output);
	}

	@Test
	public void gameRunner_with__fixed_roll_value_of_6() {
		StringPrinter stringPrinter = new StringPrinter();
		GameRunner.runGame(new RandomMock(7), stringPrinter);
		String output = stringPrinter.getOutput();
		assertEquals(EXPECTED_WITH_ROLL_VALUE_OF_SIX, output);
	}

	public class RandomMock extends Random {
		private int rollValue;

		public RandomMock(int rollValue) {

			this.rollValue = rollValue;
		}

		@Override
		public int nextInt(int bound) {
			rollValue++;
			return rollValue % bound;
		}
	}
}

