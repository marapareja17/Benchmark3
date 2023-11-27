package org.bigdata.builders;

import org.bigdata.matrixes.CCS_Matrix;
import org.bigdata.matrixes.CRS_Matrix;
import org.bigdata.matrixes.CoordinateMatrix;


public class MatrixConverter {
    public static CRS_Matrix convertToCRS(CoordinateMatrix coordinateMatrix) {
        int numRows = getNumRows(coordinateMatrix);
        int numNonZeros = coordinateMatrix.rows.size();

        int[] rowPointers = new int[numRows + 1];
        int[] columnIndices = new int[numNonZeros];
        double[] values = new double[numNonZeros];


        // Initialises all rowPointers values to 0
        for (int i = 0; i < numRows; i++) {
            rowPointers[i] = 0;
        }

        for (int i = 0; i < numNonZeros; i++) {
            int row = coordinateMatrix.rows.get(i);
            rowPointers[row]++;
        }

        // Calculate the cumulative values of rowPointers
        int cumSum = 0;
        for (int i = 0; i < numRows; i++) {
            int rowNonZeros = rowPointers[i];
            rowPointers[i] = cumSum;
            cumSum += rowNonZeros;
        }
        rowPointers[numRows] = numNonZeros;

        // Fills columnIndexes and values
        for (int i = 0; i < numNonZeros; i++) {
            int row = coordinateMatrix.rows.get(i);
            int rowNonZeros = rowPointers[row];
            columnIndices[rowNonZeros] = coordinateMatrix.cols.get(i);
            values[rowNonZeros] = coordinateMatrix.values.get(i);
            rowPointers[row]++;
        }

        // Reset rowPointers values
        int previousValue = 0;
        for (int i = 0; i <= numRows; i++) {
            int originalValue = rowPointers[i];
            rowPointers[i] = previousValue;
            previousValue = originalValue;
        }
        return new CRS_Matrix(rowPointers, columnIndices, values);
    }

    public static CCS_Matrix convertToCCS(CoordinateMatrix coordinateMatrix) {
        int numRows = getNumRows(coordinateMatrix);
        int numNonZeros = coordinateMatrix.rows.size();

        int[] columnPointers = new int[numRows + 1];
        int[] rowIndices = new int[numNonZeros];
        double[] values = new double[numNonZeros];

        int currentCol = 0;

        for (int col = 0; col < numRows; col++) {
            columnPointers[col] = currentCol;
            for (int i = 0; i < numNonZeros; i++) {
                if (coordinateMatrix.cols.get(i) == col) {
                    rowIndices[currentCol] = coordinateMatrix.rows.get(i);
                    values[currentCol] = coordinateMatrix.values.get(i);
                    currentCol++;
                }
            }
        }

        columnPointers[numRows] = numNonZeros;

        return new CCS_Matrix(columnPointers, rowIndices, values);
    }

    private static int getNumRows(CoordinateMatrix cooMatrix) {
        int maxRow = -1;
        for (int row : cooMatrix.rows) {
            if (row > maxRow) {
                maxRow = row;
            }
        }
        return maxRow + 1;
    }
}
