package chess.pieces;

import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.List;

/**
 * The King class
 */
public class King extends VectorMovingPiece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    protected boolean continuousMove() {
        return false;
    }

    @Override
    protected List<Vector> getMoveVectors(Position from) {
        return Vector.BOTH;
    }
}
