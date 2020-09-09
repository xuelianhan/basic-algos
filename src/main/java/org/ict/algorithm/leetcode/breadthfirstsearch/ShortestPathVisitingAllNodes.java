package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 * graph.length = N, and j != i is in the list graph[i] exactly once, 
 * if and only if nodes i and j are connected.
 * 
 * Return the length of the shortest path that visits every node. 
 * You may start and stop at any node, 
 * you may revisit nodes multiple times, 
 * and you may reuse edges.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * Example 2:
 * 
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *  
 * 
 * Note:
 * 
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 * 
 * LC847
 * High Frequency:https://ac.nowcoder.com/discuss/292850
 *
 */
public class ShortestPathVisitingAllNodes {
	
	public static void main(String[] args) {
		bitMaskTest();
	}
	/**
	 * Idea is to use BFS to traverse the graph.
	 * Since all edges are weighted 1, we can use a Queue (instead of a PriorityQueue sorted by cost). 
	 * Since all edges are weighted 1, then closer nodes will always be evaluated before farther ones.
	 * In order to represent a path, I used a combination of 3 variables:
	 * 
	 * int bitMask: mask of all the nodes we visited so far: 0 -> not visited, 1 -> visited 
	 * (Originally this was Set<Integer>of all nodes we visited so far, 
	 * but since the Solution TLE and N <= 12, it turns out we can use a bitMask and 32 bits total in an Integer can cover all the possibilities. 
	 * This is a small speed up optimization.)
	 * 
	 * int curr: current node we are on
	 * int cost: the total cost in the path.
	 * 
	 * Each path taken will have a unique combination of these 3 variables.
	 * We initialize our queue to contain N possible paths each starting from [0,N-1].
	 * This is because we can start at any of N possible Nodes.
	 * At each step, we remove element from the queue and see if we have covered all 12 nodes in our bitMask.
	 * If we cover all nodes, we return the cost of the path immediately.
	 * Since we are using BFS, this is guranteed to be path with the lowest cost.
	 * Otherwise, we get all the neighbors of current node, and for each neighbor,
	 * set the Node to visited in bitMask, and then add it back into the queue.
	 * In order to prevent duplicate paths from being visited, we use a Set<Tuple> to store the Set<Path> that we have visited before. 
	 * Since we don't really need the cost here, I set cost to 0 for elements stored in Set.
	 * You could also set the actual cost value here, it wouldn't make a difference
	 * 
	 * @author simonzhu91
	 * @param graph
	 * @return
	 */
	public int shortestPathLength(int[][] graph) {
		int length = graph.length;
        Queue<Tuple> queue = new LinkedList<>();
        Set<Tuple> set = new HashSet<>();

        for (int i = 0; i < length; i++) {
            int bitMask = (1 << i);//2^i
            set.add(new Tuple(bitMask, i, 1));
            queue.add(new Tuple(bitMask, i, 1));
        }
        
        while(!queue.isEmpty()){
            Tuple curr = queue.remove();
			// (1 << length) means 2^length
            // (1 << length) - 1) means all 1 in bits, such as 1,3,7,15,31,63....(2^n -1)
            if (curr.bitMask == (1 << length) - 1) {
                return curr.cost - 1;
            }
            
            int[] neighbors = graph[curr.curr];
            for (int v : neighbors) {
                int bitMask = curr.bitMask;
                bitMask = bitMask | (1 << v);
                
                Tuple t = new Tuple(bitMask, v, 0);
                if (!set.contains(t)) {
                    queue.add(new Tuple(bitMask, v, curr.cost + 1));
                    set.add(t);
                }         
            }
        }
        return -1;
    }
	
	class Tuple{
	    int bitMask;//mask of all the nodes we visited so far: 0 -> not visited, 1 -> visited
	    int curr;// current node
	    int cost;// the total cost in the path
	    
	    public Tuple(int bit, int node, int cost) {
	        bitMask = bit;
	        curr = node;
	        cost = cost;
	    }
	    
	    public boolean equals(Object o) {
	    	if (!(o instanceof Tuple)) {
	    		return false;
	    	}
	        Tuple p = (Tuple) o;
	        return bitMask == p.bitMask && curr == p.curr && cost == p.cost;
	    }
	    
	    public int hashCode(){
	        return 1331 * bitMask + 7193 * curr + 727 * cost;
	    }
	}
	
	private static void bitMaskTest() {
		for (int i = 0; i < 5; i++) {
            int bitMask = (1 << i);
            System.out.println("i:" + i + ", bitMask:" + bitMask);
		}
	}
}
