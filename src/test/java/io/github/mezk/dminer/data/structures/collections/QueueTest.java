package io.github.mezk.dminer.data.structures.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueueTest {

    @Test
    public void testEnqueue() {
        final Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        final String expected = "[3, 2, 1]";
        assertEquals(expected, queue.toString());
    }

    @Test
    public void testDequeue() {
        final Queue<String> queue = new Queue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        final String expected = "[3, 2, 1]";
        assertEquals(expected, queue.toString());
        assertEquals("1", queue.dequeueu());
        assertEquals("2", queue.dequeueu());
        assertEquals("3", queue.dequeueu());
    }


    @Test
    public void testSize() {
        final Queue<String> queue = new Queue<>();
        assertTrue(queue.size() == 0);
    }

    @Test
    public void testIsEmpty() {
        final Queue<String> queue = new Queue<>();
        assertTrue(queue.isEmpty());
    }
}
