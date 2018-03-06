package org.ict.algorithm.sort;

import java.security.SecureRandom;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

public class SecureShuffle {
    
    public static void main(String[] args) {
        //read in the data
        String[] a = StdIn.readAllStrings();
        int n = a.length;

        //create random array of real numbers between 0 and 1
        SecureRandom random = new SecureRandom();
        Double[] r = new Double[n];
        for (int i = 0; i < n; i++) {
            r[i] = random.nextDouble();
        }

        //shuffle and get resulting permutation
        int[] index = Merge.indexSort(r);

        //print permutation
        for (int i = 0; i < n; i++) {
            StdOut.println(a[index[i]]);
        }
    }

}
