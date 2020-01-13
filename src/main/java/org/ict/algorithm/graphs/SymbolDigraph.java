package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.ST;

public class SymbolDigraph {

	// string -> index
	private ST<String, Integer> st;
	
	// index -> string
	private String[] keys;
	
	// the underlying digraph
	private Digraph graph;
	/**
	 * Initializes a digraph from a file using the specified delimiter.
	 * Each line in the file contains the name of a vertex, followed by
	 * a list of the names of the vertices adjacent to that vertex, 
	 * separated by the delimiter.
	 * @param filename the name of the file
	 * @param delimiter the delimiter between fields
	 */
	public SymbolDigraph(String filename, String delimiter) {
		st = new ST<String, Integer>();
		// First pass builds the index by reading strings to associate
		// distinct strings with an index
	}
}
