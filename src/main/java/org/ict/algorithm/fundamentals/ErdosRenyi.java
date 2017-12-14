package org.ict.algorithm.fundamentals;

/**
 * Random connections 
 * Develop a UF client ErdosRenyi.java
 * that takes an integer value N from the command line, generates random
 * pairs of integers between 0 and N-1, calling connected() to determine 
 * if they are connected and then union() if not(as in our development client),
 * looping until allsites are connected, and printing the number of connections 
 * generated
 * Package your program as a static method count() that takes N as argument and
 * returns the number of connections and a main() that takes N from the command
 * line, calls count(), and prints the returned value
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 *
 */
public class ErdosRenyi {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
    }

}
