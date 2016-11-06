package io.github.mezk.dminer.gui.models;

import org.apache.commons.math3.optim.linear.Relationship;

/**
 * Table Model for SimplexOptimizer constraints.
 * @author Vladislav Lisetskiy
 */
public class ConstraintsTableModel extends AbstractSimplexOptimizerTableModel {

    /** Serial ID. */
    private static final long serialVersionUID = 7463365944921771714L;

    /**
     * Create an empty table.
     * @param rowNumber the number of rows.
     * @param colNumber the number of columns.
     */
    public ConstraintsTableModel(int rowNumber, int colNumber) {
        super(new Object[rowNumber][colNumber + 2]);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> result;
        if (columnIndex == getColumnCount() - 2) {
            result = Relationship.class;
        }
        else {
            result = Double.class;
        }
        return result;
    }

    @Override
    public String getColumnName(int column) {
        final String name;
        if (column < getColumnCount() - 2) {
            name = "x" + (column + 1);
        }
        else {
            name = "";
        }
        return name;
    }
}
