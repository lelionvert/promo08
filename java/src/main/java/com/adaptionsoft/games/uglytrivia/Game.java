package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {
    final Deck deck = new Deck();
    private final Printer printer;
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;


	public Game(Printer printer) {
        this.printer = printer;
    }

    public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	@Deprecated
	public boolean add(String playerName) {
		addPlayer(playerName);
		return true;
	}

	public void addPlayer(String playerName) {
		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		printer.displayLine(playerName + " was added");
		printer.displayLine("They are player number " + players.size());
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		printer.displayLine(players.get(currentPlayer) + " is the current player");
		printer.displayLine("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (isOdd(roll)) {
				isGettingOutOfPenaltyBox = true;

				printer.displayLine(players.get(currentPlayer) + " is getting out of the penalty box");
				moveCurrentPlayer(roll);

				printer.displayLine(players.get(currentPlayer)
						+ "'s new location is "
						+ places[currentPlayer]);
                printer.displayLine("The category is " + getQuestionCategory().getValue());
				askQuestion();
			} else {
				printer.displayLine(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			moveCurrentPlayer(roll);

			printer.displayLine(players.get(currentPlayer)
					+ "'s new location is "
					+ places[currentPlayer]);
            printer.displayLine("The category is " + getQuestionCategory().getValue());
			askQuestion();
		}

	}

	private void moveCurrentPlayer(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
	}

	private boolean isOdd(int roll) {
		return roll % 2 != 0;
	}

	private void askQuestion() {
        QuestionCategory questionCategory = getQuestionCategory();
        printer.displayLine(deck.drawQuestion(questionCategory));
    }

    private QuestionCategory getQuestionCategory() {
        QuestionCategory currentCategory = QuestionCategory.ROCK;
        int place = places[currentPlayer];
        if (place == 0 || place == 4 || place == 8) currentCategory = QuestionCategory.POP;
        if (place == 1 || place == 5 || place == 9) currentCategory = QuestionCategory.SCIENCE;
        if (place == 2 || place == 6 || place == 10) currentCategory = QuestionCategory.SPORTS;
        return currentCategory;
    }

    public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				printer.displayLine("Answer was correct!!!!");
				purses[currentPlayer]++;
				printer.displayLine(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {

			printer.displayLine("Answer was corrent!!!!");
			purses[currentPlayer]++;
			printer.displayLine(players.get(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		printer.displayLine("Question was incorrectly answered");
		printer.displayLine(players.get(currentPlayer) + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
