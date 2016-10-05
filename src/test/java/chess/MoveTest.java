package chess;

import org.junit.Assert;
import org.junit.Test;

public class MoveTest {
    @Test
    public void testValidParse() {
        Move move = new Move("e2", "e4");
        Assert.assertEquals(new Position("e2"), move.getFrom());
        Assert.assertEquals(new Position("e4"), move.getTo());
        Assert.assertEquals(move.toString(), "e2 e4");
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(new Move("e2", "e4").equals(new Move("e2", "e4")));
    }
}
