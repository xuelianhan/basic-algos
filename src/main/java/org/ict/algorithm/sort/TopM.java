package org.ict.algorithm.sort;

import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.fundamentals.Transaction;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * Given an integer m from the command line and an input stream where
 * each line contains a String and a long value, this MinPQ client 
 * prints the m lines whose numbers are the highest.
 * 
 * The {@code TopM} class provides a client that reads a sequence of 
 * transactions from standard input and prints the <em>m</em> largest
 * ones to standard output. This implementation uses a {@link MinPQ} 
 * of size at most <em>m</em> + 1 to the <em>M</em> largest transactions
 * and a {@link Stack} to output them in the proper order.
 * 
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class TopM {

	// This class should not be instantiated.
	private TopM() {}
	
	/**
	 * Reads a sequence of transactions from standard input; takes a
	 * command-lin integer m; printts to standard output the m largest
	 * transactions in descending order.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		int m = Integer.parseInt(args[0]);
		MinPQ<Transaction> pq = new MinPQ<Transaction>(m+1);
		
		while (StdIn.hasNextLine()) {
			// Create an entry from the next line and put on the pq
			String line = StdIn.readLine();
			Transaction transaction = new Transaction(line);
			pq.insert(transaction);
			
			// remove minimum if m+1 entries on the PQ
			if (pq.size() > m) {
				pq.delMin();
			}
			//top m entries are on the pq
		}
		
		// print the entries on pq in reverse order
		Stack<Transaction> stack = new Stack<Transaction>();
		for (Transaction transaction : pq) {
			stack.push(transaction);
		}
		for (Transaction transaction : stack) {
			StdOut.println(transaction);
		}
		
	}
			
	
}
