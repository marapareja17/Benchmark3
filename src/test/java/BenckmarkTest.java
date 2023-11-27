
import org.bigdata.MatrixReader;
import org.bigdata.builders.*;
import org.bigdata.matrixes.*;
import org.bigdata.operations.*;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class BenckmarkTest {
    private static final String[] matrixFiles = {
            "size_matrixes/130.mtx",
            "size_matrixes/420.mtx",
            "size_matrixes/662.mtx",
            "size_matrixes/1074.mtx",
            "size_matrixes/1473.mtx"
    };

    @Test
    public void testSparseMatrixMultiplication() {
        System.out.println("\nRunning test for sparse matrix multiplication...");

        for (String matrixFile : matrixFiles) {
            MatrixReader matrixReader = new MatrixReader();
            List<String> matrixLines = matrixReader.readMatrix(matrixFile);

            CoordinateMatrix coordinateMatrix = CoordinateMatrixBuilder.buildCoordinateMatrix(matrixLines);

            CRS_Matrix CRS = MatrixConverter.convertToCRS(coordinateMatrix);
            CCS_Matrix CCS = MatrixConverter.convertToCCS(coordinateMatrix);

            MatrixMultiplier matrixMultiplier = new MatrixMultiplier();

            long startTime = System.currentTimeMillis();
            CoordinateMatrix result = matrixMultiplier.multiply(CRS, CCS);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("Matrix: " + matrixFile + " - Sparse Matrix run time: " + executionTime + " milliseconds");

            assertNotNull(result);
        }

        System.out.println("Sparse matrix multiplication test completed successfully.");
    }

    @Test
    public void testDenseMatrixMultiplication() {
        System.out.println("\nRunning test for dense matrix multiplication...");

        for (String matrixFile : matrixFiles) {
            MatrixReader matrixReader = new MatrixReader();
            List<String> matrixLines = matrixReader.readMatrix(matrixFile);

            CoordinateMatrix coordinateMatrix = CoordinateMatrixBuilder.buildCoordinateMatrix(matrixLines);

            DenseMatrix denseMatrix = DenseMatrixConverter.convertToDenseMatrix(coordinateMatrix);

            DenseMatrixMultiplier denseMatrixMultiplier = new DenseMatrixMultiplier();
            double[][] matrixA = denseMatrix.getMatrix();
            double[][] matrixB = denseMatrix.getMatrix();

            long startTime = System.currentTimeMillis();
            double[][] resultMatrix = denseMatrixMultiplier.multiply(matrixA, matrixB);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("Matrix: " + matrixFile + " - Dense Matrix run time: " + executionTime + " milliseconds");

            assertNotNull(resultMatrix);
        }

        System.out.println("Dense matrix multiplication test completed successfully.");
    }
}
