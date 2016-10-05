package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.List;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();

    /**
     * Get the valid move positions as if this piece standed
     *
     * @param from
     * @param gameState
     * @return
     */
    public abstract List<Position> getValidMoves(Position from, GameState gameState);
}
