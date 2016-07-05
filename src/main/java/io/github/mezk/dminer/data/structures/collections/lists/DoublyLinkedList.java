package io.github.mezk.dminer.data.structures.collections.lists;

import java.util.Iterator;

public class DoublyLinkedList<E> extends AbstractList<E> implements List<E> {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() { }

    public DoublyLinkedList(E[] array) {
        for (E element : array) {
            addLast(element);
        }
    }

    @Override
    public void add(E element) {

    }

    @Override
    public void add(E element, int position) {

    }

    public E getFirst() {
        return null;
    }

    public E getLast() {
        return tail.element;
    }

    @Override
    public E get(int index) {
        return null;
    }

    public void addFirst(E element) {
        Node newNode = new Node(element);
        Node temp = head;
        head = newNode;
        head.next = temp;
        if (size == 0) {
            tail = head;
        }
        else {
            temp.previous = head;
        }
        size++;
    }

    public void addLast(E element) {
        Node newNode = new Node(element);
        if (size == 0) {
            head = newNode;
        }
        else {
            tail.next = newNode;
            newNode.previous = tail;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void swap(E element1, E element2) {

    }

    @Override
    public void swap(int position1, int position2) {
    }

    @Override
    public boolean remove(E element) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null) {
                        tail = previous;
                    }
                    else {
                        previous.next.previous = previous;
                    }
                    size--;
                }
                else {
                    removeFirst();
                }
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    public void removeFirst() {
        if (size != 0) {
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            else {
                head.previous = null;
            }
        }
    }

    public void removeLast() {
        if (size != 0) {
            if (size == 1) {
                head = null;
                tail = null;
            }
            else {
                tail.previous.next = null;
                tail = tail.previous;
            }
            size--;
        }
    }

    @Override
    public boolean contains(E element) {
        Node current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        return toArray(0);
    }

    @Override
    public E[] toArray(int fromIndex) {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[size];
        Node current = head;
        while (current != null) {
            array[fromIndex++] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                builder.append(current.element).append("]");
                break;
            }
            builder.append(current.element).append(", ");
            current = current.next;
        }
        return builder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node nextNode = head;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                final Node next = nextNode;
                nextNode = nextNode.next;
                return next.element;
            }
        };
    }

    @Override
    public void reverse() {

    }

    private class Node {
        private E element;
        private Node next;
        private Node previous;

        Node(E element) {
            this.element = element;
        }
    }
}
