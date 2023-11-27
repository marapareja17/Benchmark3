package org.bigdata.builders;

import org.bigdata.matrixes.CoordinateMatrix;
import org.bigdata.matrixes.DenseMatrix;

public class DenseMatrixConverter {
    public static DenseMatrix convertToDenseMatrix(CoordinateMatrix coordinateMatrix) {
        int numRows = coordinateMatrix.getNumRows();
        int numCols = coordinateMatrix.getNumCols();
        double[][] matrix = new double[numRows][numCols];

        for (int i = 0; i < coordinateMatrix.getSize(); i++) {
            int row = coordinateMatrix.getRow(i);
            int col = coordinateMatrix.getCol(i);
            double value = coordinateMatrix.getValue(i);
            matrix[row][col] = value;
        }

        return new DenseMatrix(matrix);
    }
}

