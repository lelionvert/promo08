package com.adaptionsoft.games.uglytrivia;

public class Game {
    public static final int NUMBER_OF_COINS_TO_WIN = 6;
    private final Deck deck;
    public final Players players;
    private final Printer printer;
    boolean isGettingOutOfPenaltyBox;

	public Game(Printer printer) {
        this.printer = printer;
        deck = new Deck();
        players = new Players();
    }

    static int computeNewPlaceNumber(int newPlaceNumber) {
        return (newPlaceNumber > 11)
                ? newPlaceNumber - 12
                : newPlaceNumber;
    }

    public boolean isPlayable() {
        return (players.howManyPlayers() >= 2);
	}

	@Deprecated
	public boolean add(String playerName) {
		addPlayer(playerName);
		return true;
	}

	public void addPlayer(String playerName) {
        players.addNewPlayer(Player.valueOf(playerName));

		printer.displayLine(playerName + " was added");
        printer.displayLine("They are player number " + players.howManyPlayers());
	}

    public void roll(int roll) {
        printer.displayLine(players.getCurrentPlayer() + " is the current player");
		printer.displayLine("They have rolled a " + roll);

        if (players.isInPenaltyBox(players.currentPlayerIndex)) {
			if (isOdd(roll)) {
				isGettingOutOfPenaltyBox = true;

                printer.displayLine(players.getCurrentPlayer() + " is getting out of the penalty box");
                moveCurrentPlayer(roll);

                printer.displayLine(players.getCurrentPlayer()
						+ "'s new location is "
                        + players.getCurrentPlayerPlaceNumber());
                printer.displayLine("The category is " + QuestionCategory.fromPlace(players.getCurrentPlayerPlaceNumber()).getValue());
				askQuestion();
			} else {
                printer.displayLine(players.getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

            moveCurrentPlayer(roll);

            printer.displayLine(players.getCurrentPlayer()
					+ "'s new location is "
                    + players.getCurrentPlayerPlaceNumber());
            printer.displayLine("The category is " + QuestionCategory.fromPlace(players.getCurrentPlayerPlaceNumber()).getValue());
			askQuestion();
		}

	}

    private void moveCurrentPlayer(int roll) {
        players.moveCurrentPlayerTo(computeNewPlaceNumber(players.getCurrentPlayerPlaceNumber() + roll));
    }

    private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private void askQuestion() {
        QuestionCategory questionCategory = QuestionCategory.fromPlace(players.getCurrentPlayerPlaceNumber());
        printer.displayLine(deck.drawQuestion(questionCategory));
    }

	public boolean wasCorrectlyAnswered() {
        if (players.isInPenaltyBox(players.currentPlayerIndex)) {
			if (isGettingOutOfPenaltyBox) {
				printer.displayLine("Answer was correct!!!!");
                players.getCurrentPlayer().addCoin();
                printer.displayLine(players.getCurrentPlayer()
						+ " now has "
                        + players.getCurrentPlayer().getCoins()
						+ " Gold Coins.");

                boolean winner = !players.currentPlayerHasCoins(NUMBER_OF_COINS_TO_WIN);
                players.nextPlayer();
                return winner;
			} else {
                players.nextPlayer();
                return true;
			}
		} else {
			printer.displayLine("Answer was corrent!!!!");
            players.getCurrentPlayer().addCoin();
            printer.displayLine(players.getCurrentPlayer()
					+ " now has "
                    + players.getCurrentPlayer().getCoins()
					+ " Gold Coins.");

            boolean winner = !players.currentPlayerHasCoins(NUMBER_OF_COINS_TO_WIN);
            players.nextPlayer();

            return winner;
		}
	}

    public boolean wrongAnswer() {
		printer.displayLine("Question was incorrectly answered");
        printer.displayLine(players.getCurrentPlayer() + " was sent to the penalty box");
        players.putPlayerInPenaltyBox(players.currentPlayerIndex);

        players.nextPlayer();
        return true;
	}


}
