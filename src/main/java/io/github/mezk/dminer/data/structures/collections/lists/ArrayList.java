package io.github.mezk.dminer.data.structures.collections.lists;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> extends AbstractList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 4;

    private int size;

    private E[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Wrong array length.");
        }
        array = (E[]) new Object[capacity];
    }

    public ArrayList(E[] array) {
        size = array.length;
        this.array = array;
    }

    @Override
    public void add(E element) {
        if (array.length == size) {
            extendArray();
        }
        array[size++] = element;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element, int position) {
        if (position > size) {
            throw new IndexOutOfBoundsException();
        }
        if (position == size) {
            extendArray();
        }
        else {
            extendArray(array.length + 1);
        }
        System.arraycopy(array, position, array, position + 1, size - position);
        array[position] = element;
        size++;
    }

    private void extendArray() {
        final int newLength = array.length == 0 ? DEFAULT_CAPACITY : array.length << 1;
        extendArray(newLength);
    }

    private void extendArray(int size) {
        array = Arrays.copyOf(array, size);
    }

    public void addFirst(E element) {
        add(element, 0);
    }

    public void addLast(E element) {
        add(element);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public E getFirst() {
        return null;
    }

    public E getLast() {
        return null;
    }

    @Override
    public boolean remove(E element) {
        boolean success = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                removeAt(i);
                success = true;
                break;
            }
        }
        return success;
    }

    public void removeAt(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        final int shiftStart = index + 1;
        if (shiftStart < size) {
            System.arraycopy(array, shiftStart, array, index, size - shiftStart);
        }
        size--;
    }

    public void removeFirst() {
        removeAt(0);
    }

    public void removeLast() {
        removeAt(size);
    }

    @Override
    public void swap(E element1, E element2) {

    }

    @Override
    public void swap(int position1, int position2) {

    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    public int indexOf(E element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void clear() {

    }

    @Override
    public E[] toArray() {
        return Arrays.copyOfRange(array, 0, size);
    }

    @Override
    public E[] toArray(int fromIndex) {
        return Arrays.copyOfRange(array, fromIndex,size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return array[index++];
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1 || isEmpty()) {
                builder.append(array[i]).append("]");
                break;
            }
            builder.append(array[i]).append(", ");
        }
        return builder.toString();
    }

    @Override
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            final E tmp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = tmp;
        }
    }


}
