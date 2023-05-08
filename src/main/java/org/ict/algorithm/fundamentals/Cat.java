package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.Out;

/**
 * $ javac org/ict/algorithm/fundamentals/Cat.java 
 * $ java org/ict/algorithm/fundamentals/Cat  ../resources/whitelist.log  ../resources/out-test.log  ../resources/cat-out-test.log
 *
 * $ Cat  ../resources/whitelist.log
 *   1
 *   2
 *   3
 *   4
 *   5
 * 
 * $ cat ../resources/out-test.log 
 *   hello
 *   Princess
 *
 * $ cat ../resources/cat-out-test.log 
 *   1
 *   2
 *   3
 *   4
 *   5
 *
 * Reads in text files specified as the first command-line
 * arguments, concatenates them, and writes the result to 
 * filename specified as the last command-line arguments.
 *  
 * The {@code Cat} class provides a client for concatenating the results
 * of several text files.
 * <p>
 * For additional documentation, see <a href="">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i>by Robert Sedgewick and Kevin Wayne.
 */

public class Cat {
    //this class should not be instantiated
    private Cat () {}

    public static void main(String[] args) {
        Out out = new Out(args[args.length - 1]);
        for (int i = 0; i < args.length -1; i++) {
            In in = new In(args[i]);
            String s = in.readAll();
            out.println(s);
            in.close();
        }
    }


}
