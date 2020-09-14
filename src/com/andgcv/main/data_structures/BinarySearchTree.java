package com.andgcv.main.data_structures;

public class BinarySearchTree {
    BSTNode root;

    // Add a Node with the given value to the BST
    public void add(int value) {
        root = addRecursive(root, value);
    }

    // Return whether or not a Node with the given value can be found in the BST
    public boolean find(int value) {
        return findRecursive(root, value);
    }

    // Delete a Node with the given value from the BST, if it can be found
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    // HELPER METHODS
    // Helper method - Traverses the tree until it finds the right spot and creates a new Node with the given value
    private BSTNode addRecursive(BSTNode current, int value) {
        if (current == null) return new BSTNode(value);        // Arrived at desired spot, create new Node

        if (value < current.value) {                        // Value < current node, traverse to left branch
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {                 // Value > current node, traverse to right branch
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    // Helper method - Tries to find the Node with the given value, returns whether it was found or not
    private boolean findRecursive(BSTNode current, int value) {
        if (current == null) return false;                  // Didn't find the given value

        if (value < current.value) {                        // Value < current node, traverse to left branch
            return findRecursive(current.left, value);
        } else if (value > current.value) {                 // Value > current node, traverse to right branch
            return findRecursive(current.right, value);
        }

        return true;                                        // Found given value
    }

    // Helper method - Tries to find the Node with the given value, deletes it, and reorganizes the tree
    private BSTNode deleteRecursive(BSTNode current, int value) {
        if (current == null) return null;                   // Didn't find the given value

        if (current.value == value) {
            if (current.left == null && current.right == null) return null; // Current node is a leaf, delete it
            if (current.right == null) return current.left; // Assign left child to the parent Node
            if (current.left == null) return current.right; // Assign right child to the parent Node
            // Current node has both children
            int smallestValue = findSmallestValue(current.right); // Find the smallest value in the right branch
            current.value = smallestValue; // Assign the smallest value in the right branch to the Node we're deleting
            current.right = deleteRecursive(current.right, smallestValue); // Delete the Node from the right branch
        }

        if (value < current.value) {                        // Value < current node, traverse to left branch
            current.left = deleteRecursive(current.left, value);
        } else if (value > current.value) {                 // Value > current node, traverse to right branch
            current.right = deleteRecursive(current.right, value);
        }

        return current;
    }

    // Helper method - Traverses left branch until it finds the smallest value, then returns it
    private int findSmallestValue(BSTNode root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }
}