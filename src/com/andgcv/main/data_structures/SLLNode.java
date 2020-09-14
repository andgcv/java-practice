package com.andgcv.main.data_structures;

public class SLLNode {
    SLLNode next = null;
    int data;

    public SLLNode(int data) {
        this.data = data;
    }

    // For simplicity, instantiates data with 0 on a default constructor
    public SLLNode() {
        this.data = 0;
    }
}
