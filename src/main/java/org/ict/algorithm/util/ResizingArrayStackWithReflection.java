package org.ict.algorithm.util;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A LIFO stack using a resizeable array. 
 * % more tobe.txt 
 *   to be or not to - be - - that - - - is
 *  
 *   % java ResizingArrayStackWithReflection < tobe.txt
 *   to be not that or be (2 left on stack)
 *   $ javac org/ict/algorithm/util/ResizingArrayStackWithReflection.java 
 *   $ java org/ict/algorithm/util/ResizingArrayStackWithReflection < /home/hanxuelian/Public/basic-algos/src/main/resources/tobe.txt
 *   to be not that or be (2left in stack)
 *   $
 */
public class ResizingArrayStackWithReflection<Item> implements Iterable<Item> {
    private Class<Item[]> itemArrayClass;

    private Item[] array;

    private int N = 0;

    public ResizingArrayStackWithReflection(Class<Item[]> itemArrayClass) {
        this.itemArrayClass = itemArrayClass;
        array = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), 1));
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item.");
        }
        if (N == array.length) {
            resize(array.length * 2);
        }
        array[N++] = item;
    }

    private void resize(int size) {
        Item[] newarray = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), size));
        for (int i = 0; i < N; i++) {
            newarray[i] = array[i];
        }
        array = newarray;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = array[--N];
        array[N] = null;
        if ((N > 0) && (N <= array.length / 4)) {
            resize(array.length / 2); 
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[N - 1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    public static void main(String[] args) {
       ResizingArrayStackWithReflection<String> s = new ResizingArrayStackWithReflection<String>(String[].class);         
       while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
       }
       StdOut.println("(" + s.size() + "left in stack)");
    }
}



