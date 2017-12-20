package com.LeeGainer.Battleship2;

import java.util.Scanner;

public class Console {
	
	private static Scanner myScanner = new Scanner(System.in);
	
	// I/O
	
	/*
	 * Outputs message
	 * String -> void
	 */
	public void displayMessage(String string) {
		System.out.print(string);
	}
	
	/*
	 * Gets coordinate from user or computer
	 * int, int -> int
	 */
	public int getCoordinate(int minValue, int maxValue) {
		
		String coordinate;
		int x = 0;
		boolean running = true;
			
		// Validate coordinate
		while(running) {
			coordinate = myScanner.next();
			
			try {
				x = Integer.parseInt(coordinate);
			}
			catch( Exception e) {
				displayMessage("That input is invalid.\n"
						+ "Please enter a number between 0 and " + Integer.toString(maxValue) + ". ");	
				continue;
			}			
			
			if(x > maxValue || x < minValue) {
				displayMessage("That input is invalid.\n"
					+ "Please enter a number between 0 and " + Integer.toString(maxValue) + ". ");	
				continue;
			} else {
				running = false;
			}
		}			
		return x;
	}
	
	/*
	 * Displays Gameboard 2d array w/ game info around it
	 * String[][], Object -> void
	 */
	public void displayBoard(String[][] array, Gameboard board) {
		
		int row = board.getMaxRow();
		int col = board.getMaxCol();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < col; i++) {
			sb.append(i + " ");
		}
		
		displayMessage("\n\n   " + sb.toString() + "   \n");		
		for(int i = 0; i < row; i++) {
			displayMessage(i + " |");
			for(int j = 0; j < col; j++) {
				if(j == col - 1) {
					displayMessage(array[i][j]);
				} else {
					displayMessage(array[i][j] + " ");
				}				
			}
			displayMessage("| " + i + "\n");
		}
		displayMessage("   " + sb.toString() + "   ");		
	}
	
	/*
	 * Pauses game for better readability
	 * int -> void
	 */	
	public void pause(int i) { 
		
		try {
			Thread.sleep(i);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}		
	}
	
	/*
	 * Display Fleet status (how many ships are active)
	 * Object -> void
	 */
	public void showFleetStatus(Gameboard board) {
		
		Counter myCounter = new Counter();		
		
		for(Ship s : board.getMyShips()) {
			if(s.getActive() == true) {
				myCounter.addToCounter();
			}
		}
		
		if(myCounter.getCounter() >= 2) {
			displayMessage("\n\nYou have " + myCounter.getCounter() + " ships left in your fleet.");
		} else {
			displayMessage("\n\nYou have " + myCounter.getCounter() + " ship left in your fleet.");
		}
		
		
	}
	
	/*
	 * Checks for positive response
	 * void -> boolean 
	 */
	 public boolean checkForYes(String string) {
		 
		String[] list = new String[] {"yes", "y"};	
		
		for(String s : list) {
			if(s.equalsIgnoreCase(string)) {
				return true;
			}
		}		
		return false;
	}
	 
	 /*
	  * Asks human if they want to see the computer's fleet
	  * void -> boolean
	  */
	public boolean viewComputerFleet() {
		displayMessage("\nWould you like to see the computer's fleet, Y/N?");
		String s = myScanner.next();
		if(checkForYes(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Asks human if they want to play again?
	 * void -> boolean
	 */

	public boolean playAgain() {
		displayMessage("\nWould you like to play again, Y/N?");
		String s = myScanner.next();
		
		if(checkForYes(s)) {
			displayMessage("\n");
			return true;
		} else {
			return false;
		}		
	}
}
