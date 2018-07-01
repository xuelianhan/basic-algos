package org.ict.algorithm.search;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

public class DeDup {
    // Do not instantiate.
    private DeDup() {}

    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // read in strings and add to set
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (!set.contains(key)) {
                set.add(key);
                StdOut.println(key);
            }
        }
    }
}
