package org.ict.algorithm.search;


import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

public class LookupIndex {

    // Do not instantiate
    private LookupIndex() {}

    public static void main(String[] args) {
        String filename  = args[0];
        String separator = args[1];
        In in = new In(filename);
        
        ST<String, Queue<String>> st = new ST<String, Queue<String>>();
        ST<String, Queue<String>> ts = new ST<String, Queue<String>>();
    }
}
