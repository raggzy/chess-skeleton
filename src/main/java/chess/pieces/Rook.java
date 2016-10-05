package chess.pieces;

import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.List;

/**
 * The 'Rook' class
 */
public class Rook extends VectorMovingPiece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    protected boolean continuousMove() {
        return true;
    }

    @Override
    protected List<Vector> getMoveVectors(Position from) {
        return Vector.ROW_COL;
    }
}
