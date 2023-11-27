package org.bigdata.builders;

import org.bigdata.matrixes.CoordinateMatrix;

import java.util.ArrayList;
import java.util.List;

public class CoordinateMatrixBuilder {
    public static CoordinateMatrix buildCoordinateMatrix(List<String> matrixLines) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        for (String line : matrixLines) {
            String[] parts = line.trim().split("\\s+");

            int row = Integer.parseInt(parts[0]) - 1; // Adjust for an index based on 0
            int col = Integer.parseInt(parts[1]) - 1;
            double value = Double.parseDouble(parts[2]);

            rows.add(row);
            cols.add(col);
            values.add(value);
        }

        return new CoordinateMatrix(rows, cols, values);
    }
}
