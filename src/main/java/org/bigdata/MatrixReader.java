package org.bigdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixReader {
    public List<String> readMatrix(String fileName) {
        List<String> matrixLines = new ArrayList<>();

        try {
            String filePath = "src/main/resources/" + fileName;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            boolean firstLine = true;
            String line;

            while ((line = reader.readLine()) != null) {
                // Remove lines beginning with "%" because its metadata.
                if (!line.startsWith("%")) {
                    if (firstLine) {
                        firstLine = false;
                        continue;
                    }
                    matrixLines.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrixLines;
    }
}
