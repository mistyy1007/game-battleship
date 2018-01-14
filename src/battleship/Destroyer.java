package battleship;

/**
 * Describes a ship of length 2
 * 
 * @author Rui Yang
 *
 */
public class Destroyer extends Ship {

	public Destroyer() {
		this.length = 2;
		for (int i = 0; i < 2; i++) {
			this.hit[i] = false;

		}
	}

	/**
	 * Returns "destroyer".
	 */
	@Override
	String getShipType() {
		return "destroyer";
	}

	@Override
	int getLength() {
		return length;
	}

}
