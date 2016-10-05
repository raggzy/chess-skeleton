package chess.pieces;

import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.List;

/**
 * The 'Bishop' class
 */
public class Bishop extends VectorMovingPiece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    protected boolean continuousMove() {
        return true;
    }

    protected List<Vector> getMoveVectors(Position from) {
        return Vector.DIAG;
    }
}
