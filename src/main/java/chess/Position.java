package chess;

/**
 * Describes a position on the Chess Board
 */
public class Position {
    public static final int MIN_ROW = 0;
    public static final int MAX_ROW = 7;
    public static final int MIN_COLUMN = 0;
    public static final int MAX_COLUMN = 7;
    private final int row;
    private final int column;

    /**
     * Create a new position object
     *
     * @param column The column
     * @param row    The row
     */
    public Position(int column, int row) {
        if (column < MIN_COLUMN || column > MAX_COLUMN || row < MIN_ROW || row > MAX_ROW)
            throw new IllegalArgumentException("Invalid colrow");
        this.column = column;
        this.row = row;
    }

    /**
     * Create a new Position object by parsing the string
     *
     * @param colrow The column and row to use.  I.e. "a1", "h7", etc.
     */
    public Position(String colrow) {
        this(colrow.toCharArray()[0] - 'a', colrow.toCharArray()[1] - '1');
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    public static char getColumnLabel(int column) {
        return (char) ('a' + column);
    }

    public static char getRowLabel(int row) {
        return (char) ('1' + row);
    }

    public char getColumnLabel() {
        return getColumnLabel(column);
    }

    public char getRowLabel() {
        return getRowLabel(row);
    }

    @Override
    public String toString() {
        return "" + getColumnLabel() + getRowLabel();
    }
}
