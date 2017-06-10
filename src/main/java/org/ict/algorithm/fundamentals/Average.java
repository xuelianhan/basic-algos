package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *
 * Reads in a sequence of real numbers, and computes their average.
 * 
 * $ javac org/ict/algorithm/fundamentals/Average.java 
 * $ java org/ict/algorithm/fundamentals/Average  1 2 3 4 5 6 7 8 9 10
 *
 * $ java org/ict/algorithm/fundamentals/Average
 * 1.0 2.0 3.0 4.0 5.0 6.0
 * 7.0 8.0 9.0 10.0
 * Average is 5.5
 *
 *
 *
 * Note [Ctrl-d] signifies the end of file on Unix.
 * On Windows use [Ctrl-z].
 *
 * The {@code Average} class provides a client for reading in a sequence
 * of real numbers and printing out their average.
 * For additional documentation, see <a href="">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i>by Robert Sedgewick and Kevin Wayne.
 */
public class Average {
    //this class should not be instantiated
    private Average() {}

    /**
     * Reads in a sequence of real numbers from standard input and prints
     * out their average to standard output.
     *
     * @param args the command-line arguments
     *
     */
    public static void main(String[] args) {
        //number input values;
        int count = 0;
        //sum of input values;
        double sum = 0.0;
        //read data and compute statistics
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            sum += value;
            count++;
        }

        double average = sum / count;
        StdOut.println("\nAverage is " + average);
    }

}
