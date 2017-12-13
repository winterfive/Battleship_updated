package com.LeeGainer.Battleship2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Gameboard {
	
	Console myConsole = new Console();
	Counter shotCount = new Counter();
	
	private int maxRow = 10;
	private int maxCol = 10;
	private String[][] shotboard;	// Hits and misses
	private String[][] fleetboard;	// Ship locations
	private boolean isHuman = false;
	private ArrayList <Ship> myShips;
	private ArrayList <Point> myShots;
	
	public Gameboard(boolean isHuman) {
		this.isHuman = isHuman;
		
		shotboard = new String[maxRow][maxCol];
		fillArray(shotboard, " ");		
		
		fleetboard = new String[maxRow][maxCol];
		fillArray(fleetboard, " ");
		
		myShips = new ArrayList <Ship>();
		myShots = new ArrayList <Point>();
	}
	
	/*
	 * Fills null String[][] with spaces, " "
	 * String[][], String -> void
	 */
	private void fillArray(String[][] board, String s) {	
		for(int i = 0; i < maxRow; i++) {
			for(int j = 0; j < maxCol; j++) {
				board[i][j] = s;
			}
		}
	}

	/*
	 * Updates Gameboard fleetboard
	 * Point, String -> void
	 */
	protected void updateFleetboard(Point p, String s) {
		int x = p.x;
		int y = p.y;
		
		this.getFleetboard()[x][y] = s;
	}
	
	/*
	 * Updates shotboard
	 * Point, String -> void
	 */
	protected void updateShotboard(Point p, String s) {
		int x = p.x;
		int y = p.y;
		
		getShotboard()[x][y] = s;
	}
	
	/*
	 * Creates Player "fleet" (ArrayList myShips)
	 * void -> void
	 */	
	protected void createFleet() {
		
		int shipCount = 0;
		
		while(shipCount < 5) {
			
			Point xy;			
			
			while(true) {
				
				xy = new Point();
				
				// Get Row Coordinate
				myConsole.displayMessage("\n\nEnter X coordinate for ship #" + (shipCount + 1) + ": ");
				xy.x = myConsole.getCoordinate(0, maxRow - 1);
				
				// Get Column Coordinate
				myConsole.displayMessage("Enter Y coordinate for ship #" + (shipCount + 1) + ": ");
				xy.y = myConsole.getCoordinate(0, maxCol - 1);
				
				// Check for repeated ship location
				// If this isn't the first ship
				if(shipCount > 0) {
					if(isLocationUsed(xy)) {
						myConsole.displayMessage("\nThat location is already being used.");					
						continue;							
					}
				} 
				break;
			}
			
			Ship myShip = new Ship(xy, true);
			myShips.add(myShip);			
			updateFleetboard(xy, "S");
			myConsole.displayBoard(this.getFleetboard(), this);
			shipCount++;
		}
	}
	
	/*
	 * Creates fleet of 5 ships without input
	 * Places five points in myShips ArrayList
	 * void -> void
	 */
	protected void autoCreateFleet() {
		
		int shipCount = 1;
		
		while(shipCount++ <= 5) {
			
			Point xy;
			
			while(true) {
				
				xy = getRandomPoint();

				if(shipCount > 0) {
					if(isLocationUsed(xy)) {				
						continue;							
					} else {
						break;
					}
				}
			}

			Ship myShip = new Ship(xy, true);
			myShips.add(myShip);
			updateFleetboard(xy, "S");
		}
	}
	
	/*
	 * Gets Point for player shots
	 * void -> Point
	 */
	protected Point takeShot() {
		
		Point xy = new Point();
		shotCount.addToCounter();

		// If player is user
		if(isHuman) {
			myConsole.displayMessage("\nEnter X coordinate for shot #" + shotCount.getCounter() + ": ");
			xy.x = myConsole.getCoordinate(0, maxRow);
			myConsole.displayMessage("Enter Y coordinate for shot #" + shotCount.getCounter() + ": ");
			xy.y = myConsole.getCoordinate(0, maxCol);

		} else {
			// Get coordinates for computer ship
			do {
				xy = getRandomPoint();
			} while(isShotRepeat(xy));	
		}
		return xy;
	}
	
	/*
	 * Evaluates "shot" (point) fired by players
	 * Point -> boolean
	 */
	protected boolean evaluateShot(Point point) {
		// If shot hit a ship
		if(isLocationUsed(point)) {
			return true;
		// Shot missed.
		} else {
			return false;			
		}
	}
		
	/*
	 * Checks active state of ships in myArray for victory
	 * void -> boolean
	 */
	protected boolean hasLost() {
		
		for(Ship s : myShips) {
			if(s.getActive() == true) {
				return false;
			}
		}		
		return true;
	}
	
	/*
	 * Check if Point already exists in ArrayList myShips
	 * Point -> boolean
	 */
	protected boolean isLocationUsed(Point point) {
			
		for(Ship s : myShips) {
	        if(s.getLocation().equals(point)) {
	        		return true;
	        }
		}		
		return false;
	}
	
	/*
	 * Checks computer myShots for repeated shots
	 * Point -> boolean
	 */	
	protected boolean isShotRepeat(Point xy) {
		for(Point point : myShots) {
	        if(point.equals(xy)) {
	        		return true;
	        }
		}
		return false;
	}
	
	/*
	 * Checks if player is user
	 * void -> boolean
	 */
	protected boolean isHuman() {
		return this.isHuman;
	}
	
	/*
	 * returns maxRow
	 * void -> int
	 */	
	protected int getMaxRow() {
		return maxRow;
	} 
	
	/*
	 * returns maxCol
	 * void -> int
	 */	
	protected int getMaxCol() {
		return maxCol;
	}
	
	/*
	 * Returns field shotboard
	 * void -> String[][]
	 */
	protected String[][] getShotboard() {
		return shotboard;
	}
	
	/*
	 * Returns field fleetboard
	 * void -> String[][]
	 */
	public String[][] getFleetboard() {
		return fleetboard;
	}
	
	/*
	 * Returns ArrayList myShips
	 * void -> ArrayList
	 */
	protected ArrayList <Ship> getMyShips() {
		return this.myShips;
	}
	
	/**
	 * Return ArrayList myShots
	 * void -> ArrayList
	 */
	protected ArrayList <Point> getMyShots() {
		return myShots;
	}
	
	/* 
	 * Gets a random point within our playable grid
	 * void -> Point
	 */
	protected Point getRandomPoint() {
		Point xy = new Point();
		Random r = new Random();
		
		// maxRow/maxCol - 1 due to 0 index		
		xy.x = r.nextInt(maxRow - 1);
		xy.y = r.nextInt(maxCol - 1);
		
		return xy;
	}
	
	/*
	 * Change sunken Ship.active to false
	 * Point -> void
	 */
	public void setToFalse(Point point) {
		// Change user ship.active to false
		for(Ship s : myShips) {
			if(s.getLocation().equals(point)) {
                s.setActive(false);
			}
		}
	}
	
	/*
	 * Updates fleetboard and myShips with "hit"
	 * Point, Object -> void
	 */
	protected void setHit(Point p, Gameboard opponent) {
		// Update fields for player who took shot
		updateShotboard(p, "X");
		myShots.add(p);
		// Update fields for opponent
		opponent.updateFleetboard(p, "X");
		opponent.setToFalse(p);
	}
	
	/*
	 * Updates fleetboard with "miss"
	 * Point -> void
	 */
	protected void setMiss(Point p) {
		// Update fields for player who took shot
		updateShotboard(p, "-");
		myShots.add(p);
	}
}
