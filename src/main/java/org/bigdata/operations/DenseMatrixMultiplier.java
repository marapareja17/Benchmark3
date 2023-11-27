package org.bigdata.operations;

public class DenseMatrixMultiplier {
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        int numRowsA = matrixA.length;
        int numColsA = matrixA[0].length;
        int numColsB = matrixB[0].length;

        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied. Incompatible dimensions.");
        }

        double[][] resultMatrix = new double[numRowsA][numColsB];

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                for (int k = 0; k < numColsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return resultMatrix;
    }
}
