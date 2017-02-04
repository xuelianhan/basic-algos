package org.ict.algorithm.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *
 * @see http://algs4.cs.princeton.edu/13stacks/Stack.java.html
 */
public class Stack<Item> implements Iterable<Item> {
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	
	private Node<Item> first;
	
	private int N;
	
	public Stack() {
		first = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}

	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("stack overflow!");
		}
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	public Item peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("stack overflow!");
		}
		return first.item;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Item item : this) {
			sb.append(item + " ");
		}
		return sb.toString();
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item> {
		
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException("no more element!");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * [hanxuelian@ict java]$ cat /home/hanxuelian/Desktop/test.log
	 *	to be or not      to - be - - that - - - 
	 * 
	 * [hanxuelian@ict java]$ java org/ict/algorithm/util/Stack < /home/hanxuelian/Desktop/test.log
     * to be not that or be (2 left on stack!)
     * 
     * to  <- 1
     * be  <- 2     13 be   ->
     * or  <- 3     12 or   ->
     * not <- 4     9  not  ->
     * to  <- 5     6  to   ->
     * be  <- 7     8  be   ->
     * that<- 10    11 that ->
	 */
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				stack.push(item);
			} else if (!stack.isEmpty()) {
				StdOut.print(stack.pop() + " ");
			}
		}
		StdOut.println("(" + stack.size() + " left on stack!)");
	}

}
