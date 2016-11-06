package io.github.mezk.dminer.gui.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Table Model for data in OLS.
 * @author Vladislav Lisetskiy
 */
public class DataTableModel extends AbstractTableModel {

    /** Serial ID. */
    private static final long serialVersionUID = 122439047044957057L;

    /** Column names. */
    private static final String[] COLUMN_NAMES = {"x", "y"};

    /** Whether the cells are editable. */
    private boolean isCellEditable = true;

    /** Contains table data. */
    private List<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();

    /**
     * Create an empty table.
     */
    public DataTableModel() {
        this.data.add(new ArrayList<Double>());
        this.data.add(new ArrayList<Double>());
    }

    /**
     * Create a table.
     * @param xInput values for the first column.
     * @param yInput values for the second column.
     * @param isCellEditable whether the cells in the table are editable.
     */
    public DataTableModel(double[] xInput, double[] yInput, boolean isCellEditable) {
        this.isCellEditable = isCellEditable;
        final List<Double> xDouble = Arrays.asList(ArrayUtils.toObject(xInput));
        final List<Double> yDouble = Arrays.asList(ArrayUtils.toObject(yInput));
        this.data.add(new ArrayList<Double>(xDouble));
        this.data.add(new ArrayList<Double>(yDouble));
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(columnIndex).get(rowIndex);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data.get(columnIndex).set(rowIndex, (Double) value);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return isCellEditable;
    }

    @Override
    public int getRowCount() {
        return data.get(0).size();
    }

    @Override
    public int getColumnCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    /**
     * Add empty row to the end of the table.
     */
    public void addRow() {
        data.get(0).add(null);
        data.get(1).add(null);
        fireTableRowsInserted(data.get(0).size(), data.get(0).size());
    }

    /**
     * Remove the last row of the table.
     */
    public void removeRow() {
        final int index = data.get(0).size() - 1;
        if (index >= 0) {
            data.get(0).remove(index);
            data.get(1).remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    /**
     * Get column with the specified index and convert it to a Double array.
     * @param index the index of the column.
     * @return column with the specified index converted to a Double array
     */
    public double[] getColumn(int index) {
        checkColumn(index);
        final Double[] column =  data.get(index).toArray(new Double[0]);
        return ArrayUtils.toPrimitive(column);
    }

    public double[][] getData() {
        return new double[][]{getColumn(0), getColumn(1)};
    }

    /**
     * Clear the table.
     */
    public void clear() {
        data.get(0).clear();
        data.get(1).clear();
        fireTableStructureChanged();
    }

    /**
     * Check whether the data in the column is valid.
     * @param index the index of the column to check.
     * @throws IllegalArgumentException if the column has empty cells.
     * @throws IllegalStateException if the column's size is less then the lest acceptable.
     */
    private void checkColumn(int index) {
        if (data.get(index).contains(null)) {
            throw new IllegalArgumentException();
        }
        if (data.get(index).size() < 2) {
            throw new IllegalStateException();
        }
    }
}
