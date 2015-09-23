package io.github.mezk.dminer.gui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import io.github.mezk.dminer.gui.models.AbstractSimplexOptimizerTableModel;
import io.github.mezk.dminer.gui.models.ConstraintsTableModel;
import io.github.mezk.dminer.gui.models.FunctionTableModel;
import io.github.mezk.dminer.gui.models.ResultTableModel;
import io.github.mezk.dminer.optimization.linear.SimplexOptimizer;
import io.github.mezk.dminer.utils.LocalizedMessage;

/**
 * 
 * @author Vladislav Lisetskiy
 */
public class SimplexPanel extends JPanel {
    private static final long serialVersionUID = -2684635876791732233L;
    private JTextField numberOfVariablesTextField;
    private JTextField numberOfConstraintsTextField;
    private JTable constraintsTable;
    private JTable coefficientsTable;
    private JTable resultTable;
    private JButton createTableButton;
    private JButton clearTableButton;
    private JButton optimizeButton;
    private JComboBox<GoalType> objectiveComboBox;
    private JComboBox<Relationship> tableComboBox = new JComboBox<>(Relationship.values());
    private static LocalizedMessage messages = new LocalizedMessage("messages");

    /**
     * Create the panel.
     */
    public SimplexPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {200, 670, 0};
        gridBagLayout.rowHeights = new int[]{502, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JPanel paramPanel = new JPanel();
        paramPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        GridBagConstraints gbc_paramPanel = new GridBagConstraints();
        gbc_paramPanel.fill = GridBagConstraints.BOTH;
        gbc_paramPanel.insets = new Insets(0, 0, 0, 5);
        gbc_paramPanel.gridx = 0;
        gbc_paramPanel.gridy = 0;
        add(paramPanel, gbc_paramPanel);
        paramPanel.setLayout(null);

        JLabel numberOfVariablesLabel = new JLabel(
                messages.getMessage("gui.simplex.variables.field"));
        numberOfVariablesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfVariablesLabel.setBounds(12, 12, 162, 15);
        paramPanel.add(numberOfVariablesLabel);

        numberOfVariablesTextField = new JTextField();
        numberOfVariablesTextField.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfVariablesTextField.setBounds(43, 39, 105, 19);
        paramPanel.add(numberOfVariablesTextField);
        numberOfVariablesTextField.setColumns(10);

        JLabel numberOfConstraintsLabel = new JLabel(
                messages.getMessage("gui.simplex.constraints.field"));
        numberOfConstraintsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfConstraintsLabel.setBounds(12, 70, 162, 15);
        paramPanel.add(numberOfConstraintsLabel);

        numberOfConstraintsTextField = new JTextField();
        numberOfConstraintsTextField.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfConstraintsTextField.setColumns(10);
        numberOfConstraintsTextField.setBounds(43, 97, 105, 19);
        paramPanel.add(numberOfConstraintsTextField);

        createTableButton = new JButton(
                messages.getMessage("gui.simplex.create.table"));
        createTableButton.addActionListener(new CreateTableButtonListener());
        createTableButton.setBounds(12, 187, 171, 25);
        paramPanel.add(createTableButton);

        clearTableButton = new JButton(
                messages.getMessage("gui.simplex.clear.table"));
        clearTableButton.addActionListener(new ClearTableButtonListener());
        clearTableButton.setEnabled(false);
        clearTableButton.setBounds(12, 224, 171, 25);
        paramPanel.add(clearTableButton);

        optimizeButton = new JButton(messages.getMessage("gui.simplex.optimize"));
        optimizeButton.addActionListener(new OptimizeButtonListener());
        optimizeButton.setEnabled(false);
        optimizeButton.setBounds(12, 261, 171, 25);
        paramPanel.add(optimizeButton);

        JLabel objectiveLabel = new JLabel(messages.getMessage("gui.simplex.objective"));
        objectiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        objectiveLabel.setBounds(34, 122, 126, 15);
        paramPanel.add(objectiveLabel);

        objectiveComboBox = new JComboBox<>(GoalType.values());
        objectiveComboBox.setBackground(Color.WHITE);
        objectiveComboBox.setFocusable(false);
        objectiveComboBox.setBounds(43, 149, 105, 19);
        paramPanel.add(objectiveComboBox);
        objectiveComboBox.setFocusable(false);

        JPanel tablePanel = new JPanel();
        GridBagConstraints gbc_tablePanel = new GridBagConstraints();
        gbc_tablePanel.fill = GridBagConstraints.BOTH;
        gbc_tablePanel.gridx = 1;
        gbc_tablePanel.gridy = 0;
        add(tablePanel, gbc_tablePanel);
        GridBagLayout gbl_tablePanel = new GridBagLayout();
        gbl_tablePanel.columnWidths = new int[]{657, 0};
        gbl_tablePanel.rowHeights = new int[] {15, 42, 15, 42, 15, 325, 0};
        gbl_tablePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_tablePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        tablePanel.setLayout(gbl_tablePanel);

        JLabel resultLable = new JLabel(messages.getMessage("gui.simplex.result"));
        GridBagConstraints gbc_resultLable = new GridBagConstraints();
        gbc_resultLable.anchor = GridBagConstraints.NORTH;
        gbc_resultLable.insets = new Insets(0, 0, 5, 0);
        gbc_resultLable.gridx = 0;
        gbc_resultLable.gridy = 0;
        tablePanel.add(resultLable, gbc_resultLable);
        resultLable.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane resultTableScrollPane = new JScrollPane();
        GridBagConstraints gbc_resultTableScrollPane = new GridBagConstraints();
        gbc_resultTableScrollPane.fill = GridBagConstraints.BOTH;
        gbc_resultTableScrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_resultTableScrollPane.gridx = 0;
        gbc_resultTableScrollPane.gridy = 1;
        tablePanel.add(resultTableScrollPane, gbc_resultTableScrollPane);
        resultTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        resultTable = new JTable();
        resultTable.setModel(new DefaultTableModel());
        resultTable.setRowSelectionAllowed(false);
        resultTableScrollPane.setViewportView(resultTable);

        JLabel coefficientsLabel = new JLabel(messages.getMessage("gui.simplex.coefficients"));
        GridBagConstraints gbc_coefficientsLabel = new GridBagConstraints();
        gbc_coefficientsLabel.anchor = GridBagConstraints.NORTH;
        gbc_coefficientsLabel.insets = new Insets(0, 0, 5, 0);
        gbc_coefficientsLabel.gridx = 0;
        gbc_coefficientsLabel.gridy = 2;
        tablePanel.add(coefficientsLabel, gbc_coefficientsLabel);
        coefficientsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane coefficientsTableScrollPane = new JScrollPane();
        GridBagConstraints gbc_coefficientsTableScrollPane = new GridBagConstraints();
        gbc_coefficientsTableScrollPane.fill = GridBagConstraints.BOTH;
        gbc_coefficientsTableScrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_coefficientsTableScrollPane.gridx = 0;
        gbc_coefficientsTableScrollPane.gridy = 3;
        tablePanel.add(coefficientsTableScrollPane, gbc_coefficientsTableScrollPane);
        coefficientsTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        coefficientsTable = new JTable();
        coefficientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        coefficientsTable.setRowSelectionAllowed(false);
        coefficientsTable.setModel(new DefaultTableModel());
        coefficientsTableScrollPane.setViewportView(coefficientsTable);

        JLabel constraintsLabel = new JLabel(messages.getMessage("gui.simplex.constraints"));
        constraintsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_constraintsLabel = new GridBagConstraints();
        gbc_constraintsLabel.fill = GridBagConstraints.VERTICAL;
        gbc_constraintsLabel.insets = new Insets(0, 0, 5, 0);
        gbc_constraintsLabel.gridx = 0;
        gbc_constraintsLabel.gridy = 4;
        tablePanel.add(constraintsLabel, gbc_constraintsLabel);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 5;
        tablePanel.add(scrollPane, gbc_scrollPane);

        constraintsTable = new JTable();
        scrollPane.setViewportView(constraintsTable);
    }

