package battleship;

/**
 * Describes a ship of length 3.
 * 
 * @author Rui Yang
 *
 */
public class Cruiser extends Ship {

	public Cruiser() {
		this.length = 3;
		for (int i = 0; i < 3; i++) {
			this.hit[i] = false;
		}
	}

	/**
	 * Returns "cruiser".
	 */
	@Override
	String getShipType() {
		return "cruiser";
	}

	@Override
	int getLength() {
		return length;
	}

}
