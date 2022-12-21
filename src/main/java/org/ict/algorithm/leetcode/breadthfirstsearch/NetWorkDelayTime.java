package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w),
 * where u is the source node, v is the target node, 
 * and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. 
 * How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * Example 1:
 * 
 *     1
 * 2------>3
 * |       |
 * | 1    1|
 * |       |
 * v       v
 * 1       4
 * 
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 *  
 * 
 * Note:
 * 
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 * 
 * LC743
 *
 */
public class NetWorkDelayTime {

	public static void main(String[] args) {
		int[][] times = {{2,1,1}, {2,3,1}, {3,4,1}};
		int N = 4, K = 2;
		int result = bellmanFordAlgoV2(times, N, K);
		System.out.println(result);
	}
	
	/**
	 * Runtime: 28 ms, faster than 42.42% of Java online submissions for Network Delay Time.
	 * Memory Usage: 42.6 MB, less than 97.62% of Java online submissions for Network Delay Time.
	 * @param times
	 * @param N
	 * @param K
	 * @return
	 * @see <a href="https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/BellmanFordSP.java"></a>
	 * The Bellman-Ford algorithm solves the single-source shortest-paths problem from a given source s
	 * (or finds a negative cycle reachable from s)
	 * for any edge-weighted digraph with V vertices and E edges, 
	 * in time proportional to E V and extra space proportional to V, in the worst case.
	 */
	public static int bellmanFordAlgoV2(int[][] times, int N, int K) {
		if (times == null || times.length == 0 || times[0].length == 0) {
			return -1;
		}
		double[] distTo = new double[N];// max index is N - 1, because index start from 0.
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[K - 1] = 0;// K - 1 represent the K-th Node's index
		Queue<Integer> queue = new LinkedList<>();
		boolean[] onQueue = new boolean[N];
		
		queue.offer(K);
		onQueue[K-1] = true;
		while (!queue.isEmpty() ) {
			int u = queue.poll();
			onQueue[u-1] = false;
			for(int[] edge : times) {
				if (edge[0] != u) {
					continue;
				}
				// edge: u-->v
				int v = edge[1];
				int w = edge[2];
				if (distTo[v-1] > distTo[u-1] + w) {
					distTo[v-1] = distTo[u-1] + w;
					if (!onQueue[v - 1]) {
						queue.offer(v);
						onQueue[v-1] = true;
					}
				}
			}
		}
		
		double res = Double.MIN_VALUE;
		for (double cost : distTo) {
			res = Math.max(cost, res);
		}
		return res == Double.POSITIVE_INFINITY ? -1 :  (int)res;
	}
	
	/**
	 * Start from node K, find the minimum weight of reach all nodes from K.
	 * 
	 * Initialize distTo[s] to 0 and all other distTo[] values to infinity. 
	 * Then, considering the digraph's edges in any order, and relax all edges. Make V such passes.
	 * We do not consider this version in detail because it always relaxes V E edges.
	 * 
	 * Time complexity: O(N*E), Space complexity: O(N)
	 * 
	 * Runtime: 44 ms, faster than 22.88% of Java online submissions for Network Delay Time.
	 * Memory Usage: 43 MB, less than 90.48% of Java online submissions for Network Delay Time.
	 * 
	 * @param times
	 * @param N
	 * @param K
	 * @return
	 */
	public static int bellmanFordAlgoV1(int[][] times, int N, int K) {
		if (times == null || times.length == 0 || times[0].length == 0) {
			return -1;
		}
		double[] distTo = new double[N];// max index is N - 1, because index start from 0.
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[K - 1] = 0;// K - 1 represent the K-th Node's index
		for (int i = 0; i < N; i++) {
			for (int[] edge : times) {
				// u-->v
				int u = edge[0] - 1; // from node index
				int v = edge[1] - 1; // to node index
				int w = edge[2];
				
				distTo[v] = Math.min(distTo[v], distTo[u] + w);
			}
		}
		
		double res = Double.MIN_VALUE;
		for (double cost : distTo) {
			res = Math.max(cost, res);
		}
		return res == Double.POSITIVE_INFINITY ? -1 :  (int)res;
	}
	
	/**
	 * Time complexity: O(Nlog(N) + E), Space complexity: O(N + E)
	 * @param times
	 * @param N
	 * @param K
	 * @return
	 */
	public int dijkstraAlgoV1(int[][] times, int N, int K) {
		if (times == null || times.length == 0 || times[0].length == 0) {
			return -1;
		}
		return -1;
	}
	
	/**
	 * Time complexity: O(N^3), Space complexity: O(N^2)
	 * @param times
	 * @param N
	 * @param K
	 * @return
	 */
	public int floydWarshallAlgoV1(int[][] times, int N, int K) {
		if (times == null || times.length == 0 || times[0].length == 0) {
			return -1;
		}
		return -1;
	}
}
