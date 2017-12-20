package com.LeeGainer.Battleship2;

public class Player {
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}

	/*
	 * Returns name of Player
	 * Object -> String
	 */	
	public String getName(Player p) {
		return name;
	}
		
	/*
	 * Checks if Player name is "user"
	 * Object -> boolean
	 */
	public boolean checkForUser(Player p) {
		if(p.getName(p).equals("user")) {
			return true;			
		} else {
			return false;
		}
	}
}
