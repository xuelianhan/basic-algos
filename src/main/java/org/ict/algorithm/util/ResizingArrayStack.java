package org.ict.algorithm.util;


import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 *
 * @see http://algs4.cs.princeton.edu/13stacks/ResizingArrayStack.java.html
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a;

    private int N;

    public ResizingArrayStack() {
        this.a = (Item[])new Object[2];
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int capacity) {
        if (capacity < N) {
            throw new IllegalArgumentException("capacity: " + capacity + " must be greater than N:" + N);
        }
        Item[] temp = (Item[])new Object(capacity);
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (a.length == N) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw NoSuchElementException("Stack underflow!");
        }
        Item item = a[N-1];
        a[N-1] = null;
        N--;
        if (N > 0 && N == a.length / 4) {
            resize(a.length/2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw NoSuchElementException("Stack underflow!");
        }
        return a[N-1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }


    public class ReverseArrayIterator implements Iterator<Item> {
        
        private int i = N - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stack.push(item);
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            } else {
                
            }
        }
    }
}
