package io.github.mezk.dminer.algorithms.basec.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.github.mezk.dminer.algorithms.basic.search.BinarySearch;
import io.github.mezk.dminer.utils.SequenceGenerators;
import io.github.mezk.dminer.utils.TestUtils;

public class BinarySearchTest {

    @Test
    public void testPrivateConstructor() throws ReflectiveOperationException {
        TestUtils.assertUtilsClassHasPrivateConstructor(BinarySearch.class);
    }

    @Test
    public void testSearchInArrayOfTwoIntValues() {
        final int[] array = {1, 2};
        assertEquals(-1, BinarySearch.search(0, array));
        assertEquals(0, BinarySearch.search(1, array));
        assertEquals(1, BinarySearch.search(2, array));
    }

    @Test
    public void testSearchInArrayOfOneIntValues() {
        final int[] array = {1};
        assertEquals(-1, BinarySearch.search(0, array));
        assertEquals(0, BinarySearch.search(1, array));
    }

    @Test
    public void testSearchInArrayOfZeroIntValues() {
        final int[] array = { };
        assertEquals(-1, BinarySearch.search(0, array));
    }

    @Test
    public void testSearchFirstValuesInArrayOfIntValues() {
        final int[] array = SequenceGenerators.generateIntSequence(1000);
        assertEquals(0, BinarySearch.search(0, array));
    }

    @Test
    public void testSearchLastValuesInArrayOfIntValues() {
        final int[] array = SequenceGenerators.generateIntSequence(1001);
        assertEquals(1000, BinarySearch.search(1000, array));
    }

    @Test
    public void testSearchNonExistingValuesInArrayOfIntValues() {
        final int[] array = {1, 4, 5, 8, 10, 12};
        assertEquals(-2, BinarySearch.search(3, array));
    }

    @Test
    public void testSearchInArrayOfIntValuesWhichHaveDuplicates() {
        final int[] array = {1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(2, BinarySearch.search(2, array));
        assertEquals(0, BinarySearch.search(1, array));
        assertEquals(11, BinarySearch.search(10, array));
    }

    @Test
    public void testSearchInArrayOfIntValuesFoundMiddle() {
        final int[] array = {1, 2, 4, 5, 6};
        assertEquals(2, BinarySearch.search(4, array));
    }

    @Test
    public void testSearchInArrayOfIntValuesFoundRight() {
        final int[] array = {1, 2, 4, 5, 6};
        assertEquals(4, BinarySearch.search(6, array));
    }

    @Test
    public void testSearchInArrayOfIntValuesFoundLeft() {
        final int[] array = {1, 2, 4, 5, 6};
        assertEquals(0, BinarySearch.search(1, array));
    }

    @Test
    public void testSearchInArrayOfIntWrongRange() {
        final int[] array = {1, 2, 4, 5, 6};
        try {
            BinarySearch.search(1, array, -1, 2);
            fail("ArrayIndexOutOfBoundsException is expected.");
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("Array index out of range: -1", ex.getMessage());
        }
        try {
            BinarySearch.search(1, array, 0, 10);
            fail("ArrayIndexOutOfBoundsException is expected.");
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("Array index out of range: 10", ex.getMessage());
        }
        try {
            BinarySearch.search(1, array, 1, -8);
            fail("ArrayIndexOutOfBoundsException is expected.");
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            assertEquals("Array index out of range: -8", ex.getMessage());
        }
        try {
            BinarySearch.search(1, array, 10, 1);
            fail("IllegalArgumentException is expected.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals("fromIndex = 10 > toIndex = 1", ex.getMessage());
        }
        try {
            BinarySearch.search(1, array, 1, 1);
            fail("IllegalArgumentException is expected.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals("fromIndex = 1 is equal to toIndex = 1", ex.getMessage());
        }
    }

    @Ignore
    @Test
    public void testSearchInArrayOfIntOverflow() {
        // implement later
        // now it has problem with heap size
    }

    @Test
    public void testSearchInArrayOfTwoDoubleValues() {
        final double[] array = {1.0d, 2.0d};
        assertEquals(-1, BinarySearch.search(0.0d, array));
        assertEquals(0, BinarySearch.search(1.0d, array));
        assertEquals(1, BinarySearch.search(2.0d, array));
    }

    @Test
    public void testSearchInArrayOfOneDoubleValues() {
        final double[] array = {1.0d};
        assertEquals(-1, BinarySearch.search(0.0d, array));
        assertEquals(0, BinarySearch.search(1.0d, array));
    }

    @Test
    public void testSearchInArrayOfZeroDoubleValues() {
        final double[] array = { };
        assertEquals(-1, BinarySearch.search(0.0d, array));
    }

    @Test
    public void testSearchFirstValuesInArrayOfDoubleValues() {
        final double[] array = SequenceGenerators.generateDoubleSequence(1000);
        assertEquals(0, BinarySearch.search(0.0d, array));
    }

    @Test
    public void testSearchLastValuesInArrayOfDoubleValues() {
        final double[] array = SequenceGenerators.generateDoubleSequence(1001);
        assertEquals(1000, BinarySearch.search(1000.0d, array));
    }

    @Test
    public void testSearchNonExistingValuesInArrayOfDoubleValues() {
        final double[] array = {1.0d, 4.0d, 5.0d, 8.0d, 10.0d, 12.0d};
        assertEquals(-2, BinarySearch.search(3.0d, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesWhichHaveDuplicates() {
        final double[] array = {1.0d, 1.0d, 2.0d, 2.0d, 3.0d, 4.0d,
                                5.0d, 6.0d, 7.0d, 8.0d, 9.0d, 10.0d};
        assertEquals(2, BinarySearch.search(2.0d, array));
        assertEquals(0, BinarySearch.search(1.0d, array));
        assertEquals(11, BinarySearch.search(10.0d, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesFoundMiddle() {
        final double[] array = {1.0d, 2.0d, 4.0d, 5.0d, 6.0d};
        assertEquals(2, BinarySearch.search(4.0d, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesFoundRight() {
        final double[] array = {1.0d, 2.0d, 4.0d, 5.0d, 6.0d};
        assertEquals(4, BinarySearch.search(6.0d, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesFoundLeft() {
        final double[] array = {1.0d, 2.0d, 4.0d, 5.0d, 6.0d};
        assertEquals(0, BinarySearch.search(1.0d, array));
    }

    @Test
    public void testSearchInArrayOfDoubleNanValuesFound() {
        final double[] array = {1.0d, Double.NaN, 2.0d};
        assertEquals(0, BinarySearch.search(1.0d, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesNanFound() {
        final double[] array = {1.0d, Double.NaN, 2.0d};
        assertEquals(1, BinarySearch.search(Double.NaN, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesPositiveInfinityFound() {
        final double[] array = {1.0d, Double.POSITIVE_INFINITY, 2.0d};
        assertEquals(1, BinarySearch.search(Double.POSITIVE_INFINITY, array));
    }

    @Test
    public void testSearchInArrayOfDoubleValuesNegativeInfinityFound() {
        final double[] array = {Double.NEGATIVE_INFINITY, 1.0d, 2.0d};
        assertEquals(0, BinarySearch.search(Double.NEGATIVE_INFINITY, array));
    }

    @Ignore
    @Test
    public void testSearchInArrayOfDoubleOverflow() {
        // implement later
        // now it has problem with heap size
    }
}
