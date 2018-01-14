package battleship;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

public class OceanTest {
	private static Ocean oc;

	@BeforeClass
	public static void beforeClass() {
		oc = new Ocean();
	}

	@Test
	public void testIsOccupied() {
		Assert.assertFalse(oc.isOccupied(0, 0));
	}

	public void testIsOccupisdTrue() {
		Ocean ocean = new Ocean();
		Ship submarine = new Submarine();
		submarine.placeShipAt(0, 0, true, ocean);
		Assert.assertTrue(ocean.isOccupied(0, 0));
	}

	@Test
	public void testShootAt() {
		Ship submarine = new Submarine();
		submarine.placeShipAt(0, 0, true, oc);
		Assert.assertTrue(oc.shootAt(0, 0));

		Ship battleship = new Battleship();
		boolean shot = true;
		battleship.placeShipAt(0, 5, true, oc);
		for (int i = 5; i < 9; i++) {
			if (!oc.shootAt(0, i)) {
				shot = false;
			}
		}
		Assert.assertTrue(shot);
	}

	@Test
	public void testShotsFiredAndHitCountAndShipsSunk() {
		Ocean ocean = new Ocean();
		Ship submarine = new Submarine();
		submarine.placeShipAt(1, 1, true, ocean);
		ocean.shootAt(0, 0);
		Assert.assertEquals(0, ocean.getHitCount());
		ocean.shootAt(1, 1);
		Assert.assertEquals(1, ocean.getHitCount());
		Assert.assertEquals(2, ocean.getShotsFired());
		Assert.assertEquals(1, ocean.getShipsSunk());
	}

	@Test
	public void testIsGameOver() {
		Assert.assertFalse(oc.isGameOver());
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
			}
		}
		Assert.assertTrue(ocean.isGameOver());
	}

	@Test
	public void testGetShipArray() {
		Ship emptySea = new EmptySea();
		Ship[][] ships = oc.getShipArray();
		boolean isEmptySea = true;
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				if (!ships[i][j].toString().equals(emptySea.toString())) {
					isEmptySea = false;
				}
			}
		}
		Assert.assertTrue(isEmptySea);
	}

	@Test
	public void testGetHit() {
		Ship battleship = new Battleship();
		battleship.placeShipAt(0, 5, true, oc);
		oc.shootAt(0, 6);
		Assert.assertTrue(oc.getHit(0, 6));
	}

	@Test
	public void testPrint() {
		Ocean ocean = new Ocean();
		Ship submarine = new Submarine();
		submarine.placeShipAt(0, 6, true, ocean);
		ocean.print();
		Assert.assertEquals(ocean.shipPrint[1][6], ".");
	}

}
