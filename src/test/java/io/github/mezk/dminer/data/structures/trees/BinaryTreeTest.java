package io.github.mezk.dminer.data.structures.trees;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinaryTreeTest {

    @Test
    public void testAdd() {
        final BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(6);
        tree.add(4);
    }

    @Test
    public void testContains() {
        final BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(3);
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(5));
    }

}
