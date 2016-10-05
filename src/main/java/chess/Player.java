package chess;

/**
 * Which side of the board is being played
 */
public enum Player {
    White(1), Black(-1);

    private final int rowDirection;

    Player(int rowDirection) {
        this.rowDirection = rowDirection;
    }

    public Player getOther() {
        return Player.values()[1 - ordinal()];
    }

    /**
     * Direction of row flow. E.g. how vector moves are applied
     * Used for pawns
     */
    public int getRowDirection() {
        return rowDirection;
    }
}
