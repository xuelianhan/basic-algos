package org.ict.algorithm.util;

public class Autoboxing {
    public static void cmp(Integer first, Integer second) {
        if (first < second) {
            StdOut.printf("%d < %d\n", first, second);
        } else if (first == second) {
            StdOut.printf("%d == %d\n", first, second);
        } else if (first > second) {
            StdOut.printf("%d > %d\n", first, second);
        } else {
            StdOut.printf("%d and %d are imcomparable!", first, second);
        }
    }

    public static void main(String[] args) {
        cmp(new Integer(42), 42);
        cmp(new Integer(42), 43);
        cmp(new Integer(42), 41);
    }

}