    private void setComboBoxColumn(TableColumn column) {
        column.setCellEditor(new DefaultCellEditor(tableComboBox));
        column.setResizable(false);
        column.setMaxWidth(30);
    }

    private void createTable() {
        int varNumber = Integer.valueOf(numberOfVariablesTextField.getText()); // TODO: check value
        resultTable.setModel(new ResultTableModel(varNumber));
        coefficientsTable.setModel(new FunctionTableModel(new Object[1][varNumber]));
        
        int constraintsNumber = Integer.valueOf(numberOfConstraintsTextField.getText()); // TODO: check value
        constraintsTable.setModel(new ConstraintsTableModel(constraintsNumber, varNumber));
        setComboBoxColumn(constraintsTable.getColumnModel().getColumn(varNumber));
    }

    private void optimize() {
        final GoalType goalType = (GoalType) objectiveComboBox.getSelectedItem();
        final Object[][] coefficientsTableData = ((AbstractSimplexOptimizerTableModel) coefficientsTable.getModel()).getData();
        final double[] coefficients = convertObjectArrayToDouble(coefficientsTableData[0]);
        final LinearObjectiveFunction function = new LinearObjectiveFunction(coefficients, 0);
        final Collection<LinearConstraint> constraints = new ArrayList<>();
        final Object[][] constraintsTableData = ((AbstractSimplexOptimizerTableModel) constraintsTable.getModel()).getData();
        for (Object[] row: constraintsTableData) {
            constraints.add(getLinearConstraintFromRow(row));
        }
        final SimplexOptimizer simplexOptimizer = new SimplexOptimizer(function, constraints, goalType);
        final PointValuePair solution = simplexOptimizer.optimize();
        final double [] resultValues = solution.getPoint();
        final Object[][] resultTableData = new Object[][] {ArrayUtils.toObject(getResultTableData(resultValues, function.value(solution.getPoint())))};
        resultTable.setModel(new ResultTableModel(resultTableData));
    }
    
