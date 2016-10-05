package chess;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Basic Unit Tests for the Position class
 */
public class PositionTest {

    @Test
    public void testStringParsingConstructor() {
        Position pos = new Position("d5");

        assertEquals("The column should be d", 'd', pos.getColumnLabel());
        assertEquals("The row should be 5", '5', pos.getRowLabel());
    }

    @Test
    public void testPositionEquality() {
        Position one = new Position("a1");
        Position other = new Position("a1");

        assertEquals("The positions should equal each other", one, other);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid() {
        new Position("p0");
    }
}
