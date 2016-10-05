package chess;

public class Move {
    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Move(String from, String to) {
        this(new Position(from), new Position(to));
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!from.equals(move.from)) return false;
        return to.equals(move.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "" + from + " " + to;
    }
}
