package io.github.mezk.dminer.utils;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class DataFuleReaderTest {

    @Test
    public void testReadDataLineSeparator() throws Exception {
        final String separator = " | ";
        final String inputFilePath = "src/test/resources/"
            + "io/github/mezk/dminer/utils/InputLineSeparator.txt";
        final double[][] actuals = DataFileReader.readData(inputFilePath, separator);
        final double[][] expecteds = {
            {1.0, 8.0, 9.0, 84.0, 98.0},
            {4.5, 5.0, 4.12, 11.6, 12.9},
        };
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testReadDataNewLineSeparator() throws Exception {
        final String separator = System.lineSeparator();
        final String inputFilePath = "src/test/resources/"
            + "io/github/mezk/dminer/utils/InputNewLineSeparator.txt";
        final double[][] actuals = DataFileReader.readData(inputFilePath, separator);
        final double[][] expecteds = {
            {1.0, 8.0, 9.0, 84.0, 98.0},
            {4.5, 5.0, 4.12, 11.6, 12.9},
        };
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testReadDataSemicolonSeparator() throws Exception {
        final String separator = ";";
        final String inputFilePath = "src/test/resources/"
            + "io/github/mezk/dminer/utils/InputSemicolonSeparator.txt";
        final double[][] actuals = DataFileReader.readData(inputFilePath, separator);
        final double[][] expecteds = {
            {1.0, 8.0, 9.0, 84.0, 98.0},
            {4.5, 5.0, 4.12, 11.6, 12.9},
        };
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testReadDataSlashSeparator() throws Exception {
        final String separator = "/";
        final String inputFilePath = "src/test/resources/"
            + "io/github/mezk/dminer/utils/InputSlashSeparator.txt";
        final double[][] actuals = DataFileReader.readData(inputFilePath, separator);
        final double[][] expecteds = {
            {1.0, 8.0, 9.0, 84.0, 98.0},
            {4.5, 5.0, 4.12, 11.6, 12.9},
        };
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testReadDataSpaceSeparator() throws Exception {
        final String separator = " ";
        final String inputFilePath = "src/test/resources/"
            + "io/github/mezk/dminer/utils/InputSpaceSeparator.txt";
        final double[][] actuals = DataFileReader.readData(inputFilePath, separator);
        final double[][] expecteds = {
            {1.0, 8.0, 9.0, 84.0, 98.0},
            {4.5, 5.0, 4.12, 11.6, 12.9},
        };
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentsNumber() throws Exception {
        final String separator = " | ";
        final String inputFilePath = "src/test/resources/"
            + "io/github/mezk/dminer/utils/InputWrongArgumentsNumber.txt";
        DataFileReader.readData(inputFilePath, separator);
    }

    @Test(expected = FileNotFoundException.class)
    public void testNoSuchFile() throws Exception {
        final String separator = " ";
        final String inputFilePath = "NonExistingFile.txt";
        DataFileReader.readData(inputFilePath, separator);
    }

    @Test
    public void testInvokePrivateConstructor() throws Exception {
        TestUtils.assertUtilsClassHasPrivateConstructor(DataFileReader.class);
    }
}
