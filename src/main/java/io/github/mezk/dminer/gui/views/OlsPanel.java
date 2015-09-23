package io.github.mezk.dminer.gui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import net.sourceforge.jeuclid.swing.JMathComponent;

import uk.ac.ed.ph.snuggletex.SnuggleEngine;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.XChartPanel;

import io.github.mezk.dminer.gui.Main;
import io.github.mezk.dminer.gui.controllers.OlsController;
import io.github.mezk.dminer.gui.models.DataTableModel;

public class OlsPanel extends JPanel {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4056066432573944945L;
    private JPanel chartPanel;
    private JTable inputDataTable;
    private JTable outputDataTable;
    private JComboBox<String> algorithmComboBox;
    private JLabel correlationLabel;
    private JPanel functionPanel;
    private SnuggleEngine engine = new SnuggleEngine();

    private final String[] comboBoxItems = {
        "LinearOrdinaryLeastSquares",
        "ExponentialOrdinaryLeastSquares",
        "FractionalLinearOrdinaryLeastSquares",
        "HyperbolicOrdinaryLeastSquares",
        "LogarithmicOrdinaryLeastSquares",
        "PowerOrdinaryLeastSquares",
        "RationalOrdinaryLeastSquares",
    };

    /**
     * Create the frame.
     */
    public OlsPanel() {
        final GridBagLayout gblOlsPanel = new GridBagLayout();
        gblOlsPanel.columnWidths = new int[]{1, 305, 90, 482, 0};
        gblOlsPanel.rowHeights = new int[] {1, 30, 91, 0};
        gblOlsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gblOlsPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
        setLayout(gblOlsPanel);

        final Component verticalStrut = Box.createVerticalStrut(20);
        final GridBagConstraints gbcVerticalStrut = new GridBagConstraints();
        gbcVerticalStrut.anchor = GridBagConstraints.WEST;
        gbcVerticalStrut.fill = GridBagConstraints.VERTICAL;
        gbcVerticalStrut.insets = new Insets(0, 0, 5, 5);
        gbcVerticalStrut.gridx = 0;
        gbcVerticalStrut.gridy = 0;
        add(verticalStrut, gbcVerticalStrut);
        algorithmComboBox = new JComboBox<>(comboBoxItems);
        algorithmComboBox.setBackground(Color.WHITE);
        final GridBagConstraints gbcAlgorithmComboBox = new GridBagConstraints();
        gbcAlgorithmComboBox.anchor = GridBagConstraints.NORTHWEST;
        gbcAlgorithmComboBox.insets = new Insets(5, 0, 5, 5);
        gbcAlgorithmComboBox.gridx = 1;
        gbcAlgorithmComboBox.gridy = 0;
        add(algorithmComboBox, gbcAlgorithmComboBox);

        final JButton processButton = new JButton("Process");
        processButton.addActionListener(new ProcessButtonListener());
        final GridBagConstraints gbcProcessButton = new GridBagConstraints();
        gbcProcessButton.anchor = GridBagConstraints.NORTHWEST;
        gbcProcessButton.insets = new Insets(5, 0, 5, 5);
        gbcProcessButton.gridx = 2;
        gbcProcessButton.gridy = 0;
        add(processButton, gbcProcessButton);

        chartPanel = new JPanel();
        chartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanel.setLayout(new BoxLayout(chartPanel, BoxLayout.X_AXIS));
        final GridBagConstraints gbcChartPanel = new GridBagConstraints();
        gbcChartPanel.fill = GridBagConstraints.BOTH;
        gbcChartPanel.insets = new Insets(5, 0, 5, 5);
        gbcChartPanel.gridheight = 3;
        gbcChartPanel.gridx = 3;
        gbcChartPanel.gridy = 0;
        add(chartPanel, gbcChartPanel);

        final JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        final GridBagLayout gblTablePanel = new GridBagLayout();
        gblTablePanel.columnWidths = new int[]{142, 150, 0};
        gblTablePanel.rowHeights = new int[]{15, 364, 0};
        gblTablePanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gblTablePanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        tablePanel.setLayout(gblTablePanel);

        final JLabel labelInputData = new JLabel("Input data");
        labelInputData.setLabelFor(labelInputData);
        labelInputData.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbcLabelInputData = new GridBagConstraints();
        gbcLabelInputData.anchor = GridBagConstraints.NORTH;
        gbcLabelInputData.fill = GridBagConstraints.HORIZONTAL;
        gbcLabelInputData.insets = new Insets(0, 0, 5, 5);
        gbcLabelInputData.gridx = 0;
        gbcLabelInputData.gridy = 0;
        tablePanel.add(labelInputData, gbcLabelInputData);

        final JLabel labelCalculatedData = new JLabel("Calculated data");
        labelCalculatedData.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbcLabelCalculatedData = new GridBagConstraints();
        gbcLabelCalculatedData.anchor = GridBagConstraints.NORTH;
        gbcLabelCalculatedData.fill = GridBagConstraints.HORIZONTAL;
        gbcLabelCalculatedData.insets = new Insets(0, 0, 5, 0);
        gbcLabelCalculatedData.gridx = 1;
        gbcLabelCalculatedData.gridy = 0;
        tablePanel.add(labelCalculatedData, gbcLabelCalculatedData);

        final JScrollPane inputDataScrollPane = new JScrollPane();
        final GridBagConstraints gbcInputDataScrollPane = new GridBagConstraints();
        gbcInputDataScrollPane.fill = GridBagConstraints.BOTH;
        gbcInputDataScrollPane.insets = new Insets(0, 5, 5, 5);
        gbcInputDataScrollPane.gridx = 0;
        gbcInputDataScrollPane.gridy = 1;
        tablePanel.add(inputDataScrollPane, gbcInputDataScrollPane);

        inputDataTable = new JTable();
        inputDataTable.setModel(new DataTableModel());
        inputDataTable.setColumnSelectionAllowed(true);
        inputDataTable.setCellSelectionEnabled(true);
        inputDataScrollPane.setViewportView(inputDataTable);

        final JScrollPane outputDataScrollPane = new JScrollPane();
        final GridBagConstraints gbcOutputDataScrollPane = new GridBagConstraints();
        gbcOutputDataScrollPane.insets = new Insets(0, 0, 5, 5);
        gbcOutputDataScrollPane.fill = GridBagConstraints.BOTH;
        gbcOutputDataScrollPane.gridx = 1;
        gbcOutputDataScrollPane.gridy = 1;
        tablePanel.add(outputDataScrollPane, gbcOutputDataScrollPane);

        outputDataTable = new JTable();
        outputDataTable.setModel(new DataTableModel());
        outputDataTable.setColumnSelectionAllowed(true);
        outputDataTable.setCellSelectionEnabled(true);
        outputDataScrollPane.setViewportView(outputDataTable);
        final GridBagConstraints gbcTablePanel = new GridBagConstraints();
        gbcTablePanel.fill = GridBagConstraints.BOTH;
        gbcTablePanel.insets = new Insets(0, 0, 0, 5);
        gbcTablePanel.gridheight = 3;
        gbcTablePanel.gridwidth = 2;
        gbcTablePanel.gridx = 1;
        gbcTablePanel.gridy = 1;
        add(tablePanel, gbcTablePanel);

        final JPanel resultPanel = new JPanel();
        final GridBagConstraints gbcResultPanel = new GridBagConstraints();
        gbcResultPanel.insets = new Insets(0, 0, 5, 5);
        gbcResultPanel.fill = GridBagConstraints.BOTH;
        gbcResultPanel.gridx = 3;
        gbcResultPanel.gridy = 3;
        add(resultPanel, gbcResultPanel);
        final GridBagLayout gblResultPanel = new GridBagLayout();
        gblResultPanel.columnWidths = new int[] {120, 250, 120};
        gblResultPanel.rowHeights = new int[] {15, 60, 0};
        gblResultPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gblResultPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        resultPanel.setLayout(gblResultPanel);

        correlationLabel = new JLabel();
        correlationLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        correlationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final GridBagConstraints gbcCorrelationLabel = new GridBagConstraints();
        gbcCorrelationLabel.anchor = GridBagConstraints.NORTH;
        gbcCorrelationLabel.insets = new Insets(5, 0, 5, 0);
        gbcCorrelationLabel.gridx = 1;
        gbcCorrelationLabel.gridy = 0;
        resultPanel.add(correlationLabel, gbcCorrelationLabel);

        functionPanel = new JPanel();
        final GridBagConstraints gbcFunctionPanel = new GridBagConstraints();
        gbcFunctionPanel.fill = GridBagConstraints.BOTH;
        gbcFunctionPanel.gridx = 1;
        gbcFunctionPanel.gridy = 1;
        resultPanel.add(functionPanel, gbcFunctionPanel);
        functionPanel.setLayout(new BorderLayout(0, 0));
    }

