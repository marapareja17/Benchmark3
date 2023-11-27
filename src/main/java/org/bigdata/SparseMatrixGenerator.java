package org.bigdata;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class SparseMatrixGenerator {

    public static void main(String[] args) {
        // Tamaño de la matriz
        int rows = 256;
        int cols = 256;

        // Porcentajes de ceros
        int[] zeroPercentages = {20, 40, 60, 80};

        // Generar matrices y escribir en archivos .mtx en el paquete resources
        for (int percentage : zeroPercentages) {
            double[][] matrix = generateSparseMatrix(rows, cols, percentage);

            // Escribir la matriz en un archivo .mtx en el paquete resources
            writeMatrixToMTXFile(matrix, "src/main/resources/percentage_matrixes/" + percentage + "percent.mtx");
        }
    }

    // Generar una matriz dispersa con el porcentaje de ceros dado
    private static double[][] generateSparseMatrix(int rows, int cols, int zeroPercentage) {
        double[][] matrix = new double[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextInt(100) < zeroPercentage) {
                    // Asignar 0 si el número aleatorio está dentro del porcentaje especificado
                    matrix[i][j] = 0;
                } else {
                    // Generar un número aleatorio en el rango [1.0, 2.0) para los valores no nulos
                    matrix[i][j] = 1.0 + random.nextDouble();
                }
            }
        }

        return matrix;
    }

    // Escribir la matriz en un archivo .mtx en el paquete resources
    private static void writeMatrixToMTXFile(double[][] matrix, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat decimalFormat = new DecimalFormat("0.########", symbols);

            writer.printf("%%MatrixMarket matrix coordinate real general\n");
            writer.printf("%d %d %d\n", matrix.length, matrix[0].length, countNonZeroElements(matrix));

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != 0) {
                        writer.printf("%d %d %s\n", i + 1, j + 1, decimalFormat.format(matrix[i][j]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Contar los elementos no nulos en la matriz
    private static int countNonZeroElements(double[][] matrix) {
        int count = 0;
        for (double[] row : matrix) {
            for (double value : row) {
                if (value != 0) {
                    count++;
                }
            }
        }
        return count;
    }

}
