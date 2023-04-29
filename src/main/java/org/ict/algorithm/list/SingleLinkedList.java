package org.ict.algorithm.list;

import java.util.Iterator;
import org.ict.algorithm.util.StdOut;
/**
 *
 *
 * Tue Aug  2 10:19:08 CST 2016
 */
public class SingleLinkedList<Item> implements Iterable<Item> {

    private transient Node first;

    private transient Node last;

    private transient Node lastPrior;

    private transient int N;

    public SingleLinkedList() {
        this.first = null;
        this.last = null;
        this.lastPrior = null;
        N = 0;
    }

    private class Node {
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
        Node oldlast = last;
        last = new Node();
        last.item = data;
        if (oldlast != null) {
            oldlast.next = last;
        }
        lastPrior = oldlast;
        N++;
        if (N == 1) {
            first = last;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(" " + item);
        }
        return sb.toString();
    }

    public void remove(Item data) {
        Node prior = null;
        for (Node n = first; n != null;) {
            Node next = n.next;
            if (n.item == data) {
                if (prior == null) {
                    prior = next;
                    first = prior;
                } else {
                    prior.next = next;
                    prior = n;
                }
                N--;
            }
            prior = n;
            n = next;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node cur = first; 
        
        private Node lastReturned = first;
        
        public boolean hasNext() {
            return cur != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new IllegalArgumentException();
            }
            Item item = cur.item;
            lastReturned = cur;
            cur = cur.next;
            return item; 
        }

        public void remove() {
           throw new UnsupportedOperationException(); 
        }

    }

    public static void main(String[] args) {
        SingleLinkedList<String> sl = new SingleLinkedList<String>();
        sl.add("A");
        sl.add("B");
        sl.add("C");
        sl.add("D");
        
        StdOut.println("Input:" + sl + "\nOutput:");
        Iterator<String> iter = sl.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            StdOut.print(s + ",");
        }
        String del = "D";
        sl.remove(del);
        StdOut.println("\nAfter " + del + " has been removed, Output:" + sl + "\n");
    }

}
