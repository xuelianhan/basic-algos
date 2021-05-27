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
		int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
		ShortestPathVisitingAllNodes instance = new ShortestPathVisitingAllNodes();
		int result = instance.shortestPathLengthV1(graph);
		System.out.println(result);
		/**
		 * 
		 * bitMaskTest();
		 */
	}

	/**
	 * How one will approach in 45 min interview?
	 * Very first thing comes in undirected graph for finding shortest path is bfs. Since, bfs ensures that a node at distance k will be visited first then a node at k+1 distance. But problem given here, is slightly different. Here in the problem it is given that we can visit any node any number of time, even we can use edge more than once.
	 *
	 * This means that we can't use simple bfs (in which we maintain a visited set - to avoid cycle) because in the problem we can visit a node any number of time.
	 * Take an example to understand why simple bfs will not work?
	 *
	 * 0 -- 1
	 * | \
	 * 2  3
	 * If we start bfs from 0,
	 * {0} -> {0,1} -> stuck as we have visited 0 already.
	 * If we start bfs from 1,
	 * {1}->{1,0}->{1,0,3}-> Stuck as we already visited 0
	 * You got the idea.
	 *
	 * So, the point which I am trying to make here is this,
	 *
	 * If we apply simple bfs starting from any node(say 0) where we also keep track of visited array (Consecutively meaning we can't visit them again) it will never lead us to the solution.
	 * On the other hand if we apply bfs from any node (say 0) and don't keep the track of visited array, it will lead to cycle [ {0}->{0,1}->{0,1,0}->{0,1,0,1} and so on]
	 * What if there is a way, in which we can visit the same node again and still avoid cycle. lets call this type of bfs as intelligent bfs.
	 * that means,
	 *
	 * If starting node is 0
	 * {0}->{0,1}->{0,1,0}-{0,1,0,3}->{0,1,0,3,0,2}
	 * If starting node is 1,
	 * {1}->{1,0}->{1,0,2}->{1,0,3}->{1,0,3,0}->{1,0,3,0,2}
	 *
	 * That means we still need to iterate this intelligent bfs from each node and see from which node we are getting minimum answer.
	 *
	 * Now, lets assume that we have devised this intelligent bfs.
	 *
	 * Simultaneously BFS
	 * What if we apply bfs on every node in one go. In other words, start bfs on each node simulatneously? That is, Queue for bfs will now be
	 *
	 * [ {0},{1},{2},{3} ] -> [ {0,1} ,{0,2},{0,3}, {1,0}, {2,0}, {3,0}  ] -> [...]
	 * where each step(which is [...] ) in bfs will contains set of nodes or path visited in bfs fashion for each of the element in previous step.
	 *
	 * The advantage (If we have that intelligent bfs) we will reach the answer fastly rather then applying bfs individually as in individual bfs we need to extend bfs unecessary for current starting node, while we would have reached to the answer earlier if we would have started using some other starting node.
	 *
	 * Intelligent bfs should be such which will able to detect if including current node will result into cycle or not.
	 * Take an example,
	 *
	 * Take individual bfs starting from 0
	 * {0}->{0,1}->{0,1,0}->Now we should not got to {0,1,0,1}.
	 *
	 * If you see we already visited set of nodes {0,1} while we are at 0. (bold above). So we must not include 1 again as resulting visited list will become {0,1} again.
	 *
	 * Take another example,
	 * Starting from 1,
	 *
	 * {1}->{1,0}->{1,0,1}(We should visited this as by visiting 1 again (currently current node is 1)
	 * this could lead to some other path which would have connected to 1 only)
	 * but -> {1,0,1,0} (This shouldn't be visited) as by including 0 again, this is just repetition of 0 and 1 again in other words, by including 0 again resulting visited set will become {0,1} which we already visited(bold abov)
	 *
	 * In nutshell, for each current head(or leading) node we must maintain a space where we can check whether we visited that set of path again or not).
	 *
	 * Thats, why in editorial of bfs solution dist 2d matrix is used, dist[cover][head] where cover is rows and head are coloumns.
	 * head/coloums will denote all nodes and cover/rows will denote all possible combination of set of nodes which is visited.
	 * For keep tracking of combination of nodes, bit masking is used.
	 *
	 * For bit masking,
	 * [https://www.hackerearth.com/practice/algorithms/dynamic-programming/bit-masking/tutorial/]
	 *
	 * Each cell that is dist[i][j] represent if jth node is the leading node, then what is the path distance (min distance) we have covered if all set bits in i nodes are visited.
	 *
	 * So We will implements simulatneously bfs starting from each node, and keep track of distance using dist 2d matrix.
	 * Due to simulatneously bfs over all node, whenever we encouter that i where all bits are set, that will be the answer, as it is bfs in undirected graph which ensures that a node at distance k will be visited first then a node at k+1 distance.
	 *
	 * Now, read the original bfs solution given in the editorial, Things will become more clear.
	 * @author niks_vat
	 * @param graph
	 * @return
	 */
	public static int shortestPathLength(int[][] graph) {

		return 0;
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
	public int shortestPathLengthV1(int[][] graph) {
		int N = graph.length;
        Queue<Tuple> queue = new LinkedList<>();
        Set<Tuple> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
        	// bitMask range in (1, 2, 4, 8, 16, 32...2^n)
			//2^i
            int bitMask = (1 << i);
            set.add(new Tuple(bitMask, i, 1));
            queue.add(new Tuple(bitMask, i, 1));
        }
        
        while(!queue.isEmpty()){
            Tuple curr = queue.remove();
            System.out.println("curr node is:" + curr + ", N:" + N + ", (1 << N) - 1:" + ((1 << N) - 1));
			// (1 << length) means 2^length
            // (1 << length) - 1) means all bits are 1, such as 1,3,7,15,31,63....(2^n -1)
			// all the nodes have been visited
            if (curr.bitMask == (1 << N) - 1) {
            	System.out.println("return cost:" + (curr.cost - 1));
                return curr.cost - 1;
            }
			int[] neighbors = graph[curr.curr];
			for (int v : neighbors) {
				int bitMask = curr.bitMask;
				// (1 << v) means 2^v
				// bitMask | (1 << v) means the node v is marked as visited
				bitMask = bitMask | (1 << v);

				Tuple t = new Tuple(bitMask, v, 1);
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
	        this.cost = cost;
	    }

		@Override
	    public boolean equals(Object o) {
	        Tuple p = (Tuple) o;
	        return bitMask == p.bitMask && curr == p.curr && cost == p.cost;
	    }
		@Override
	    public int hashCode(){
	        return 1331 * bitMask + 7193 * curr + 727 * cost;
	    }

	    @Override
	    public String toString() {
	    	return "bitMask:" + bitMask + ", curr:" + curr + ", cost:" + cost;
		}
	}
	
	private static void bitMaskTest() {
		for (int i = 0; i < 5; i++) {
            int bitMask = (1 << i);
            System.out.println("i:" + i + ", bitMask:" + bitMask);
		}
	}
}