    private boolean checkTextFields() {
        boolean result = true;
        final String constraints = numberOfConstraintsTextField.getText();
        final String variables = numberOfVariablesTextField.getText();
        if (constraints.trim().length() == 0 || variables.trim().length() == 0) {
            showErrorDialog(this, messages.getMessage("exception.simplex.fillParams"));
            result = false;
        }
        else {
            try {
                Integer.valueOf(constraints);
                Integer.valueOf(variables);
            }
            catch (NumberFormatException ex) {
                showErrorDialog(this, messages.getMessage("exception.simplex.enterNumber"));
                result = false;
            }
        }
        return result;
    }

    /**
     * TODO: extract this method from controllers in utils or somewhere else
     * Opens dialog with error.
     * @param parent Component for displaying errors.
     * @param msg Error message to display.
     */
    public static void showErrorDialog(final Component parent, final String msg) {
        final Runnable showError = new FrameShower(parent, msg, JOptionPane.ERROR_MESSAGE);
        SwingUtilities.invokeLater(showError);
    }

    private static double[] getResultTableData(double[] resultValues, double functionValue) {
        final double[] output =  new double[resultValues.length + 1];
        for (int i = 0; i < resultValues.length; i++) {
            output[i] = resultValues[i];
        }
        output[output.length - 1] = functionValue;
        return output;
    }

    private LinearConstraint getLinearConstraintFromRow(Object[] row) {
        final double[] coefficients = convertObjectArrayToDouble(ArrayUtils.subarray(row, 0, row.length - 2));
        final Relationship relationship = (Relationship) row[row.length - 2];
        final double value = (double) row[row.length - 1]; 
        return new LinearConstraint(coefficients, relationship, value);
    }

    private static double[] convertObjectArrayToDouble(Object[] inputArray) throws IllegalArgumentException {
        double[] result = new double[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == null) {
                throw new IllegalArgumentException("Please, fill the input data.");
            }
            result[i] = (Double) inputArray[i];
        }
        return result;
    }

    private void clear() {
        resultTable.setModel(new DefaultTableModel());
        coefficientsTable.setModel(new DefaultTableModel());
        constraintsTable.setModel(new DefaultTableModel());
    }

    private class CreateTableButtonListener extends AbstractAction {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 2679631982265305390L;

        @Override
        public void actionPerformed(ActionEvent event) {
            if (checkTextFields()) {
                numberOfVariablesTextField.setEditable(false);
                numberOfConstraintsTextField.setEditable(false);
                createTableButton.setEnabled(false);
                clearTableButton.setEnabled(true);
                optimizeButton.setEnabled(true);
                objectiveComboBox.setEnabled(false);
                createTable();
            }
        }
    }

    private class ClearTableButtonListener extends AbstractAction {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 2679631982265305390L;

        @Override
        public void actionPerformed(ActionEvent event) {
            numberOfVariablesTextField.setEditable(true);
            numberOfConstraintsTextField.setEditable(true);
            createTableButton.setEnabled(true);
            clearTableButton.setEnabled(false);
            optimizeButton.setEnabled(false);
            objectiveComboBox.setEnabled(true);
            clear();
        }
    }

    private class OptimizeButtonListener extends AbstractAction {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 2679631982265305390L;

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                optimize();
            }
            catch (IllegalArgumentException ex) {
                
            }
        }
    }
}
