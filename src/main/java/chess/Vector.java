package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vector {
    private final int dRow;
    private final int dCol;

    public Vector(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public int getdRow() {
        return dRow;
    }

    public int getdCol() {
        return dCol;
    }

    private static <T> List<T> concat(List<? extends T>... collections) {
        List<T> result = new ArrayList<T>();
        for (List<? extends T> coll : collections) {
            result.addAll(coll);
        }
        return result;
    }

    public static List<Vector> ROW_COL = Arrays.asList(new Vector(1, 0), new Vector(-1, 0), new Vector(0, 1), new Vector(0, -1));
    public static List<Vector> DIAG = Arrays.asList(new Vector(1, 1), new Vector(-1, 1), new Vector(1, -1), new Vector(-1, -1));
    public static List<Vector> BOTH = concat(ROW_COL, DIAG);
}
