package io.github.mezk.dminer.data.structures.collections;

import io.github.mezk.dminer.data.structures.collections.lists.DoublyLinkedList;

public class Queue<E> {

    private final DoublyLinkedList<E> list = new DoublyLinkedList<E>();

    public void enqueue(E element) {
        list.addFirst(element);
    }

    public E dequeueu() {
        E element =  peek();
        list.removeLast();
        return element;
    }

    public E peek() {
        if (list.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list.getLast();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
