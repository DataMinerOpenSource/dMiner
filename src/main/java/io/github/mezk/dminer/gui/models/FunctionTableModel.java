package io.github.mezk.dminer.gui.models;

/**
 * Table Model for SimplexOptimizer result.
 * @author Vladislav Lisetskiy
 */
public class FunctionTableModel extends AbstractSimplexOptimizerTableModel {

    /** Serial ID. */
    private static final long serialVersionUID = -7123777162653579352L;

    /**
     * Create a table from the given data.
     * @param data the data of the table.
     */
    public FunctionTableModel(Object[][] data) {
        super(data);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    @Override
    public String getColumnName(int column) {
        return "x" + (column + 1);
    }
}
