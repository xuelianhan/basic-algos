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

    private transient int N;

    public SingleLinkedList() {
        this.first = null;
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
        Node oldfirst = first;
        first = new Node();
        first.item = data;
        first.next = oldfirst;
        N++;
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
        if (data == null) {
           for (Node n = first; n != null;) {
               if (n.item == null) {
                   prior.next = n.next; 
                   N--;
               }
               prior = n;
               n = n.next;
           }
        } else {
            for (Node n = first; n != null;) {
                if (n.item == data) {
                    prior.next = n.next;
                    N--;
                }
                prior = n;
                n = n.next;
            }
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node cur = first; 
        
        public boolean hasNext() {
            return cur != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new IllegalArgumentException();
            }
            Item item = cur.item;
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
        sl.remove("A");
        StdOut.println("\nAfter A has been removed, Output:" + sl + "\n");
    }

}
