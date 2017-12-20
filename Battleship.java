package com.LeeGainer.Battleship2;

import java.awt.Point;

public class Battleship {
	
	Console myConsole = new Console();
	
	Gameboard userBoard, computerBoard;
	// Player user, computer;
	
	public static void main(String [] args) {
		
		Battleship myBattleship = new Battleship();
		myBattleship.beginGame();			
	}
	
	public Battleship() {}
	
	/*
	 * Restarts game if the human chooses to do so
	 * void -> void
	 */	
	public void beginGame() {
		do {
			setupGame();
			playGame();			
		} while(myConsole.playAgain());		
	}
	
	/* 
	 * Sets up the game board so we can begin play.
	 * void -> void
	 */
	private void setupGame() {	
		
		userBoard = new Gameboard(true);
		computerBoard = new Gameboard(false);
		
		myConsole.displayMessage("*** Welcome to the Battleship Game ***\n\nRight now, the sea is empty.");
		
		//Build the users fleet
		myConsole.displayBoard(userBoard.getFleetboard(), userBoard);
		myConsole.displayMessage("\n\nPrepare your fleet for battle.");		
		userBoard.createFleet();
		myConsole.displayMessage("\n\nYour fleet is deployed.");
		
		// Build the computer's fleet
		myConsole.pause(1000);
		myConsole.displayMessage("\nPreparing the computer's fleet for battle.");
		myConsole.pause(1000);
		computerBoard.autoCreateFleet();
		myConsole.displayMessage("\nThe computer's fleet is ready.");	
		myConsole.pause(1000);
		myConsole.displayMessage("\nLet the battle begin!");
		myConsole.pause(1000);
	}
	
	/*
	 * Play the game
	 * void -> void
	 */
	public void playGame() {
	
		Point xy;
		boolean running = true;
		
		while(running) {
			myConsole.displayBoard(userBoard.getShotboard(), userBoard);
			myConsole.displayMessage("\n\nPLAYER'S TURN:");
			xy = userBoard.takeShot();
			myConsole.pause(1000);
			// Evaluate if the user's shot against the computer board.
			// Shot is repeat
			if(userBoard.isShotRepeat(xy)) {
				myConsole.displayMessage("You've made that shot before.");
			} else if(computerBoard.evaluateShot(xy)) {
				// Human sank a computer ship
				userBoard.setHit(xy, computerBoard);
				myConsole.displayMessage("You sank a ship!");
			} else {
				// Human missed.
				userBoard.setMiss(xy);
				myConsole.displayMessage("You missed.");
			}			
			
			if(computerBoard.hasLost()) {
				myConsole.displayBoard(userBoard.getShotboard(), userBoard);
				myConsole.displayMessage("\n\nThe computer's fleet has been defeated.  You win!");
				running = false;
				continue;
			}
			
			myConsole.displayMessage("\n\nCOMPUTER'S TURN:");
			myConsole.pause(1500);
			xy = computerBoard.takeShot();
			// Evaluate if the computer shot against the user board.
			if(userBoard.evaluateShot(xy)) {
				// Computer sank a human ship
				computerBoard.setHit(xy, userBoard);
				myConsole.displayMessage("\nThe computer sank a ship!");				
			} else {
				// Computer missed
				computerBoard.setMiss(xy);
				myConsole.displayMessage("\nThe computer missed.");
			}
			
			// Display human player fleet status update
			myConsole.pause(500);
			if(userBoard.hasLost()) {
				myConsole.displayBoard(userBoard.getFleetboard(), userBoard);
				myConsole.displayMessage("\n\nYour fleet has been defeated.  You lose.");
				// Display computer fleet?
				if(myConsole.viewComputerFleet()) {
					myConsole.displayBoard(computerBoard.getFleetboard(), computerBoard);
				}
				running = false;
			} else {
				myConsole.showFleetStatus(userBoard); 
			}
		}			
	}
}
