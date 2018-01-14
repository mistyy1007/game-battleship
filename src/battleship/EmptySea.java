package battleship;

/**
 * Describes a part of the ocean that doesn't have a ship in it.
 */
public class EmptySea extends Ship {

	EmptySea() {
		this.length = 1;
		this.hit[0] = false;
	}

	@Override
	boolean shootAt(int row, int column) {
		this.hit[0] = true;
		return false;
	}

	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * Returns a single-character String "-" to use in the Ocean's print method
	 * (see below).
	 */
	@Override
	public String toString() {
		if (hit[0]) {
			return "-";
		}
		return ".";
	}

	@Override
	int getLength() {
		return length;
	}

	@Override
	String getShipType() {
		return null;
	}

}
