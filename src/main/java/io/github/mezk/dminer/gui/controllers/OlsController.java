package io.github.mezk.dminer.gui.controllers;

import io.github.mezk.dminer.gui.views.FrameShower;
import io.github.mezk.dminer.gui.views.OlsPanel;
import io.github.mezk.dminer.regression.ols.OrdinaryLeastSquares;
import io.github.mezk.dminer.regression.ols.RegressionResults;
import io.github.mezk.dminer.regression.ols.SimpleOlsFactory;
import io.github.mezk.dminer.utils.DataFileReader;
import io.github.mezk.dminer.utils.LocalizedMessage;
import io.github.mezk.dminer.utils.StatsUtils;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.sourceforge.jeuclid.swing.JMathComponent;
import uk.ac.ed.ph.snuggletex.SnuggleEngine;
import uk.ac.ed.ph.snuggletex.SnuggleInput;
import uk.ac.ed.ph.snuggletex.SnuggleSession;

import com.google.common.primitives.Doubles;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesColor;
import com.xeiam.xchart.SeriesLineStyle;
import com.xeiam.xchart.SeriesMarker;

public class OlsController {

    private static final String SEPARATORS = "|/;_ ";
    private static final double OUTPUT_GRAPH_DISCRITIZATION_VALUE = 0.01;
    private static LocalizedMessage messages = new LocalizedMessage("messages");

    /**
     * Open a new input file for OLS.
     * @param file the file to open.
     * @param parent the parent panel.
     */
    public static void openFile(File file, final OlsPanel parent) {
        if (file != null) {
            try {
                final double[][] inputData =
                        DataFileReader.readData(file.getAbsolutePath(), SEPARATORS);
                parent.cleanPanel();
                parent.setInputData(inputData);
                setCorrelation(inputData, parent);

                Chart chart = new ChartBuilder()
                        .xAxisTitle("X")
                        .yAxisTitle("Y")
                        .build();
                Series series = chart.addSeries(messages.getMessage("gui.ols.chart.input"),
                        inputData[0], inputData[1]);
                series.setLineStyle(SeriesLineStyle.NONE);
                parent.setChart(chart);
            }
            catch (final IOException | IllegalArgumentException ex) {
                showErrorDialog(
                        parent,
                        "Could not parse" + file + ": " + ex.getMessage());
            }
        }
    }

    public static void process(OlsPanel panel) {
        final double[][] inputData = panel.getInputData();
        final int algorithmIndex = panel.getAlgorithmIndex();
        final OrdinaryLeastSquares ols = SimpleOlsFactory.createOls(algorithmIndex);
        final RegressionResults result = ols.regress(inputData);

        panel.setOutputData(new double [][]{inputData[0], result.getPredictedFunctionValues()});
        setCorrelation(inputData, panel);
        
        String func = SimpleOlsFactory.createOlsFunction(algorithmIndex,
                result.getCoefficientA(), result.getCoefficientB());
        panel.setFunction(getFunction(panel.getEngine(), func));
        
        drawOutputChart(panel, ols, inputData, result);
    }

    private static JMathComponent getFunction(SnuggleEngine engine, String func){
        SnuggleInput input = new SnuggleInput(func);
        SnuggleSession session = engine.createSession();
        try {
            session.parseInput(input);
        } catch (IOException ex) {
            // cannot happen
        }
        String xmlString = session.buildXMLString();
        JMathComponent formula = new JMathComponent();
        formula.setContent(xmlString);
        return formula;
    }

    private static void drawOutputChart(OlsPanel panel, final OrdinaryLeastSquares ols,
            final double[][] inputData, final RegressionResults result) {
        Chart chart = new ChartBuilder()
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();
        chart.getSeriesMap().clear();
        double[][] generatedValues = generateChartValues(ols, result,
                Doubles.min(inputData[0]), Doubles.max(inputData[0]));
        Series series = chart.addSeries(messages.getMessage("gui.ols.chart.input"),
                inputData[0], inputData[1]);
        series.setLineStyle(SeriesLineStyle.NONE);
        series.setMarkerColor(SeriesColor.BLUE);
        series.setMarker(SeriesMarker.CIRCLE);
        
        series = chart.addSeries(messages.getMessage("gui.ols.chart.output")
                , inputData[0], result.getPredictedFunctionValues());
        series.setLineStyle(SeriesLineStyle.NONE);
        series.setMarkerColor(SeriesColor.ORANGE);
        series.setMarker(SeriesMarker.TRIANGLE_DOWN);

        series = chart.addSeries(messages.getMessage("gui.ols.chart.function"),
                generatedValues[0], generatedValues[1]);
        series.setMarker(SeriesMarker.NONE);
        series.setLineColor(SeriesColor.PURPLE);
        
        panel.setChart(chart);
    }

    private static double[][] generateChartValues(OrdinaryLeastSquares ols, RegressionResults res,
                                            double minX, double maxX) {
        int pointsNumber = (int) ((maxX - minX) / OUTPUT_GRAPH_DISCRITIZATION_VALUE);
        double[][] output = new double[2][pointsNumber];
        output[0][0] = minX;
        for (int i = 1; i < output[0].length; i++) {
            output[0][i] = output[0][i-1] + OUTPUT_GRAPH_DISCRITIZATION_VALUE;
        }
        output[1] = ols.predictFunctionValues(output[0],
                res.getCoefficientA(), res.getCoefficientB());
        return output;
    }

    private static void setCorrelation(double[][] data, OlsPanel panel) {
        final double correlationCoefficient = StatsUtils.correlation(data[0], data[1]);
        panel.setCorrelationLabel("Correlation coeficient: "
                + String.format("%.4f", correlationCoefficient));
    }

    /**
     * Opens dialog with error.
     * @param parent Component for displaying errors.
     * @param msg Error message to display.
     */
    public static void showErrorDialog(final Component parent, final String msg) {
        final Runnable showError = new FrameShower(parent, msg, JOptionPane.ERROR_MESSAGE);
        SwingUtilities.invokeLater(showError);
    }
}
