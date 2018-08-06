package org.ict.algorithm.graphs;

import java.util.Iterator;
import org.ict.algorithm.fundamentals.Stack;
import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdOut;

/**
 * $ cat ../resources/tinyCG.txt 
 * 6
 * 8
 * 0 5
 * 2 4
 * 2 3
 * 1 2
 * 0 1
 * 3 4
 * 3 5
 * 0 2
 * $ java org/ict/algorithm/graphs/Graph ../resources/tinyCG.txt 
 * 6 vertices, 8 edges 
 * 0: 2 1 5 
 * 1: 0 2 
 * 2: 0 1 3 4 
 * 3: 5 4 2 
 * 4: 3 2 
 * 5: 3 0 
 *
 * $ javac org/ict/algorithm/graphs/NonrecursiveDFS.java 
 * Note: org/ict/algorithm/graphs/NonrecursiveDFS.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
 * $ java org/ict/algorithm/graphs/NonrecursiveDFS ../resources/tinyCG.txt 0
 * check 2
 * stack:2 0 dfs(2)
 * check 0
 * check 1
 * stack:1 2 0 dfs(1)
 * check 0
 * check 2
 * 1 done
 * check 3
 * stack:3 2 0 dfs(3)
 * check 5
 * stack:5 3 2 0 dfs(5)
 * check 3
 * check 0
 * 5 done
 * check 4
 * stack:4 3 2 0 dfs(4)
 * check 3
 * check 2
 * 4 done
 * check 2
 * 3 done
 * check 4
 * 2 done
 * check 1
 * check 5
 * 0 done
 * 0 1 2 3 4 5 
 *
 * The {@code NonrecursiveDFS} class represents a data type for finding
 * the vertices connected to a source vertex <em>s</em> in the undirected
 * graph.
 *
 * <p>
 * This implementation uses a nonrecursive version of depth-first search
 * with an explicit stack.
 * The constructor takes time proportional to <em>V</em> + <em>E</em>,
 * where <em>V</em> is the number of vertices and <em>E</em> is the number
 * of edges.
 * It uses extra space (not including the graph) proportional to <em>V</em>.
 * <p>
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class NonrecursiveDFS {

    // marked[v] = is there an s-v path ?
    private boolean[] marked;

    /**
     * Computes the vertices connected to the source vertex {@code s} in the graph {@code G}.
     *
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public NonrecursiveDFS(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }

        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>(); 
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                StdOut.printf("check %d\n", w);
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    // edgeTo[w] = v;
                    stack.push(w);
                    StdOut.printf("stack:" + stack + "dfs(%d)\n", w);
                }
            } else {
                StdOut.printf("%d done\n", v);
                stack.pop();
            }
        }
    }


    /**
     * Is Vertex {@code v} connected to the source vertex {@code s}?
     *
     * @param v the vertex
     * return {@code true} if vertex {@code v} is connected to the source vertex {@code s},
     *        {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1)); 
        }
    }

    /**
     * Unit tests the {@code NonrecursiveDFS} data type.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        NonrecursiveDFS dfs = new NonrecursiveDFS(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }
}
