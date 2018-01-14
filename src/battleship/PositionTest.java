package battleship;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {
	Position position = new Position(3, 7);

	@Test
	public void testSetAndGetColumn() {
		position.setColumn(0);
		position.setRow(9);
		Assert.assertEquals(0, position.getColumn());
		Assert.assertEquals(9, position.getRow());
	}
}
