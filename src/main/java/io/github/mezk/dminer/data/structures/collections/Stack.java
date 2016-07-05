package io.github.mezk.dminer.data.structures.collections;

import java.awt.dnd.InvalidDnDOperationException;

import io.github.mezk.dminer.data.structures.collections.lists.DoublyLinkedList;

public class Stack<E> extends DoublyLinkedList<E> {

    @Override
    public int size() {
        return super.size();
    }

    public void push(E element) {
        addLast(element);
    }

    public E pop() {
        final E element = peek();
        removeLast();
        return element;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        E element = getLast();
        return element;
    }
}
