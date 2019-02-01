package com.adaptionsoft.games.uglytrivia;

public class Game {
    private final Deck deck;
    public final Players players;
    private final Board board;
    private final Printer printer;
    int currentPlayerIndex = 0;
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
        printer.displayLine(players.getPlayer(currentPlayerIndex) + " is the current player");
		printer.displayLine("They have rolled a " + roll);

        if (players.isInPenaltyBox(currentPlayerIndex)) {
			if (isOdd(roll)) {
				isGettingOutOfPenaltyBox = true;

                printer.displayLine(players.getPlayer(currentPlayerIndex) + " is getting out of the penalty box");
                board.moveCurrentPlayer(roll, currentPlayerIndex);

                printer.displayLine(players.getPlayer(this.currentPlayerIndex)
						+ "'s new location is "
                        + board.getPlace(this.currentPlayerIndex));
                printer.displayLine("The category is " + QuestionCategory.fromPlace(board.getPlace(this.currentPlayerIndex)).getValue());
				askQuestion();
			} else {
                printer.displayLine(players.getPlayer(currentPlayerIndex) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

            board.moveCurrentPlayer(roll, currentPlayerIndex);

            printer.displayLine(players.getPlayer(currentPlayerIndex)
					+ "'s new location is "
                    + board.getPlace(currentPlayerIndex));
            printer.displayLine("The category is " + QuestionCategory.fromPlace(board.getPlace(currentPlayerIndex)).getValue());
			askQuestion();
		}

	}

    private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private void askQuestion() {
        QuestionCategory questionCategory = QuestionCategory.fromPlace(board.getPlace(currentPlayerIndex));
        printer.displayLine(deck.drawQuestion(questionCategory));
    }

	public boolean wasCorrectlyAnswered() {
        if (players.isInPenaltyBox(this.currentPlayerIndex)) {
			if (isGettingOutOfPenaltyBox) {
				printer.displayLine("Answer was correct!!!!");
                players.getPlayer(this.currentPlayerIndex).addCoin();
                printer.displayLine(players.getPlayer(this.currentPlayerIndex)
						+ " now has "
                        + players.getPlayer(this.currentPlayerIndex).getCoins()
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
                this.currentPlayerIndex++;
                if (this.currentPlayerIndex == players.howManyPlayers()) this.currentPlayerIndex = 0;
				
				return winner;
			} else {
                this.currentPlayerIndex++;
                if (this.currentPlayerIndex == players.howManyPlayers()) this.currentPlayerIndex = 0;
				return true;
			}
		} else {

			printer.displayLine("Answer was corrent!!!!");
            players.getPlayer(this.currentPlayerIndex).addCoin();
            printer.displayLine(players.getPlayer(this.currentPlayerIndex)
					+ " now has "
                    + players.getPlayer(this.currentPlayerIndex).getCoins()
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
            this.currentPlayerIndex++;
            if (this.currentPlayerIndex == players.howManyPlayers()) this.currentPlayerIndex = 0;
			
			return winner;
		}
	}

    public boolean wrongAnswer() {
		printer.displayLine("Question was incorrectly answered");
        printer.displayLine(players.getPlayer(this.currentPlayerIndex) + " was sent to the penalty box");
        players.putPlayerInPenaltyBox(this.currentPlayerIndex);

        this.currentPlayerIndex++;
        if (this.currentPlayerIndex == players.howManyPlayers()) this.currentPlayerIndex = 0;
		return true;
	}


    private boolean didPlayerWin() {
        return !(players.getPlayer(currentPlayerIndex).getCoins() == 6);
	}
}
