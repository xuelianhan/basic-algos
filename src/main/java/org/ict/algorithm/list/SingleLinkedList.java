package org.ict.algorithm.list;

import java.util.Iterator;

/**
 *
 *
 *
 */
public class SingleLinkedList<Item> implements Iterable<Item> {

    private transient Node first;

    private transient int N;

    public SingleLinkedList() {
        this.first = null;
        N = 0;
    }

    private class Node<Item> {
        private Item item; 
        private Node next;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void add(Item data) {
        Node oldfirst = first;
        first = new Node();
        first.item = data;
        first.next = oldfirst;
        N++;
    }

    public void remove(Item data) {
        if (data == null) {
            
        } else {

        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

    }

    public static void main(String[] args) {


    }

}
