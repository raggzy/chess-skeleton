package chess.pieces;

import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * The Pawn. The trickest one. Has logic
 */
public class Pawn extends VectorMovingPiece {
    private static final List<Vector> FIGHT_VECTORS = Arrays.asList(new Vector(1, 1), new Vector(1, -1));
    private static final List<Vector> MOVE_VECTORS = Arrays.asList(new Vector(1, 0));
    private static final List<Vector> MOVE_VECTORS_2_ROW = Arrays.asList(new Vector(1, 0), new Vector(2, 0));

    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    protected boolean continuousMove() {
        return false;
    }

    protected List<Vector> getMoveVectors(Position from) {
        switch (getOwner()) {
            case White:
                return from.getRow() == 1 ? MOVE_VECTORS_2_ROW : MOVE_VECTORS;
            case Black:
                return from.getRow() == 6 ? MOVE_VECTORS_2_ROW : MOVE_VECTORS;
            default:
                throw new RuntimeException("Invalid owner, can't do anything");
        }
    }

    @Override
    protected List<Vector> getFightVectors(Position from) {
        return FIGHT_VECTORS;
    }
}
