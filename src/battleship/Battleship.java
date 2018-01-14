package battleship;

/**
 * Describes a ship of length 4.
 * 
 * @author Rui Yang
 *
 */
public class Battleship extends Ship {

	public Battleship() {
		this.length = 4;
		for (int i = 0; i < 4; i++) {
			this.hit[i] = false;
		}
	}

	/**
	 * Returns "battleship".
	 */
	@Override
	String getShipType() {
		return "battleship";
	}

	@Override
	int getLength() {
		return length;
	}

}
