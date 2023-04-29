package org.ict.algorithm.util;


import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @see http://algs4.cs.princeton.edu/13stacks/ResizingArrayBag.java.html
 */
public class ResizingArrayBag<Item> implements Iterable<Item> {
    private Item[] a;
    private int n; 

    public ResizingArrayBag() {
        a = (Item[])new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        if (capacity < n) {
          throw new IllegalArgumentException("capacity have to be more than current size " + n);
        }
        Item[] temp = (Item[])new Object[capacity];
        for (int i = 0; i < n; i++) {
          temp[i] = a[i];
        }
        a = temp;
    }

    public void add(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[i++];
        }
    }

    public static void main(String[] args) {
        ResizingArrayBag<String> bag = new ResizingArrayBag<String>();
        bag.add("hello");
        bag.add("world");
        bag.add("how");
        bag.add("are");
        bag.add("you");
        bag.add("!");
        for (String s : bag) {
            StdOut.println(s);
        }
    }

}
