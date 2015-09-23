package io.github.mezk.dminer.gui.models;

import javax.swing.table.AbstractTableModel;

/**
 * Abstract class for SimplexOptimizer table models.
 * @author Vladislav Lisetskiy
 */
public abstract class AbstractSimplexOptimizerTableModel extends AbstractTableModel {

    /** Serial ID. */
    private static final long serialVersionUID = 1900851580626054198L;

    /** Table's data. */
    private Object[][] data;

    /**
     * Create a table.
     * @param data the data to create the table from.
     */
    public AbstractSimplexOptimizerTableModel(Object[][] data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        this.data[rowIndex][columnIndex] = value;
    }

    public Object[][] getData() {
        return data;
    }
}
