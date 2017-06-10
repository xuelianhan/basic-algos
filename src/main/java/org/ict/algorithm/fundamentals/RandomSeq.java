package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


/**
 * the {@code RandomSeq} class is a client that prints out a psedorandom
 * sequence of real numbers in a given range.
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/11model/">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 *
 */
public class RandomSeq {
    //this class should not be instantiated
    private RandomSeq() {}

    /**
     * Reads in two command-line arguments lo and hi and prints n uniformly
     * random real numbers in [lo, hi] to standard output.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //command-line arguments
        int n = Integer.parseInt(args[0]);
        
        if (args.length == 1) {
            //generate and print n numbers between 0.0 and 1.0
            for (int i = 0; i < n; i++) {
               double x = StdRandom.uniform();
               StdOut.println(x);
            }
        } else if (args.length == 3) {
            double lo = Double.parseDouble(args[1]);
            double hi = Double.parseDouble(args[2]);
            //generate and print n numbers between lo and hi 
            for (int i = 0; i < n; i++) {
               double x = StdRandom.uniform(lo, hi);
               StdOut.printf("%.2f\n", x);
            }
        } else {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

}
