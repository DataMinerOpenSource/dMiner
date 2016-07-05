package io.github.mezk.dminer.data.structures.collections.lists;

import java.util.*;

public abstract class AbstractList<E> implements List<E> {

    protected AbstractList() { }

    @Override
    public abstract int size();

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(E element, int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void swap(E element1, E element2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void swap(int position1, int position2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E[] toArray(int fromIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reverse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
}
