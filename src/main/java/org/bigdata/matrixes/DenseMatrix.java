package org.bigdata.matrixes;

public class DenseMatrix {
    private double[][] matrix;

    public DenseMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double get(int row, int col) {
        return matrix[row][col];
    }

    public void set(int row, int col, double value) {
        matrix[row][col] = value;
    }

    public int getNumRows() {
        return matrix.length;
    }

    public int getNumCols() {
        if (matrix.length == 0) {
            return 0;
        }
        return matrix[0].length;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void printMatrix() {
        int numRows = getNumRows();
        int numCols = getNumCols();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
