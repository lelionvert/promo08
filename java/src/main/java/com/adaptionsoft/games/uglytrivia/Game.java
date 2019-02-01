package com.adaptionsoft.games.uglytrivia;

public class Game {
    final Deck deck = new Deck();
    public final Players players = new Players();
    final Board board = new Board();
    private final Printer printer;
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;



	public Game(Printer printer) {
        this.printer = printer;
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
        players.addNewPlayer(playerName);
        board.initPlace(players.howManyPlayers());
        purses[players.howManyPlayers()] = 0;
        inPenaltyBox[players.howManyPlayers()] = false;

		printer.displayLine(playerName + " was added");
        printer.displayLine("They are player number " + players.howManyPlayers());
	}

    public void roll(int roll) {
        printer.displayLine(players.getPlayer(currentPlayer) + " is the current player");
		printer.displayLine("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (isOdd(roll)) {
				isGettingOutOfPenaltyBox = true;

                printer.displayLine(players.getPlayer(currentPlayer) + " is getting out of the penalty box");
                board.moveCurrentPlayer(roll, currentPlayer);

                printer.displayLine(players.getPlayer(this.currentPlayer)
						+ "'s new location is "
                        + board.getPlace(this.currentPlayer));
                printer.displayLine("The category is " + QuestionCategory.fromPlace(board.getPlace(this.currentPlayer)).getValue());
				askQuestion();
			} else {
                printer.displayLine(players.getPlayer(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

            board.moveCurrentPlayer(roll, currentPlayer);

            printer.displayLine(players.getPlayer(currentPlayer)
					+ "'s new location is "
                    + board.getPlace(currentPlayer));
            printer.displayLine("The category is " + QuestionCategory.fromPlace(board.getPlace(currentPlayer)).getValue());
			askQuestion();
		}

	}

    private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private void askQuestion() {
        QuestionCategory questionCategory = QuestionCategory.fromPlace(board.getPlace(currentPlayer));
        printer.displayLine(deck.drawQuestion(questionCategory));
    }

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				printer.displayLine("Answer was correct!!!!");
				purses[currentPlayer]++;
                printer.displayLine(players.getPlayer(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
                if (currentPlayer == players.howManyPlayers()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
                if (currentPlayer == players.howManyPlayers()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {

			printer.displayLine("Answer was corrent!!!!");
			purses[currentPlayer]++;
            printer.displayLine(players.getPlayer(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
            if (currentPlayer == players.howManyPlayers()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		printer.displayLine("Question was incorrectly answered");
        printer.displayLine(players.getPlayer(this.currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[this.currentPlayer] = true;

        this.currentPlayer++;
        if (this.currentPlayer == players.howManyPlayers()) this.currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
