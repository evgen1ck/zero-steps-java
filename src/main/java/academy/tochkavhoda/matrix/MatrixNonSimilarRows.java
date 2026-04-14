package academy.tochkavhoda.matrix;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MatrixNonSimilarRows {

    private final int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Set<int[]> result = new LinkedHashSet<>();
        Set<Set<Integer>> seen = new HashSet<>();
        for (int[] row : matrix) {
            Set<Integer> rowSet = new HashSet<>();
            for (int value : row) {
                rowSet.add(value);
            }
            if (seen.add(rowSet)) {
                result.add(row);
            }
        }
        return result;
    }
}
