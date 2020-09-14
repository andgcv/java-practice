package com.andgcv.tests.data_structures;

import com.andgcv.main.data_structures.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SinglyLinkedListTest {
    SinglyLinkedList myInstList = new SinglyLinkedList(3);
    SinglyLinkedList myDefList = new SinglyLinkedList();

    @Test
    public void addNewHead() {
        int data = 10;
        Assertions.assertEquals(10, myInstList.addFirst(data));
        Assertions.assertEquals(10, myDefList.addFirst(data));
    }


}
