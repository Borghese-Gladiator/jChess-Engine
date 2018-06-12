import static org.junit.Assert.*;

import org.junit.Test;

import BoardMovement.Position;

public class testPosition {

	@Test
	public void test() {
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, 0);
		assertEquals(true, pos1.equals(pos2));
	}
}