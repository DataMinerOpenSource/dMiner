package io.github.mezk.dminer.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * Util-class that contains methods to regress input files for OLS module.
 * Input file format: *.txt
 *
 * <p>File data format:
 * <code>
 * x1[separator]y1
 * ...
 * xn[separator]yn
 * </code>
 *
 * <p>Separators: user predefined. Default are space and new line.
 * @author Andrei Selkin
 */
public final class DataFileReader {

    /** Default constructor. */
    private DataFileReader() { }

    /**
     * Reads data from input file into two dimensional double array.
     * @param filePath input file path.
     * @param separators separators.
     * @return two dimensional double array with input data.
     * @throws IOException in case of an I/O error.
     */
    public static double[][] readData(String filePath, String separators) throws IOException {
        final File file = new File(filePath);
        final String fileContent = FileUtils.readFileToString(file);
        return convertIntoTwoDimensionalDoubleArray(fileContent, separators);
    }

    /**
     * Parses input file content represented as string in two dimensional double array.
     * @param input file content represented as string.
     * @param separators separators.
     * @return two dimensional double array with input data.
     */
    private static double[][] convertIntoTwoDimensionalDoubleArray(String input,
                                                                   String separators) {
        final double[][] outArray;

        final CharMatcher allowedSeparators = CharMatcher.anyOf(
            separators + System.lineSeparator() + ' ');
        final List<String> inputData = Splitter.on(allowedSeparators)
            .trimResults().omitEmptyStrings().splitToList(input);

        if (inputData.size() % 2 == 0) {
            final int numberOfArguments = inputData.size() / 2;
            outArray = new double[2][numberOfArguments];
            for (int i = 0, j = 0; i < numberOfArguments; i++, j += 2) {
                outArray[0][i] = Double.valueOf(inputData.get(j));
                outArray[1][i] = Double.valueOf(inputData.get(j + 1));
            }
        }
        else {
            throw new IllegalArgumentException("Wrong number of arguments in the input file");
        }
        return outArray;
    }
}
