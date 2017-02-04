package org.ict.algorithm.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.ict.algorithm.util.StdOut;

public class LinkedQueue<Item> implements Iterable<Item> {
    
    private Node<Item> first;

    private Node<Item> last;

    private int N;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        N = 0;
        check();
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node<Item> {
        private Item item;

        private Node<Item> next;

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
        check();
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
            last = null;//to avoid loitering
        }
        check();
        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item + " ");
        }
        return sb.toString();
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
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

    private void check() {
        if (N < 0) {
            throw new IllegalStateException("N cannot be negative number! ");
        } else if (N == 0) {
            if (first != null || last != null) {
                throw new IllegalStateException("Head Tail pointer are not equal to null when N is zero");
            }
        } else if (N == 1) {
            if (first == null || last == null || first != last || first.next != null) {
                throw new IllegalStateException("Head Tail pointer are not illegal when N is one");
            }
        } else {
            if (first == null || last == null || first == last || first.next == null || last.next != null) {
                throw new IllegalStateException("Head Tail pointer are not illegal when N is more than one");
            }
            //check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = first; (x != null) && (numberOfNodes <= N); x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != N) {
                throw new IllegalStateException("Number of Nodes is not equals to N");
            }
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (lastNode != last) {
                throw new IllegalStateException("Last Node is not equals to last pointer");
            }
        }
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
        
    }
}
