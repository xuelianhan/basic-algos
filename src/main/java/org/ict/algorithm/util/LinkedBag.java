package org.ict.algorithm.util;



public class LinkedBag<Item> implements Iterable<Item> {
    private Node first;

    private int n;

    private class Node {
        private Item item;
        private Node next;
    }


}
