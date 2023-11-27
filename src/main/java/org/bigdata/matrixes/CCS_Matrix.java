package org.bigdata.matrixes;

public class CCS_Matrix {
    public int[] columnPointers;
    public int[] rowIndices;
    public double[] values;

    public CCS_Matrix(int[] columnPointers, int[] rowIndices, double[] values) {
        this.columnPointers = columnPointers;
        this.rowIndices = rowIndices;
        this.values = values;
    }

    public static void printCCSMatrix(CCS_Matrix matrix) {
        for (int i = 0; i < matrix.columnPointers.length - 1; i++) {
            int start = matrix.columnPointers[i];
            int end = matrix.columnPointers[i + 1];
            for (int j = start; j < end; j++) {
                int rowIndex = matrix.rowIndices[j];
                double value = matrix.values[j];
                System.out.println("Column: " + i + ", Row: " + rowIndex + ", Value: " + value);
            }
        }
    }
}
