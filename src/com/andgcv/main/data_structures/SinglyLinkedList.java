package com.andgcv.main.data_structures;

public class SinglyLinkedList {
    private SLLNode head;

    public SinglyLinkedList(int headData) {
        this.head = new SLLNode(headData);
    }

    public SinglyLinkedList() {
        this.head = new SLLNode();
    }

    public int addFirst(int data) {
        SLLNode node = new SLLNode(data);
        SLLNode temp = this.head;
        this.head = node;
        this.head.next = temp;

        return this.head.data;
    }
}
