package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	private final Printer printer;
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

	LinkedList<String> popQuestions = new LinkedList();
	LinkedList<String> scienceQuestions = new LinkedList();
	LinkedList<String> sportsQuestions = new LinkedList();
	LinkedList<String> rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;


	public Game(Printer printer) {
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}

		this.printer = printer;
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean addPlayer(String playerName) {
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

		printer.displayLine(playerName + " was added");
		printer.displayLine("They are player number " + players.size());
		return true;
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
				printer.displayLine("The category is " + currentCategory());
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
			printer.displayLine("The category is " + currentCategory());
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
		if (currentCategory() == "Pop")
			printer.displayLine(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			printer.displayLine(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			printer.displayLine(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			printer.displayLine(rockQuestions.removeFirst());
	}


	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
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
