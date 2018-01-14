package battleship;

/**
 * Describes characteristics common to all the ships. It has subclasses:
 * 
 * @author Rui Yang
 */
/**
 * @author Misty
 *
 */
public abstract class Ship {
	int bowRow;
	int bowColumn;
	int length;
	boolean horizontal;
	boolean[] hit = new boolean[4];

	abstract int getLength();

	// Getters
	/**
	 * Returns the row which contains the bow of the ship.
	 * 
	 * @return bowRow
	 */
	int getBowRow() {
		return bowRow;
	}

	/**
	 * Returns the column which contains the bow of the ship.
	 * 
	 * @return
	 */
	int getBowColumn() {
		return bowColumn;
	}

	/**
	 * Returns true if the ship occupies a single row, false otherwise.
	 * 
	 * @return
	 */
	boolean isHorizontal() {
		return horizontal;
	}

	// Setters
	/**
	 * Sets the value of bowRow
	 * 
	 * @param row
	 */
	void setBowRow(int row) {
		bowRow = row;
	}

	/**
	 * Sets the value of bowColumn
	 * 
	 * @param column
	 */
	void setBowColumn(int column) {
		bowColumn = column;
	}

	/**
	 * Sets the value of the instance variable horizontal.
	 * 
	 * @param horizontal
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Returns the type of this ship.
	 * 
	 * @return
	 */
	abstract String getShipType();

	/**
	 * Returns true if of left, diagonal top left, or diagonal bottom right of
	 * the bow is occupied, and returns false otherwise.
	 * 
	 * @param x
	 * @param y
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	/**
	 * Checks if the area under this ship is occupied by another ship.
	 *
	 * @param y
	 *            the y-coordinate of this ships bow
	 * @param x
	 *            the x-coordinate of this ships bow
	 * @param horizontal
	 *            the orientation of this ship
	 * @param ocean
	 *            reference to the instance of the Ocean class
	 * @return <code>true</code> if there is a ship under this ship;
	 *         <code>false</code> otherwise.
	 */
	private boolean isThereAShipUnderThisOne(int y, int x, boolean horizontal, Ocean ocean) {
		int traverseLength;

		traverseLength = length;

		return isAreaOccupied(y, x, horizontal, ocean, traverseLength);
	}

	/**
	 * Checks if the area in front of this ships bow is occupied by another
	 * ship.
	 *
	 * @param y
	 *            the y-coordinate of this ships bow
	 * @param x
	 *            the x-coordinate of this ships bow
	 * @param horizontal
	 *            the orientation of this ship
	 * @param ocean
	 *            reference to the instance of the Ocean class
	 * @return <code>true</code> if there is a ship in front of this ships bow;
	 *         <code>false</code> otherwise.
	 */
	private boolean isThereAShipBeforeTheBow(int y, int x, boolean horizontal, Ocean ocean) {

		int traverseLength;
		boolean traverseHorizontally;

		y--;
		x--;
		traverseLength = 3;
		traverseHorizontally = !horizontal;

		return isAreaOccupied(y, x, traverseHorizontally, ocean, traverseLength);
	}

	/**
	 * Checks if the area to starboard of this ship is occupied by another ship.
	 *
	 * @param y
	 *            the y-coordinate of this ships bow
	 * @param x
	 *            the x-coordinate of this ships bow
	 * @param horizontal
	 *            the orientation of this ship
	 * @param ocean
	 *            reference to the instance of the Ocean class
	 * @return <code>true</code> if there is a ship to starboard;
	 *         <code>false</code> otherwise.
	 */
	private boolean isThereAShipToStarboard(int y, int x, boolean horizontal, Ocean ocean) {
		int traverseLength;

		traverseLength = length;

		if (horizontal) {
			y--;
		} else {
			x++;
		}

		return isAreaOccupied(y, x, horizontal, ocean, traverseLength);
	}

