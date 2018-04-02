package org.ict.algorithm.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * $ javac org/ict/algorithm/fundamentals/Queue.java 
 * $ java org/ict/algorithm/fundamentals/Queue < ../resources/tobe.txt 
 * to be or not to be (2 left in queue)
 *
 *
 * @see http://algs4.cs.princeton.edu/13stacks/Queue.java.html
 */
public class Queue<Item> implements Iterable<Item> {
    
    private Node<Item> first;

    private Node<Item> last;
   
    private int N;

    private static class Node<Item> {

        private Item item;

        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        N = 0;
    }

    public boolean  isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow.");
        }
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
          last = null;
        }
        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item + " ");
        }
        return sb.toString();
    }

    public Iterator<Item> iterator() {
        // must pass first node to iterator
        return new ListIterator<Item>(first);
    }
   
    private class ListIterator<Item> implements Iterator<Item> {
        
          private Node<Item> current;

          public ListIterator(Node<Item> first) {
              current = first;
          }

          /**
           * Pay attention to implementation of this method
           * which is easily to make mistakes here.
           */
          public boolean hasNext() {
              //Notice here! 
              //First, don't write != as ==
              //Secondly, check current node is first or not before using current.next() != null
              return current != null;
          }

          public Item next() {
              if (!hasNext()) {
                  throw new NoSuchElementException();
              }
              Item item = current.item;
              current = current.next;
              return item; 
          }

          public void remove() {
              throw new UnsupportedOperationException();
          }
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!"-".equals(s)) {
                q.enqueue(s);
            } else if (!q.isEmpty()) {
                String item = q.dequeue();
                StdOut.print(item + " ");
            } else {
                StdOut.println("Nothing to do.");
            }
        }
        StdOut.println("(" + q.size() + " left in queue)");
    }

}
