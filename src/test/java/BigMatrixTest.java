import org.bigdata.MatrixReader;
import org.bigdata.builders.*;
import org.bigdata.matrixes.*;
import org.bigdata.operations.*;

import java.util.List;

import org.junit.Test;

public class BigMatrixTest {

    @Test
    public void testSparseBigMatrixMultiplication() {
        System.out.println("In this test we are running the big matrix to see how long it takes to execute as sparse multiplication.");

        MatrixReader bigMatrixReader = new MatrixReader();
        List<String> bigMatrixLines = bigMatrixReader.readMatrix("2100225.mtx");

        CoordinateMatrix bigCoordinateMatrix = CoordinateMatrixBuilder.buildCoordinateMatrix(bigMatrixLines);

        CRS_Matrix bigCRS = MatrixConverter.convertToCRS(bigCoordinateMatrix);
        CCS_Matrix bigCCS = MatrixConverter.convertToCCS(bigCoordinateMatrix);

        MatrixMultiplier bigMatrixMultiplier = new MatrixMultiplier();

        long BstartTime = System.currentTimeMillis();
        CoordinateMatrix bigResult = bigMatrixMultiplier.multiply(bigCRS, bigCCS);
        long BendTime = System.currentTimeMillis();
        long BexecutionTime = BendTime - BstartTime;

        System.out.println("Total Sparse matrix multiplication run time: " + BexecutionTime + " milliseconds");
    }
}
