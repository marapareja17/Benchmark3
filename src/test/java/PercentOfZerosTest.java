import org.bigdata.MatrixReader;
import org.bigdata.builders.CoordinateMatrixBuilder;
import org.bigdata.builders.DenseMatrixConverter;
import org.bigdata.builders.MatrixConverter;
import org.bigdata.matrixes.CCS_Matrix;
import org.bigdata.matrixes.CRS_Matrix;
import org.bigdata.matrixes.CoordinateMatrix;
import org.bigdata.matrixes.DenseMatrix;
import org.bigdata.operations.DenseMatrixMultiplier;
import org.bigdata.operations.MatrixMultiplier;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PercentOfZerosTest {
    private static final String[] matrixFiles = {
            "percentage_matrixes/20percent.mtx",
            "percentage_matrixes/40percent.mtx",
            "percentage_matrixes/60percent.mtx",
            "percentage_matrixes/80percent.mtx"
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
