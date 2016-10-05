package chess.pieces;

import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * The Knight class
 */
public class Knight extends VectorMovingPiece {
    private static final List<Vector> MOVE_VECTORS = Arrays.asList(
            new Vector(1, 2),
            new Vector(1, -2),
            new Vector(-1, 2),
            new Vector(-1, -2),
            new Vector(2, 1),
            new Vector(2, -1),
            new Vector(-2, 1),
            new Vector(-2, -1)
    );

    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    protected boolean continuousMove() {
        return false;
    }

    @Override
    protected List<Vector> getMoveVectors(Position from) {
        return MOVE_VECTORS;
    }
}
