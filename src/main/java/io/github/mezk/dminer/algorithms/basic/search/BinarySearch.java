package io.github.mezk.dminer.algorithms.basic.search;

/**
 * Implements binary search algorithm for int.
 * Binary search works on sorted arrays.
 * A binary search begins by comparing the middle element of the array with the target value.
 * If the target value matches the middle element, its position in the array is returned.
 * If the target value is less or more than the middle element, the search continues the lower
 * or upper half of the array respectively with a new middle element, eliminating the other half
 * from consideration.
 *
 * <p>For more info, please visit
 * <a href="https://en.wikipedia.org/wiki/Binary_search_algorithm">binary search page</a>
 * at <a href="https://en.wikipedia.org/wiki/Main_Page">Wikipedia</a>.
 *
 * @author Andrei Selkin
 */
public final class BinarySearch {

    /** Private constructor. */
    private BinarySearch() { }

    /**
     * Performs binary search in array of int starting with fromIndex and ending at toIndex.
     * @param key key to search.
     * @param array array in which to search.
     * @param fromIndex start index.
     * @param toIndex end index.
     * @return the index of the search key, it it is found in the array,
     *         or -(insertion point - 1). The insertion point is the point
     *         at which the key should be inserted into the array: the index of the first
     *         element greater than the key, or array.length if all
     *         elements in the array are less than the specified key.
     *         Note that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int search(int key, int[] array, int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex, array.length);
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            final int mid = (low + high) >>> 1;
            if (key < array[mid]) {
                high = mid - 1;
            }
            else if (key > array[mid]) {
                low = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -(1 + low);
    }

    /**
     * Performs binary search in array of int starting with 0 and ending at array.length - 1.
     * @param key key to search.
     * @param array array in which to search..
     * @return the index of the search key, it it is found in the array,
     *         or -(insertion point - 1). The insertion point is the point
     *         at which the key should be inserted into the array: the index of the first
     *         element greater than the key, or array.length if all
     *         elements in the array are less than the specified key.
     *         Note that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int search(int key, int[] array) {
        return search(key, array, 0, array.length);
    }

    /**
     * Checks whether range conditions are satisfied.
     * @param fromIndex start index.
     * @param toIndex end index.
     * @param arrayLength length of the array.
     */
    private static void checkRange(int fromIndex, int toIndex, int arrayLength) {
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
        if (fromIndex == toIndex && fromIndex != 0) {
            throw new IllegalArgumentException("fromIndex = " + fromIndex
                + " is equal to toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex = " + fromIndex
                + " > toIndex = " + toIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }
}
