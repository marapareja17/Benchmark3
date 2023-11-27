import org.bigdata.MatrixReader;
import org.bigdata.builders.*;
import org.bigdata.matrixes.*;
import org.bigdata.operations.*;

import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RigthMatrixMultiplicationResult {
    @Test
    public void testRigthMatrixMultiplicationResult() {
        System.out.println("In the following test we will check that the multiplication of matrixes is carried out correctly.");

        MatrixReader resultReader = new MatrixReader();
        List<String> expectedLines = resultReader.readMatrix("testMatrixResult.txt");

        CoordinateMatrix expectedMatrix = CoordinateMatrixBuilder.buildCoordinateMatrix(expectedLines);

        MatrixReader MatrixReader = new MatrixReader();
        List<String> MatrixLines = MatrixReader.readMatrix("testMatrix.txt");

        CoordinateMatrix CoordinateMatrix = CoordinateMatrixBuilder.buildCoordinateMatrix(MatrixLines);

        CRS_Matrix CRS = MatrixConverter.convertToCRS(CoordinateMatrix);
        CCS_Matrix CCS = MatrixConverter.convertToCCS(CoordinateMatrix);

        MatrixMultiplier MatrixMultiplier = new MatrixMultiplier();

        CoordinateMatrix result = MatrixMultiplier.multiply(CRS, CCS);

        for (int i = 0; i < result.getSize(); i++) {
            double expectedValue = expectedMatrix.getValue(i);
            double actualValue = result.getValue(i);
            assertEquals(expectedValue, actualValue, 0.001);
        }
        System.out.println("\nCorrect matrix multiplication test completed successfully.");
    }
}
