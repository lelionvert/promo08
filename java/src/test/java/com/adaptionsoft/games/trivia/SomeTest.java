package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class SomeTest {

	@Test
	public void given_a_player_game_should_have_one_player() {
		Game game = new Game();
		boolean addedPlayer = game.addPlayer("Tom");
		Assert.assertEquals(1, game.howManyPlayers());
	}

	@Test
	public void name() {

	}
}
