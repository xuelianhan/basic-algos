package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.ST;

/**
 * The {@code SymbolGraph} represents an undirected graph, where the
 * vertex names are arbitrary strings.
 * By providing mappings between string vertex names and integers,
 * it serves as a wrapper around the {@link Graph} data type, which
 * assumes the vertex names are integers between 0 and <em>V</em> - 1.
 * It also supports initializing a symbol graph from a file.
 * <p>
 * This implementation uses an {@link ST} to map from strings to integers,
 * an array to map from integers to strings, and a {@link Graph} to store
 * the underlying graph.
 * The <em>indexOf</em> and <em>contains</em> operations take time 
 * proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 * The <em>nameOf</em> operation takes constant time.
 * 
 * @author Robert SedgeWick
 * @author Kevin Wayne
 *
 */
public class SymbolGraph {
	// string -> index
	private ST<String, Integer> st;
	
	// index -> string
	private String[] keys;
	
	// the underlying graph
	private Graph graph;
	
	
}
