package com.andgcv.practice.data_structures;

public class BinaryTree {
    Node root;

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public boolean find(int value) {
        return findRecursive(root, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    // HELPER METHODS

    private Node addRecursive(Node current, int value) {
        // Arrived at desired spot, create new Node
        if (current == null) return new Node(value);

        // Value < current node, traverse to left branch
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        // Value > current node, traverse to left branch
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    private boolean findRecursive(Node current, int value) {
        // Didn't find the given value
        if (current == null) return false;

        // Value < current node, traverse to left branch
        if (value < current.value) {
            return findRecursive(current.left, value);
        // Value > current node, traverse to left branch
        } else if (value > current.value) {
            return findRecursive(current.right, value);
        }

        // Found given value
        return true;
    }

    private Node deleteRecursive(Node current, int value) {
        // Didn't find the given value
        if (current == null) return null;

        if (current.value == value) {
            // Current node is a leaf
            if (current.left == null && current.right == null) return null;
            // Current node has left child
            if (current.right == null) return current.left;
            // Current node has right child
            if (current.left == null) return current.right;
            // Current node has both children
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = deleteRecursive(current.right, value);
        }

        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public static void main(String[] args) {
        int values = 20;
        BinaryTree bt = new BinaryTree();
        bt.add(10);

        for (int i = 0; i < values; i++) {
            bt.add(i);
        }

        System.out.println(bt.find(5));
        bt.delete(5);
        System.out.println(bt.find(5));
    }
}