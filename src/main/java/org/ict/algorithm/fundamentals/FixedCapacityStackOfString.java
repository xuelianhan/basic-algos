package org.ict.algorithm.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


public class FixedCapacityStackOfString implements Iterable<String> {
	
    private String[] a;

    private int N;

    /**
     * if you write as: FixedCapacityStackOfString<String>,then the compiler would send error tips:
     * Cannot create a generic Array of String.
     * To solve this problem, simply remove the <String> after FixedCapacityStackOfString
     * @param capacity
     */
    public FixedCapacityStackOfString(int capacity) {
        this.a = new String[capacity];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String s) {
        //a[N] = s;
        //N++;
        a[N++] = s;
    }

    public String pop() {
        //String s = a[N-1];
        //N--;
        //return s;
        return a[--N];
    }

    public String peek() {
        return a[N-1];
    }

    public Iterator<String> iterator() {
         return new ReverseArrayIterator();
    }


    public class ReverseArrayIterator implements Iterator<String> {

        private int i = N - 1;

		public boolean hasNext() {
			return i >= 0;
		}

		public String next() {
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
        FixedCapacityStackOfString stack = new FixedCapacityStackOfString(max);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                 stack.push(s);
            } else if (stack.isEmpty()) {
                  StdOut.println("Bad input!"); 
            } else {
                StdOut.print(stack.pop() + " ");                
            }
        }

        StdOut.println();

        StdOut.print("Left on Stack:");
        for (String s : stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }

}
