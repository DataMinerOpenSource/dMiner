package io.github.mezk.dminer.data.structures.trees;

import java.util.Iterator;

public class BinaryTree <T extends Comparable> implements Iterable<T>{

    private BinaryTreeNode<T> root;
    private int size;

    public void add(T value) {
        if (root == null) {
            root = new BinaryTreeNode<>(value);
        }
        else {
            addTo(root, value);
        }
        size++;
    }

    private void addTo(BinaryTreeNode<T> node, T value) {
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new BinaryTreeNode<>(value);
            }
            else {
                addTo(node.left, value);
            }
        }
        else {
            if (node.right == null) {
                node.right = new BinaryTreeNode<>(value);
            }
            else {
                addTo(node.right, value);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {
        return find(value) != null;
    }

    private BinaryTreeNode<T> find(T value) {
        BinaryTreeNode<T> current = root;
        while (current != null) {
            int result = current.compareTo(value);
            if (result > 0) {
                current = current.left;
            }
            else if (result < 0) {
                current = current.right;
            }
            else {
                break;
            }
        }
        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class BinaryTreeNode<T extends Comparable> implements Comparable<T> {
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;
        private T value;

        public BinaryTreeNode(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(T other) {
            return value.compareTo(other);
        }
    }


}
