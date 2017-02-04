package org.ict.algorithm.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 * Compilation: javac org/ict/algorithm/util/LinkedBag.java 
 * Execution:   java org/ict/algorithm/util/LinkedBag < ../resources/tobe.txt 
 *
 * The {@code LinkedBag} class represents a bag (or multiset) of 
 * generic items. it supports insertion and iterating over the
 * items in arbitrary order.
 * <p>
 * This implementation uses a singly-linked list with a non-static nested class Node.
 * See {@link Bag} for a version that uses a static nested class.
 * the <em>add</em>, <em>isEmpty</em>, and <em>size</em> operations
 * take constant time. Iteration takes time proportional to the number of items.
 * <p>
 * @author RobertSedgewick
 * @author Kevin Wayne
 *
 */
public class LinkedBag<Item> implements Iterable<Item> {
    private Node first;

    private int n;

    private class Node {
        private Item item;
        private Node next;
    }

    public LinkedBag() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        StdOut.println("LinkedBag test start.");
        LinkedBag<String> bag = new LinkedBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
            StdOut.println("LinkedBag test add: " + item);
        }
        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
