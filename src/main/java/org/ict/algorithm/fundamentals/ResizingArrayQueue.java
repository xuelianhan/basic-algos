package org.ict.algorithm.fundamentals;

import java.util.NoSuchElementException;
import java.util.Iterator;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 * [hanxuelian@ict java]$ java org/ict/algorithm/util/ResizingArrayQueue
 * to be or not to - be - - that - - - is
 * press enter
 *
 * to be or not to be 
 * press CTRL-D
 *
 * (2 left in queue)
 *
 * @see http://algs4.cs.princeton.edu/13stacks/ResizingArrayQueue.java.html
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] a;

    private int N;
  
    private int first;

    private int last;

    public ResizingArrayQueue() {
        a = (Item[]) new Object[2];
        N = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int capacity) {
        if (capacity < N) {
            throw new IllegalArgumentException("capacity:" + capacity + " must be greater than N:" + N);
        }
        Item[] temp = (Item[])new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[(i + first) % a.length];
        }
        a = temp;
        first = 0;
        last = N;
    }

    public void enqueue(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[last++] = item;
        if (last == a.length) {
          last = 0;
        }
        N++;
    }


    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = a[first];
        a[first] = null;
        N--;
        first++;
        if (first == a.length) {
            first = 0;
        }
        if (N > 0 && N == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return a[first]; 
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {
            return i <= N;      
        }

        public Item next() {
          Item item = a[(i + first) % a.length];
          i++;
          return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                q.enqueue(s);
            } else if (!q.isEmpty()) {
                String item = q.dequeue();
                StdOut.print(item + " ");
            }
        }
        StdOut.println("\n(" + q.size() + " left in queue)");
    }
}
