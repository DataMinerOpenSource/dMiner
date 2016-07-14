package io.github.mezk.dminer.utils;

/**
 * Util class for sequences generation.
 *
 * @author Andrei Selkin
 */
public final class SequenceGenerators {

    /** Private constructor. */
    private SequenceGenerators() { }

    /**
     * Generates integer sequence from start index to end index: [start, end].
     * @param start start index.
     * @param end end index.
     * @return an array of generated integers.
     */
    public static int[] generateIntSequence(int start, int end) {
        final int[] array = new int[end - start + 1];
        int value = start;
        for (int i = 0; value <= end; i++) {
            array[i] = value;
            value++;
        }
        return array;
    }

    /**
     * Generates integer sequence from zero to end index [0, end].
     * @param end end index.
     * @return an array of generated integers.
     */
    public static int[] generateIntSequence(int end) {
        return generateIntSequence(0, end);
    }

    public static double[] generateDoubleSequence(int start, int end) {
        final double[] array = new double[end - start + 1];
        double value = start;
        for (int i = 0; value <= end; i++) {
            array[i] = value;
            value++;
        }
        return array;
    }

    public static double[] generateDoubleSequence(int end) {
        return generateDoubleSequence(0, end);
    }

    public static float[] generateFloatSequence(int start, int end) {
        final float[] array = new float[end - start + 1];
        float value = start;
        for (int i = 0; value <= end; i++) {
            array[i] = value;
            value++;
        }
        return array;
    }

    public static float[] generateFloatSequence(int end) {
        return generateFloatSequence(0, end);
    }
}
