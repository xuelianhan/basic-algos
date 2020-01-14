package org.ict.algorithm.graphs;

import org.ict.algorithm.fundamentals.ST;
import org.ict.algorithm.util.In;

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
	
	/**
	 * Initializes a graph from a file using the specified delimiter.
	 * Each line in the file contains the name of a vertex, followed by 
	 * a list of the names of the vertices adjacent to that vertex,
	 * separated by the delimiter.
	 * @param filename the name of the file
	 * @param delimiter the delimiter between fields
	 */
	public SymbolGraph(String filename, String delimiter) {
		st = new ST<String, Integer>();
		// First pass builds the index by reading strings to associate
		// distinct strings with an index
		In in = new In(filename);
		while (!in.isEmpty()) {
			String[] a = in.readLine().split(delimiter);
			for (int i = 0; i < a.length; i++) {
				if (!st.contains(a[i])) {
					st.put(a[i], st.size());
				}
			}
		} //end-while-loop
		
		// inverted index to get strign keys in an array
		keys = new String[st.size()];
		for (String name : st.keys()) {
			keys[st.get(name)] = name;
		}
		
		// second pass builds thee graph by connecting first vertex 
		// on each line to all others.
		graph = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delimiter);
			int v = st.get(a[0]);
			for (int i = 1; i < a.length; i++) {
				int w = st.get(a[i]);
				graph.addEdge(v, w);
			}
		}
	}
	
	/**
	 * Does the graph contain the vertex named {@code s}?
	 * @param s the name of a vertex
	 * @param {@code true} if {@code s} is the name of a vertex, and
	 *        {@code false} otherwise
	 */
	public boolean contains(String s) {
		return st.contains(s);
	}
	
	/**
	 * Returns the integer associated with the vertex named {@code s}.
	 * @param s the name of a vertex
	 * @return the integer (between 0 and <em>V</em> - 1) associated with
	 *         the vertex named {@code s}
	 */
	public int indexOf(String s) {
		return st.get(s);
	}
	
	/**
	 * Returns the name of the vertex associated with the integer {@code v}.
	 * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 * @return the name of the vertex associated with the integer {@code v}
	 */
	public String nameOf(int v) {
		validateVertex(v);
		return keys[v];
	}
	
	/**
	 * throw an IllegalArgumentException unless {@code 0 <= v < V}
	 */
	private void validateVertex(int v) {
		int V = graph.V();
		if ( v < 0 || v > V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
		}
	}
	
	/**
	 * Returns the graph associated with the symbol graph.
	 * It is the client's responsibility not to mutate the graph.
	 * @return the graph associated with the symbol graph
	 */
	public Graph graph() {
		return graph;
	}
	
	public static void main(String[] args) {
		
	}
}
