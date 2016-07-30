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
            StdOut.printf("%d and %d are imcomparable!\n", first, second);
        }
    }

    public static void main(String[] args) {
        cmp(new Integer(42), 42);
        cmp(new Integer(42), 43);
        cmp(new Integer(42), new Integer(42));
        cmp(43, 43);
        cmp(143,143);

        double x1 = 0.0, y1 = -0.0;
        Double a1 = x1, b1 = y1;
        StdOut.println(x1 == y1);
        StdOut.println(a1.equals(b1));

        double x2 = 0.0/0.0, y2 = 0.0/0.0;
        Double a2 = x2, b2 = y2;
        StdOut.println(x2 != y2);
        StdOut.println(!a2.equals(b2));
    }

}
