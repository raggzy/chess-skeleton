package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction for holding vectors of moves and vectors of fights, and whether vectors are "continuous"
 * All chess figures are working in this way
 */
public abstract class VectorMovingPiece extends Piece {
    protected VectorMovingPiece(Player owner) {
        super(owner);
    }

    /**
     * Determines where the move is "single vector jump" or "multiple"
     */
    protected abstract boolean continuousMove();

    /**
     * Possible directions of moves.
     * For directional moves - assume vectors are for WHITE (e.g. top row)
     * Position is bassed in case it depends from position (for pawns it is)
     */
    protected abstract List<Vector> getMoveVectors(Position from);

    /**
     * Possible directions of fight.
     * For directional moves - assume vectors are for WHITE (e.g. top row)
     * For most it's the same as move.
     * Overriden for pawns
     * Position is bassed in case it depends from position
     */
    protected List<Vector> getFightVectors(Position from) {
        return getMoveVectors(from);
    }

    @Override
    public List<Position> getValidMoves(Position from, GameState gameState) {
        boolean continuous = continuousMove();
        List<Position> positions = new ArrayList<Position>();
        // finding where we can move
        for (Vector vector : getMoveVectors(from)) {
            Position pos = from;
            do {
                pos = gameState.apply(pos, vector, getOwner());
                if (pos == null) break;
                Piece pieceAtPos = gameState.getPieceAt(pos);
                if (pieceAtPos != null) break;
                positions.add(pos);
            } while (continuous);
        }
        // finding what we can fight
        for (Vector vector : getFightVectors(from)) {
            Position pos = from;
            do {
                pos = gameState.apply(pos, vector, getOwner());
                if (pos == null) break;
                Piece pieceAtPos = gameState.getPieceAt(pos);
                if (pieceAtPos != null) {
                    if (pieceAtPos.getOwner() != getOwner()) {
                        positions.add(pos);
                    }
                    break;
                }
            } while (continuous);
        }
        return positions;
    }
}
