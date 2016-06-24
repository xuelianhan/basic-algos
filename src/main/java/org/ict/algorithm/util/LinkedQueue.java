package org.ict.algorithm.util;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item> {
    
    private Node first;

    private Node last;

    private int N;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        N = 0;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node<Item> {
        private Item item;

        private Node next;

    }
    
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
          first = last;
        } else {
          oldlast.next = last;
        }
        N++;
    }

    public Item peek() {
        return null;
    }

    public Item dequeue() {

    }

    public String toString() {
        return null;
    }

    public Iterator<Item> iterator(){
        return null;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        boolean hasNext() {

        }

        Item next() {

        }

        void remove() {

        }

    }

    private boolean check() {
        return true;
    }

    public static void main(String[] args) {

    }
}
