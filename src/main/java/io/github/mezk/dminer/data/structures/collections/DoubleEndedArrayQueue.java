package io.github.mezk.dminer.data.structures.collections;

public class DoubleEndedArrayQueue<E> {

    private E[] items = (E[]) new Object[0];
    private int size = 0;
    private int head = 0;
    private int tail = -1;

    public void enqueueFirst(E item) {
        if (items.length == size) {
            allocateNewArray(1);
        }
        if (head > 0) {
            head--;
        }
        else {
            head = items.length - 1;
        }
        items[head] = item;
        size++;
    }

    public void enqueueLast(E element) {
        if (items.length == size) {
            allocateNewArray(0);
        }
        if (tail == items.length - 1) {
            tail = 0;
        }
        else
        {
            tail++;
        }
        items[tail] = element;
        size++;
    }

    public E peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        return items[head];
    }

    public E peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        return items[tail];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void dequeueFirst() {

    }

    public void dequeueLast() {

    }

    private void allocateNewArray(int startingIndex) {
        final int newLength = (size == 0) ? 4 : size * 2;
        @SuppressWarnings("unchecked")
        final E[] newArray = (E[]) new Object[newLength];
        if (size > 0)
        {
            int targetIndex = startingIndex;
            if (tail < head)
            {
                for (int index = head; index < items.length; index++)
                {
                    newArray[targetIndex] = items[index];
                    targetIndex++;
                }
                for (int index = 0; index <= tail; index++)
                {
                    newArray[targetIndex] = items[index];
                    targetIndex++;
                }
            }
            else
            {
                for (int index = head; index <= tail; index++)
                {
                    newArray[targetIndex] = items[index];
                    targetIndex++;
                }
            }
            head = startingIndex;
            tail = targetIndex - 1;
        }
        else
        {
            head = 0;
            tail = -1;
        }
        items = newArray;
    }
}
