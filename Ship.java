package com.LeeGainer.Battleship2;

import java.awt.Point;

public class Ship {
	
	private Point location;
	private Boolean active;
	
	public Ship(Point location, Boolean active) {
		this.setLocation(location);
		this.setActive(active);
	}

	/**
	 * Return value of location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Set value of location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}	
}
