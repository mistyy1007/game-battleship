package battleship;

import java.util.Random;

/**
 * Creates a 10x10 array of Ships, representing the "ocean," and some methods to
 * manipulate it.
 * 
 * @author Rui Yang
 *
 */
public class Ocean {
	String[][] shipPrint = new String[11][11];
	Ship[][] ships = new Ship[10][10];
	int shotsFired;
	int hitCount;
	private int shipsSunk;
	private final int Num_OF_SHIPS = 10;

	public Ocean() {
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[0].length; j++) {
				ships[i][j] = new EmptySea();
			}
		}
		shotsFired = 0;
		hitCount = 0;
	}

	private Ship[] createRandomFleet() {
		Random rand = new Random();
		Ship[] fleet = new Ship[10];

		final int TYPES = 4;
		final int BATTLESHIPS = 1;
		final int CRUISERS = 2;
		final int DESTROYERS = 3;
		final int SUBMARINES = 4;

		int count = 0;
		int countBattleships = 0;
		int countCruiser = 0;
		int countDestroyer = 0;
		int countSubmarine = 0;

		do {
			int shipNo = rand.nextInt(TYPES);
			if (shipNo == 0 && countBattleships < BATTLESHIPS) {
				fleet[count] = new Battleship();
				count++;
				countBattleships++;
			}
			if (shipNo == 1 && countCruiser < CRUISERS) {
				fleet[count] = new Cruiser();
				count++;
				countCruiser++;
			}
			if (shipNo == 2 && countDestroyer < DESTROYERS) {
				fleet[count] = new Destroyer();
				count++;
				countDestroyer++;
			}
			if (shipNo == 3 && countSubmarine < SUBMARINES) {
				fleet[count] = new Submarine();
				count++;
				countSubmarine++;
			}
		} while (count < fleet.length);

		return fleet;
	}

	/**
	 * Places all ten ships randomly on the empty ocean legally.
	 */
	void placeAllShipsRandomly() {
		Random rand = new Random();
		Ship[] fleet = createRandomFleet();
		boolean horizontal;
		int row;
		int column;
		for (Ship ship : fleet) {
			do {
				row = rand.nextInt(ships.length);
				column = rand.nextInt(ships.length);
				horizontal = (rand.nextInt() % 2 == 0) ? true : false;
			} while (!ship.okToPlaceShipAt(row, column, horizontal, this));
			ship.placeShipAt(row, column, horizontal, this);
		}
	}

	/**
	 * Returns true if the given location contains a ship, false if it does not.
	 * 
	 * @param row
	 *            row number of the ship
	 * @param column
	 *            column number of the ship
	 * @return
	 */
	boolean isOccupied(int row, int column) {
		try {
			return !(ships[row][column] instanceof EmptySea);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Returns true if the given location contains a "real" ship, still afloat,
	 * (not an EmptySea), false if it does not.
	 * 
	 * @param row
	 *            row number of the ship
	 * @param column
	 *            column num of the ship
	 * @return
	 */
	boolean shootAt(int row, int column) {
		shotsFired++;
		if (isOccupied(row, column)) {
			if (ships[row][column].isSunk()) {
				return false;
			}

			if (ships[row][column].shootAt(row, column)) {
				hitCount++;
				if (ships[row][column].isSunk()) {
					shipsSunk++;
				}
				return true;
			} else {
				return false;
			}
		} else {
			ships[row][column].shootAt(row, column);
			return false;
		}
	}

	/**
	 * Returns the number of shots fired (in this game).
	 * 
	 * @return number of shots
	 */
	int getShotsFired() {
		return shotsFired;
	}

	/**
	 * Returns the number of hits recorded (in this game). All hits are counted,
	 * not just the first time a given square is hit.
	 * 
	 * @return number of hits
	 */
	int getHitCount() {
		return hitCount;
	}

	/**
	 * Returns the number of ships have been sunk.
	 * 
	 * @return the number of sunk ships
	 */
	int getShipsSunk() {
		return shipsSunk;
	}

	/**
	 * Returns true if all ships have been sunk, otherwise false.
	 * 
	 * @return
	 */
	boolean isGameOver() {
		return shipsSunk == Num_OF_SHIPS;
	}

	/**
	 * Returns the actual 10x10 array of ships.
	 * 
	 * @return
	 */
	Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Returns true if the place has been hit
	 * 
	 * @param row
	 *            row number of the ship
	 * @param column
	 *            column number of the ship
	 * @return
	 */
	Boolean getHit(int row, int column) {
		if (row < 10 && column < 10) {
			if (getShipArray()[row][column].horizontal) {
				if (column - getShipArray()[row][column].getBowColumn() < 4
						&& column - getShipArray()[row][column].getBowColumn() >= 0
						&& getShipArray()[row][column].hit[column - getShipArray()[row][column].getBowColumn()]) {
					return true;
				}
			} else {
				if (row - getShipArray()[row][column].getBowRow() < 4
						&& row - getShipArray()[row][column].getBowRow() >= 0
						&& getShipArray()[row][column].hit[row - getShipArray()[row][column].getBowRow()]) {
					return true;

				}
			}
		}
		return false;
	}

	/**
	 * Prints the ocean.
	 * 
	 * @return string value of a certain point in the ocean
	 */
	void print() {
		for (int i = 1; i < shipPrint.length; i++) {
			shipPrint[i][0] = String.valueOf(i - 1);
			for (int j = 1; j < shipPrint[0].length; j++) {

				if (getShipArray()[i - 1][j - 1].isHorizontal()) {
					if (j - 1 - getShipArray()[i - 1][j - 1].getBowColumn() < 4
							&& getShipArray()[i - 1][j - 1].hit[j - 1 - getShipArray()[i - 1][j - 1].getBowColumn()]) {
						shipPrint[i][j] = ships[i - 1][j - 1].toString();
					} else if (!isOccupied(i - 1, j - 1) && getShipArray()[i - 1][j - 1].hit[0]) {
						shipPrint[i][j] = ships[i - 1][j - 1].toString();
					} else {
						shipPrint[i][j] = ".";
					}
				} else {
					if (i - 1 - getShipArray()[i - 1][j - 1].getBowRow() < 4
							&& getShipArray()[i - 1][j - 1].hit[i - 1 - getShipArray()[i - 1][j - 1].getBowRow()]) {
						shipPrint[i][j] = ships[i - 1][j - 1].toString();
					} else if (!isOccupied(i - 1, j - 1) && getShipArray()[i - 1][j - 1].hit[0]) {
						shipPrint[i][j] = ships[i - 1][j - 1].toString();
					} else {
						shipPrint[i][j] = ".";
					}
				}
			}
		}

		for (int j = 1; j < shipPrint[0].length; j++) {
			shipPrint[0][j] = String.valueOf(j - 1);
		}

		shipPrint[0][0] = "0,0";
		for (String[] row : shipPrint) {
			printRow(row);
		}
	}

	private void printRow(String[] row) {
		for (String i : row) {
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
	}

}
