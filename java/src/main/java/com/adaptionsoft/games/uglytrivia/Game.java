package com.adaptionsoft.games.uglytrivia;

public class Game {
    public static final int NUMBER_OF_COINS_TO_WIN = 6;
    private final Deck deck;
    public final Players players;
    private final Board board;
    private final Printer printer;
    boolean isGettingOutOfPenaltyBox;

	public Game(Printer printer) {
        this.printer = printer;
        deck = new Deck();
        players = new Players();
        board = new Board();
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
        board.initPlace(players.howManyPlayers());

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
                board.moveCurrentPlayer(roll, players.currentPlayerIndex);

                printer.displayLine(players.getCurrentPlayer()
						+ "'s new location is "
                        + board.getPlace(players.currentPlayerIndex));
                printer.displayLine("The category is " + QuestionCategory.fromPlace(board.getPlace(players.currentPlayerIndex)).getValue());
				askQuestion();
			} else {
                printer.displayLine(players.getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

            board.moveCurrentPlayer(roll, players.currentPlayerIndex);

            printer.displayLine(players.getCurrentPlayer()
					+ "'s new location is "
                    + board.getPlace(players.currentPlayerIndex));
            printer.displayLine("The category is " + QuestionCategory.fromPlace(board.getPlace(players.currentPlayerIndex)).getValue());
			askQuestion();
		}

	}

    private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private void askQuestion() {
        QuestionCategory questionCategory = QuestionCategory.fromPlace(board.getPlace(players.currentPlayerIndex));
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
