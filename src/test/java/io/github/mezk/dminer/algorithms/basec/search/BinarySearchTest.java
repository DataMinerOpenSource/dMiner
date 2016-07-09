package io.github.mezk.dminer.algorithms.basec.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
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

    @Test
    public void testSearchInArrayOfIntOverflow() {
        // implement later
        // now it has problem with heap size
    }
}
