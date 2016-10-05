package chess;

import chess.pieces.Pawn;
import chess.pieces.VectorMovingPiece;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestVectorMovingPiece {

    private static class TestPiece extends VectorMovingPiece {
        private TestPiece(Player owner) {
            super(owner);
        }

        @Override
        protected boolean continuousMove() {
            return true;
        }

        @Override
        protected List<Vector> getMoveVectors(Position from) {
            return VECTOR_MOVES;
        }

        @Override
        protected char getIdentifyingCharacter() {
            return 'T';
        }
    }

    // kinda bishop, but only forward :)
    private static final List<Vector> VECTOR_MOVES = Arrays.asList(new Vector(1, 1), new Vector(1, -1));
    private VectorMovingPiece piece = new TestPiece(Player.White);

    private GameState state = new GameState();

    @Test
    public void testValidMoves() {
        Assert.assertEquals(7, piece.getValidMoves(new Position(0, 0), state).size());
        // now we place something's WHITE in front of him
        state.placePiece(new Pawn(Player.White), new Position(3, 3));
        Assert.assertEquals(2, piece.getValidMoves(new Position(0, 0), state).size());
        // now we place tehre something BLACK - he can hit
        state.placePiece(new Pawn(Player.Black), new Position(3, 3));
        Assert.assertEquals(3, piece.getValidMoves(new Position(0, 0), state).size());
    }
}
