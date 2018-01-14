package battleship;

import org.junit.Test;
import org.junit.Assert;

public class ShipTest {

	private Ship testEmptySea = new EmptySea();
	private Ship testSubmarine = new Submarine();
	private Ship testDestroyer = new Destroyer();
	private Ship testCruiser = new Cruiser();
	private Ship testBattleship = new Battleship();

	@Test
	public void testLength() {
		Assert.assertEquals(testSubmarine.getLength(), 1);
		Assert.assertEquals(testDestroyer.getLength(), 2);
		Assert.assertEquals(testCruiser.getLength(), 3);
		Assert.assertEquals(testBattleship.getLength(), 4);
		Assert.assertEquals(testEmptySea.getLength(), 1);
	}

	@Test
	public void testSetAndGetBowRow() {
		testBattleship.setBowRow(0);
		Assert.assertEquals(0, testBattleship.getBowRow());

	}

	@Test
	public void testSetAndFetColumn() {
		testBattleship.setBowColumn(4);
		Assert.assertEquals(4, testBattleship.getBowColumn());
	}

	@Test
	public void testHorizontal() {
		testBattleship.setHorizontal(false);
		Assert.assertFalse(testBattleship.isHorizontal());
	}

	@Test
	public void testGetShipType() {
		Assert.assertEquals("submarine", testSubmarine.getShipType());
		Assert.assertEquals("destroyer", testDestroyer.getShipType());
		Assert.assertEquals("cruiser", testCruiser.getShipType());
		Assert.assertEquals("battleship", testBattleship.getShipType());
	}

	@Test
	public void testOkToPlaceShipAndPlace() {
		Ocean oc = new Ocean();
		Ship submarine = new Submarine();
		Ship submarineOne = new Submarine();
		Ship submarineTwo = new Submarine();
		Ship battleshipOne = new Battleship();
		Ship battleshipTwo = new Battleship();
		Assert.assertTrue(submarine.okToPlaceShipAt(0, 0, true, oc));
		submarineOne.placeShipAt(0, 0, true, oc);
		Assert.assertFalse(submarineTwo.okToPlaceShipAt(0, 0, true, oc));
		Assert.assertFalse(submarineTwo.okToPlaceShipAt(0, 1, true, oc));
		battleshipOne.placeShipAt(0, 0, true, oc);
		Assert.assertFalse(battleshipTwo.okToPlaceShipAt(0, 3, true, oc));
		Assert.assertFalse(battleshipTwo.okToPlaceShipAt(1, 0, true, oc));
		Assert.assertFalse(battleshipTwo.okToPlaceShipAt(0, 8, true, oc));
	}

	@Test
	public void testShootAtAndIsSunk() {
		Ship cruiser = new Cruiser();
		cruiser.setBowRow(0);
		cruiser.setBowColumn(0);
		cruiser.setHorizontal(false);
		cruiser.shootAt(0, 0);
		cruiser.shootAt(1, 0);
		cruiser.shootAt(2, 0);
		Assert.assertTrue(cruiser.isSunk());

		Ship cruiserTwo = new Cruiser();
		cruiserTwo.setBowColumn(0);
		cruiserTwo.setBowRow(0);
		cruiserTwo.setHorizontal(false);
		Assert.assertTrue(cruiserTwo.shootAt(0, 0));
		Assert.assertFalse(cruiserTwo.isSunk());
	}

	@Test
	public void testToString() {

	}
}
