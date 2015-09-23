package io.github.mezk.dminer.gui.models;

/**
 * Table Model for SimplexOptimizer result.
 * @author Vladislav Lisetskiy
 */
public class ResultTableModel extends AbstractSimplexOptimizerTableModel {

    /** Serial ID. */
    private static final long serialVersionUID = -5785265577116261097L;

    /**
     * Create an empty table.
     * @param colNumber the number of columns.
     */
    public ResultTableModel(int colNumber) {
        super(new Object[1][colNumber + 1]);
    }

    /**
     * Create a table from the given data.
     * @param data the data to create the table from.
     */
    public ResultTableModel(Object[][] data) {
        super(data);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    @Override
    public String getColumnName(int column) {
        final String name;
        if (column < getColumnCount() - 1) {
            name = "x" + (column + 1);
        }
        else {
            name = "f(x)";
        }
        return name;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
