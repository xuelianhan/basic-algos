package org.ict.algorithm.search;

public class SequentialSearchST<Key, Value> {
    // number of key-value pairs
    private int n;
    // the linked list of key-value pairs
    private Node first;

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

    }
}
