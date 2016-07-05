package io.github.mezk.dminer.data.structures.collections.lists;

import java.util.Iterator;

public class SinglyLinkedList<E> extends AbstractList<E> implements List<E> {

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() { }

    public SinglyLinkedList(E[] array) {
        for (E element : array) {
            addLast(element);
        }
    }

    @Override
    public void add(E element) {
        final Node newNode = new Node(element);
        newNode.value = element;
        if (head == null) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public boolean remove(E element) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            if (current.value.equals(element)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null) {
                        tail = previous;
                    }
                }
                else {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray(int fromIndex) {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[size];
        Node current = head;
        while (current != null) {
            array[fromIndex++] = current.value;
            current = current.next;
        }
        return array;
    }

    @Override
    public E[] toArray() {
        return toArray(0);
    }

    @Override
    public void clear() {
        size  = 0;
        tail = null;
        head = null;
    }

    public void removeFirst() {

    }

    public void removeLast() {

    }

    @Override
    public String toString() {
        String list = "[";
        Node current = head;
        for (int i = 0; i < size - 1; i++) {
            list += current.value + ", ";
            current = current.next;
        }
        list += current.value;
        list += "]";
        return list;
    }

    @Override
    public void add(E element, int position) {

    }

    public void addFirst(E element) {

    }

    public void addLast(E element) {
        add(element);
    }

    @Override
    public void swap(E element1, E element2) {

    }

    @Override
    public void swap(int position1, int position2) {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E getFirst() {
        return null;
    }

    public E getLast() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
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
                Node next = nextNode;
                nextNode = nextNode.next;
                return next.value;
            }
        };
    }

    @Override
    public void reverse() {

    }

    private class Node {
        private E value;
        private Node next;

        Node(E value) {
            this.value = value;
        }
    }
}