	/**
	 * Checks if the area behind this ships stern is occupied by another ship.
	 *
	 * @param y
	 *            the y-coordinate of this ships bow
	 * @param x
	 *            the x-coordinate of this ships bow
	 * @param horizontal
	 *            the orientation of this ship
	 * @param ocean
	 *            reference to the instance of the Ocean class
	 * @return <code>true</code> if there is a ship behind this ships aft;
	 *         <code>false</code> otherwise.
	 */
	private boolean isThereAShipBehindTheStern(int y, int x, boolean horizontal, Ocean ocean) {
		int traverseLength;
		boolean traverseHorizontally;

		if (horizontal) {
			x += length;
			y--;
		} else {
			x--;
			y += length;
		}

		traverseLength = 3;
		traverseHorizontally = !horizontal;

		return isAreaOccupied(y, x, traverseHorizontally, ocean, traverseLength);

	}

	/**
	 * Checks if the area to port of this ships is occupied by another ship.
	 *
	 * @param y
	 *            the y-coordinate of this ships bow
	 * @param x
	 *            the x-coordinate of this ships bow
	 * @param horizontal
	 *            the orientation of this ship
	 * @param ocean
	 *            reference to the instance of the Ocean class
	 * @return <code>true</code> if there is a ship to port; <code>false</code>
	 *         otherwise.
	 */
	private boolean isThereAShipToPort(int y, int x, boolean horizontal, Ocean ocean) {
		int traverseLength;

		traverseLength = length;

		if (horizontal) {
			y++;
		} else {
			x--;
		}

		return isAreaOccupied(y, x, horizontal, ocean, traverseLength);
	}

	/**
	 * Returns true if the area of the ship is occupied, and returns false
	 * otherwise.
	 * 
	 * @param x
	 * @param y
	 * @param traverseHorizontally
	 * @param ocean
	 * @param traverseLength
	 * @return
	 */
	private boolean isAreaOccupied(int x, int y, boolean traverseHorizontally, Ocean ocean, int traverseLength) {
		if (traverseHorizontally) {
			for (int i = y; i < y + traverseLength; i++) {
				if (ocean.isOccupied(x, i)) {
					return true;
				}
			}
		} else {
			for (int i = x; i < x + traverseLength; i++) {
				if (ocean.isOccupied(i, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns true if the ship is out of range, and returns false otherwise.
	 * 
	 * @param x
	 * @param y
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	private boolean isAtOutsideTheOcean(int x, int y, boolean horizontal, Ocean ocean) {
		if (horizontal) {
			if (y + length > ocean.getShipArray().length) {
				return true;
			}
		} else {
			if (x + length > ocean.getShipArray().length) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if it is OK to put a ship of this length with its bow in
	 * this location, with the given orientation, and returns false otherwise.
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (isAtOutsideTheOcean(row, column, horizontal, ocean))
			return false;

		if (isThereAShipUnderThisOne(row, column, horizontal, ocean))
			return false;

		if (isThereAShipBeforeTheBow(row, column, horizontal, ocean))
			return false;

		if (isThereAShipToStarboard(row, column, horizontal, ocean))
			return false;

		if (isThereAShipBehindTheStern(row, column, horizontal, ocean))
			return false;

		if (isThereAShipToPort(row, column, horizontal, ocean))
			return false;

		if (ocean.isOccupied(row, column))
			return false;

		return true;
	}

	/**
	 * Puts the ship in the ocean.
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;

		ocean.isOccupied(row, column);
		if (horizontal) {
			for (int i = 0; i < length; i++) {
				ocean.getShipArray()[row][column + i] = this;
			}
		} else {
			for (int i = 0; i < length; i++) {
				ocean.getShipArray()[row + i][column] = this;
			}
		}
	}

	/**
	 * Returns true if a part of the ship occupies the row and column, and the
	 * ship hasn't already been sunk, and returns false otherwise.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		if (horizontal && bowRow == row) {
			for (int i = bowColumn; i < bowColumn + length; i++) {
				if (i == column) {
					hit[column - bowColumn] = true;
					return true;
				}
			}
		} else if (!horizontal && bowColumn == column) {
			for (int i = bowRow; i < bowRow + length; i++) {
				if (i == row) {
					hit[row - bowRow] = true;
					return true;
				}
			}
		}
		this.hit[0] = true;

		return false;
	}

	/**
	 * Returns true if every part of the ship has been hit, false otherwise.
	 * 
	 * @return
	 */
	boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (!hit[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a single-character String to use in the Ocean's print method.
	 */
	@Override
	public String toString() {
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}
}