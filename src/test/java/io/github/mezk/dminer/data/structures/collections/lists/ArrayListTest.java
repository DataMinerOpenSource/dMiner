package io.github.mezk.dminer.data.structures.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import io.github.mezk.dminer.data.structures.collections.lists.ArrayList;
import io.github.mezk.dminer.data.structures.collections.lists.List;

public class ArrayListTest {

    @Test
    public void testDefaultConstructor() {
        final List<Integer> list = new ArrayList<>();
        final int expectedSize = 0;
        final int actualSize = list.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testCreateArrayListWithSpecifiedCapacity() {
        final int capacity = 10;
        final List<Integer> list = new ArrayList<>(capacity);
        final int expectedSize = 0;
        final int actualSize = list.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testCreateArrayListWithNegativeCapacity() {
        final int capacity = -10;
        try {
            new ArrayList<>(capacity);
            fail("IndexOutOfBounds exception is expected.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals("Wrong array length.", ex.getMessage());
        }
    }

    @Test
    public void testCreateArrayListBasedOnArray() {
        final String[] names = {"Andrei", "Jane", "Vasya", "Petya", "Pit", "John", "Kostya"};
        final String expected = "[Andrei, Jane, Vasya, Petya, Pit, John, Kostya]";
        final List<String> list = new ArrayList<>(names);
        final String actual = list.toString();
        assertEquals(names.length, list.size());
        assertFalse(list.isEmpty());
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        final String[] names = {"Andrei", "Jane", "Vasya", "Petya", "Pit", "John", "Kostya"};
        final String expected = "[Andrei, Jane, Vasya, Petya, Pit, John, Kostya]";
        final List<String> list = new ArrayList<>(names);
        final String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddFirst() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final ArrayList<Integer> list = new ArrayList<>(array);
        list.addFirst(0);
        list.addFirst(-1);
        list.addFirst(-2);
        list.addFirst(-3);
        list.addFirst(-4);
        final int expectedSize = 10;
        assertEquals(expectedSize, list.size());
        final String expectedArrayAsString = "[-4, -3, -2, -1, 0, 1, 2, 3, 4, 5]";
        assertEquals(expectedArrayAsString, list.toString());
    }

    @Test
    public void testAddLast() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final ArrayList<Integer> list = new ArrayList<>(array);
        list.addLast(0);
        list.addLast(-1);
        list.addLast(-2);
        list.addLast(-3);
        list.addLast(-4);
        final int expectedSize = 10;
        assertEquals(expectedSize, list.size());
        final String expectedArrayAsString = "[1, 2, 3, 4, 5, 0, -1, -2, -3, -4]";
        assertEquals(expectedArrayAsString, list.toString());
    }

    @Test
    public void testIterator() {
        final Integer[] array = {1, 2, 3, 4, null, 6, 7, 8, 9, null};
        final List<Integer> list = new ArrayList<>(array);
        int index = 0;
        for (Integer i : list) {
            assertEquals(array[index++], i);
        }
    }

    @Test
    public void testGet() {
        final String[] names = {"Andrei", "Jane", "Vasya", "Petya", "Pit", "John", "Kostya"};
        final List<String> list = new ArrayList<>(names);
        assertEquals("Andrei", list.get(0));
    }

    @Test
    public void testGetNegativeIndex() {
        final String[] names = {"Andrei", "Jane", "Vasya", "Petya", "Pit", "John", "Kostya"};
        final List<String> list = new ArrayList<>(names);
        try {
            list.get(-1);
            fail("IndexOutOfBoundsException is expected.");
        }
        catch (IndexOutOfBoundsException ex) {

        }
    }

    @Test
    public void testGetIndexWhichIsGreaterThanListSize() {
        final String[] names = {"Andrei", "Jane", "Vasya", "Petya", "Pit", "John", "Kostya"};
        final List<String> list = new ArrayList<>(names);
        try {
            list.get(10);
            fail("IndexOutOfBoundsException is expected.");
        }
        catch (IndexOutOfBoundsException ex) {

        }
    }

    @Test
    public void testContains() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final List<Integer> list = new ArrayList<>(array);
        assertTrue(list.contains(4));
    }

    @Test
    public void testDoesNotContain() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final List<Integer> list = new ArrayList<>(array);
        assertFalse(list.contains(0));
    }

    @Test
    public void testIndexOfNegative() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final ArrayList<Integer> list = new ArrayList<>(array);
        assertEquals(-1, list.indexOf(0));
    }

    @Test
    public void testIndexOfPositive() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final ArrayList<Integer> list = new ArrayList<>(array);
        assertEquals(1, list.indexOf(2));
    }

    @Test
    public void testReverse() {
        final Integer[] array = {1, 2, 3, 4, 5};
        final ArrayList<Integer> list = new ArrayList<>(array);
        final String reversed = "[5, 4, 3, 2, 1]";
        list.reverse();
        assertEquals(reversed, list.toString());
    }
}
