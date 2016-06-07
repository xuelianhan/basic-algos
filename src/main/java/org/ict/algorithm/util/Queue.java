package org.ict.algorithm.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
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
        return new ListIterator<Item>();
    }
   
    private class ListIterator implements Iterator<Item> {
        
          private Node<Item> current;

          public ListIterator() {
              this.current = first;
          }

          public boolean hasNext() {
              return current.next == null;
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
                StdOut.println("Nothing to do.")
            }
        }
        StdOut.println("(" + q.size() + " left in queue)");
    }

}
