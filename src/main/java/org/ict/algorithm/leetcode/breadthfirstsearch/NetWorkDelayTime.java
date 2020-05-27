package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;

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

	public int networkDelayTime(int[][] times, int N, int K) {
		return -1;
	}
	
	/**
	 * Start from node K, find the minimum weight of reach all nodes from K.
	 * 
	 * Initialize distTo[s] to 0 and all other distTo[] values to infinity. 
	 * Then, considering the digraph's edges in any order, and relax all edges. Make V such passes.
	 * We do not consider this version in detail because it always relaxes V E edges.
	 * 
	 * Time complexity: O(N*E), Space complexity: O(N)
	 * @param times
	 * @param N
	 * @param K
	 * @return
	 */
	public int bellmanFordAlgoV1(int[][] times, int N, int K) {
		if (times == null || times.length == 0 || times[0].length == 0) {
			return -1;
		}
		double[] distTo = new double[N];// max index is N - 1, because index start from 0.
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[K - 1] = 0;// K - 1 represent the K-th Node's index
		
		return -1;
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
