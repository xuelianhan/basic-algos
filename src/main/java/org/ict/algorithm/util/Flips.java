package org.ict.algorithm.util;


/**
 * http://algs4.cs.princeton.edu/12oop/Flips.java.html
 * java org/ict/algorithm/util/Flips  10000
 * 4953
 * 5047
 * delta:94
 *
 * java org/ict/algorithm/util/Flips  100
 * 49
 * 51
 * delta:2
 *
 * java org/ict/algorithm/util/Flips  1000
 * 486
 * 514
 * delta:28
 *
 *
 */
public class Flips {

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Counter head = new Counter("head");
        Counter tail = new Counter("tail");

        for (int t = 0; t < T; t++) {
          if (StdRandom.bernoulli(0.5)) {
            head.increment();
          } else {
            tail.increment();
          }
        }

        StdOut.println(head.tally());
        StdOut.println(tail.tally());

        int delta = head.tally() - tail.tally();
        StdOut.println("delta:" + Math.abs(delta)); 
    }

}
