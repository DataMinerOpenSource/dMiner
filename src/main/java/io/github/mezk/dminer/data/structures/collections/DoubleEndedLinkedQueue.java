package io.github.mezk.dminer.data.structures.collections;

import io.github.mezk.dminer.data.structures.collections.lists.DoublyLinkedList;

public class DoubleEndedLinkedQueue<E> {

    private DoublyLinkedList<E> list = new DoublyLinkedList<E>();

    private void enqueueFirst(E element) {
        list.addFirst(element);
    }

    public void enqueueLast(E element) {
        list.addLast(element);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public E dequeueFirst() {
        E element = peakFirst();
        list.removeFirst();
        return element;
    }

    public E dequeueLast() {
        E element = peakLast();
        list.removeLast();
        return element;
    }

    private E peakLast() {
        if (list.size() == 0) {
            throw new RuntimeException("Queue is empty.");
        }
        return list.getLast();
    }

    private E peakFirst() {
        if (list.size() == 0) {
            throw new RuntimeException("Queue is empty.");
        }
        return list.getFirst();
    }

}
