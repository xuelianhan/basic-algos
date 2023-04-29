package org.ict.algorithm.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *  java org/ict/algorithm/util/FixedCapacityStack 5
 *  to be or not to - be - - that - - - is
 *  to be not that or be 
 *  Left on stack:is to 
 *  use CTRL-D to end input
 *
 * @see http://algs4.cs.princeton.edu/13stacks/FixedCapacityStack.java.html
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {
  
  private Item[] a;

  private int N;

  /**
   * @see http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
   */
  public FixedCapacityStack(int capacity) {
      this.a =  (Item[])new Object[capacity];
      this.N = 0;
  }

  public boolean isEmpty() {
      return N == 0;
  }

  public void push(Item item) {
      a[N++] = item;
  }

  public Item pop() {
      return a[--N];
  }

  public Item peek() {
      return a[N-1];
  }

  public Iterator<Item> iterator() {
      return new ReverseArrayIterator(); 
  }


  public class  ReverseArrayIterator implements Iterator<Item> {
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
      int max = Integer.parseInt(args[0]);
      FixedCapacityStack<String> stack = new FixedCapacityStack<String>(max);
      while (!StdIn.isEmpty()) {
          String item = StdIn.readString();
          if (!"-".equals(item)) {
              stack.push(item);
          } else if (stack.isEmpty()) {
              StdOut.println("Bad Input!");
          } else {
              StdOut.print(stack.pop() + " ");
          }
      }
      StdOut.println();
      StdOut.print("Left on stack:");
      for (String item : stack) {
        StdOut.print(item + " ");
      }
      StdOut.println();
  }

}
