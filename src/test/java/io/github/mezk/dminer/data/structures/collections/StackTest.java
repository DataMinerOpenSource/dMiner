package io.github.mezk.dminer.data.structures.collections;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackTest {

    @Test
    public void testPush() {
        final Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(2);
        stack.push(5);
        stack.push(1);
        final String expected = "[4, 2, 5, 1]";
        assertEquals(expected, stack.toString());
    }

    @Test
    public void testPop() {
        final Stack<String> stack = new Stack<>();
        stack.push("4");
        stack.push("2");
        stack.push("5");
        stack.push("1");
        assertEquals("1", stack.pop());
        String expected = "[4, 2, 5]";
        assertEquals(expected, stack.toString());
        assertEquals("5", stack.pop());
        expected = "[4, 2]";
        assertEquals(expected, stack.toString());
        assertEquals("2", stack.pop());
        expected = "[4]";
        assertEquals(expected, stack.toString());
        assertEquals("4", stack.pop());
        expected = "[]";
        assertEquals(expected, stack.toString());
    }

    @Test
    public void testPeek() {
        final Stack<String> stack = new Stack<>();
        stack.push("4");
        stack.push("2");
        stack.push("5");
        stack.push("1");
        assertEquals("1", stack.peek());
        String expected = "[4, 2, 5, 1]";
        assertEquals(expected, stack.toString());
    }
}
