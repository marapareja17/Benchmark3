package org.bigdata.matrixes;

public class CRS_Matrix {
    public static int[] rowPointers;
    public static int[] columnIndices;
    public static double[] values;

    public CRS_Matrix(int[] rowPointers, int[] columnIndices, double[] values) {
        this.rowPointers = rowPointers;
        this.columnIndices = columnIndices;
        this.values = values;
    }

    public static void printCRSMatrix(CRS_Matrix matrix) {
        for (int i = 0; i < matrix.rowPointers.length - 1; i++) {
            int start = matrix.rowPointers[i];
            int end = matrix.rowPointers[i + 1];
            for (int j = start; j < end; j++) {
                int colIndex = matrix.columnIndices[j];
                double value = matrix.values[j];
                System.out.println("Row: " + i + ", Column: " + colIndex + ", Value: " + value);
            }
        }
    }
}
