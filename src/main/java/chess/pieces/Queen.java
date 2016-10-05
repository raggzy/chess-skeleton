package chess.pieces;

import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.List;

/**
 * The Queen
 */
public class Queen extends VectorMovingPiece {
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    protected boolean continuousMove() {
        return true;
    }

    @Override
    protected List<Vector> getMoveVectors(Position from) {
        return Vector.BOTH;
    }
}
