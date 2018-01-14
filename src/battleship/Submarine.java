package battleship;

/**
 * Describes a ship of length 1
 * 
 * @author Rui Yang
 *
 */
public class Submarine extends Ship {

	public Submarine() {
		this.length = 1;
		for (int i = 0; i < 1; i++) {
			this.hit[i] = false;

		}
	}

	/**
	 * Returns "submarine".
	 */
	@Override
	String getShipType() {
		return "submarine";
	}

	@Override
	int getLength() {
		return length;
	}

}
