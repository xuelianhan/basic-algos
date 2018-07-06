package org.ict.algorithm.search;


import org.ict.algorithm.fundamentals.Queue;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code LookupIndex} class provides a data-driven client for reading in a 
 * key-value pairs from a file; then, printing the values corresponding to the
 * keys found on standard input. Keys are strings; values are lists of strings.
 * The separating delimiter is taken as a command-line argument. This client is
 * sometimes known as an <em>inverted index</em>.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LookupIndex {

    // Do not instantiate
    private LookupIndex() {}

    public static void main(String[] args) {
        String filename  = args[0];
        String separator = args[1];
        In in = new In(filename);
        
        ST<String, Queue<String>> st = new ST<String, Queue<String>>();
        ST<String, Queue<String>> ts = new ST<String, Queue<String>>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(separator);
            String key = fields[0];
            for (int i = 1; i < fields.length; i++) {
                String val = fields[i];
                if (!st.contains(key)) {
                    st.put(key, new Queue<String>());
                }
                if (!ts.contains(val)) {
                    ts.put(val, new Queue<String>());
                }
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }

        StdOut.println("Done indexing");

        // read queries from standard input, one per line
        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query)) {
                for (String vals : st.get(query)) {
                    StdOut.println(" " + vals);
                }
            }
            if (ts.contains(query)) {
                for (String keys.ts.get(query)) {
                    StdOut.println(" " + keys);
                }
            }
        }
    }
}