    /**
     * Clean everything before opening a new input file.
     */
    public void cleanPanel() {
        ((DataTableModel) inputDataTable.getModel()).clear();
        ((DataTableModel) outputDataTable.getModel()).clear();
        correlationLabel.setText("");
        chartPanel.removeAll();
        chartPanel.repaint();
        functionPanel.removeAll();
        functionPanel.repaint();
    }

    public void setChart(Chart chart) {
        chartPanel.removeAll();
        chartPanel.add(new XChartPanel(chart));
    }

    public void setInputData(double[][] inputData) {
        inputDataTable.setModel(new DataTableModel(inputData[0], inputData[1], true));
    }

    public void setOutputData(double[][] outputData) {
        outputDataTable.setModel(new DataTableModel(outputData[0], outputData[1], false));
    }

    public double[][] getInputData() {
        return ((DataTableModel) inputDataTable.getModel()).getData();
    }

    public int getAlgorithmIndex() {
        return algorithmComboBox.getSelectedIndex();
    }

    public SnuggleEngine getEngine() {
        return engine;
    }

    public void setFunction(JMathComponent function) {
        functionPanel.removeAll();
        functionPanel.add(function);
        functionPanel.revalidate();
        functionPanel.repaint();
    }

    public void addRow() {
        ((DataTableModel) inputDataTable.getModel()).addRow();
    }

    public void removeRow() {
        ((DataTableModel) inputDataTable.getModel()).removeRow();
    }

    public void setCorrelationLabel(String text) {
        correlationLabel.setText(text);
    }

    private static class ProcessButtonListener extends AbstractAction {
        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 1578139330812671368L;

        /** Default constructor to setup current action. */
        ProcessButtonListener() {
            super("Process the input data");
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                final JButton sourceButton = (JButton) e.getSource();
                OlsController.process((OlsPanel) sourceButton.getParent());
            }
            catch (IllegalStateException ex) {
                OlsController.showErrorDialog(Main.getFrame(),
                        "Please, enter at least two input values.");
            }
            catch (IllegalArgumentException ex) {
                OlsController.showErrorDialog(Main.getFrame(),
                        "Please, fill the input table.");
            }
        }
    }
}
