package org.bigdata.matrixes;

import java.util.List;
public class CoordinateMatrix {

    public List<Integer> rows;
    public List<Integer> cols;
    public List<Double> values;

    public CoordinateMatrix(List<Integer> rows, List<Integer> cols, List<Double> values) {
        this.rows = rows;
        this.cols = cols;
        this.values = values;
    }

    public int getRow(int index) {
        if (index >= 0 && index < rows.size()) {
            return rows.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public int getCol(int index) {
        if (index >= 0 && index < cols.size()) {
            return cols.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public double getValue(int index) {
        if (index >= 0 && index < values.size()) {
            return values.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    public int getNumRows() {
        return rows.stream().mapToInt(Integer::intValue).max().orElse(-1) + 1;
    }

    public int getNumCols() {
        return cols.stream().mapToInt(Integer::intValue).max().orElse(-1) + 1;
    }

    public int getSize() {
        return rows.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(getRow(i)).append(" ");
            sb.append(getCol(i)).append(" ");
            sb.append(getValue(i)).append("\n");
        }
        return sb.toString();
    }
}
