package org.ict.algorithm.util;

import java.util.Iterator;

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
		
	}
	
	public Item pop() {
		
		return null;
	}
	
	public Item peek() {
		
		return null;
	}
	
	public String toString() {
		
		return null;
	}
	
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class ListIterator<Item> implements Iterator<Item> {

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public Item next() {
			// TODO Auto-generated method stub
			return null;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
