package chess;

import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (int col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(new Position(col, row)));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }

    @Test
    public void testCurrentPlayerMoves() {
        state.reset();
        List<Move> currentPlayerMoves = state.getCurrentPlayerMoves();
        Assert.assertTrue("White should be able to do e2e4", currentPlayerMoves.contains(new Move("e2", "e4")));
    }

    @Test
    public void testMove() {
        state.reset();
        state.validateAndMove(new Move("e2", "e4"));
        assertEquals("Now it shouldbe black turn", Player.Black, state.getCurrentPlayer());
        Assert.assertTrue("Black shouldbe able to do b7b5", state.getCurrentPlayerMoves().contains(new Move("b7", "b5")));
    }
}
