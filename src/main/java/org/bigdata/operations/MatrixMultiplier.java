package org.bigdata.operations;

import org.bigdata.matrixes.CCS_Matrix;
import org.bigdata.matrixes.CRS_Matrix;
import org.bigdata.matrixes.CoordinateMatrix;


import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplier {
    public CoordinateMatrix multiply(CRS_Matrix a, CCS_Matrix b) {
        int numRowsA = a.rowPointers.length - 1;
        int numRowsB = b.columnPointers.length - 1;

        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        List<Double> values = new ArrayList();

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numRowsB; j++) {
                int aStart = a.rowPointers[i];
                int aEnd = a.rowPointers[i + 1];
                int bStart = b.columnPointers[j];
                int bEnd = b.columnPointers[j + 1];

                double sum = 0.0;

                int aIndex = aStart;
                int bIndex = bStart;

                while (aIndex < aEnd && bIndex < bEnd) {
                    int aCol = a.columnIndices[aIndex];
                    int bRow = b.rowIndices[bIndex];

                    if (aCol == bRow) {
                        sum += a.values[aIndex] * b.values[bIndex];
                        aIndex++;
                        bIndex++;
                    } else if (aCol < bRow) {
                        aIndex++;
                    } else {
                        bIndex++;
                    }
                }

                if (sum != 0.0) {
                    rows.add(i);
                    cols.add(j);
                    values.add(sum);
                }
            }
        }

        return new CoordinateMatrix(rows, cols, values);
    }
}
