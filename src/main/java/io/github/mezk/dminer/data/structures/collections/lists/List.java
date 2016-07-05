package io.github.mezk.dminer.data.structures.collections.lists;

public interface List<E> extends Iterable<E> {

    void add(E element);

    void add(E element, int position);

    E get(int index);

    boolean remove(E element);

    void swap(E element1, E element2);

    void swap(int position1, int position2);

    boolean contains(E element);

    int size();

    void clear();

    boolean isEmpty();

    E[] toArray();

    E[] toArray(int fromIndex);

    void reverse();
}
