package org.ict.algorithm.search;

import java.io.File;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
/**
 * $ javac org/ict/algorithm/search/FileIndex.java 
 * $ java org/ict/algorithm/search/FileIndex ex*.txt
 * $ java org/ict/algorithm/search/FileIndex ../resources/ex*.txt
 * indexing files
 * ../resources/ex1.txt
 * ../resources/ex2.txt
 * ../resources/ex3.txt
 * ../resources/ex4.txt
 * age
 *  ex3.txt
 *  ex4.txt
 * best
 *  ex1.txt
 * was
 *  ex1.txt
 *  ex2.txt
 *  ex3.txt
 *  ex4.txt
 * it
 *  ex1.txt
 *  ex2.txt
 *  ex3.txt
 *  ex4.txt
 *
 * The {@code FileIndex} class provides a client for indexing a set of files,
 * sepecified as command-line arguments. It takes queries from standard input
 * and prints each file that contains the given query.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class FileIndex {
    // Do not instantiate
    private FileIndex() {}

    public static void main(String[] args) {
        // key = word, value = set of files containing that word
        ST<String, SET<File>> st = new ST<String, SET<File>>();

        // create inverted index of files
        StdOut.println("indexing files");
        for (String filename : args) {
            StdOut.println(" " + filename);
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                String word = in.readString();
                if (!st.contains(word)) {
                    st.put(word, new SET<File>());
                }
                SET<File> set = st.get(word);
                set.add(file);
            }
        }

        // read queries from standard input, one per line
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                SET<File> set = st.get(query);
                for (File file : set) {
                    StdOut.println(" " + file.getName());
                }
            }
        }
        
    }
}
