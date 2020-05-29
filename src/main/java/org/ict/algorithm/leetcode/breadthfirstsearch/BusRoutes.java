package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We have a list of bus routes. 
 * Each routes[i] is a bus route that the i-th bus repeats forever. 
 * For example if routes[0] = [1, 5, 7], 
 * this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
 * Travelling by buses only, what is the least number of buses we must take to reach our destination?
 * Return -1 if it is not possible.
 * 
 * Example:
 * Input: 
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation: 
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *  
 * 
 * Constraints:
 * 
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5.
 * 0 <= routes[i][j] < 10 ^ 6.
 * LC815
 * 
 *
 */
public class BusRoutes {
	
	public static void main(String[] args) {
		int[][] routes = {{1, 2, 7}, {3, 6, 7}};
		int S = 1, T = 6;
		int result = numBusesToDestination(routes, S, T);
		System.out.println(result);
	}

	/**
	 * Runtime: 29 ms, faster than 70.13% of Java online submissions for Bus Routes.
	 * Memory Usage: 58.4 MB, less than 82.35% of Java online submissions for Bus Routes.
	 * 
	 * @param routes
	 * @param S
	 * @param T
	 * @return
	 */
	public static int numBusesToDestination(int[][] routes, int S, int T) {
		if (S == T) {
			return 0;
		}
		
		// key is the bus station, value is the buses pass this station
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				graph.putIfAbsent(routes[i][j], new ArrayList<>());
				// for bus station routes[i][j], add the i-th bus into it.
				graph.get(routes[i][j]).add(i);
			}
		}
		
		int res = 1;
		// store buses has been visited
		Set<Integer> visitedBus = new HashSet<>();
		// store the bus station node
		Queue<Integer> stationQueue = new LinkedList<>();
		
		stationQueue.add(S);
		while (!stationQueue.isEmpty()) {
			int size = stationQueue.size();
			//res++;// if put <code>res++;</code> here, the res initial value should be 0
			// level order traversal
			for (int i = 0; i < size; i++) {
				// get the bus list of current bus station.
				Integer curStation = stationQueue.poll();
				List<Integer> busList = graph.get(curStation);
				for (Integer bus : busList) {
					// if the bus is visited, skip this bus, 
					
					if (visitedBus.contains(bus)) {
						continue;
					}
					// otherwise we get this bus's station and compare its station one by one with target station.
					// if target station is in this bus's route, we get the destination.
					visitedBus.add(bus);
					for (int j = 0; j < routes[bus].length; j++) {
						if (routes[bus][j] == T) {
							return res;
						}
						// if target station is not met, add current station into the queue.
						if (curStation != routes[bus][j]) {// avoid push into the queue repeatly to save memory, this line can be removed 
							stationQueue.offer(routes[bus][j]);
						}
						System.out.println("curStation:" + curStation + ", stationQueue:" + stationQueue);
					}
				}
			}// end level-order-traversal
			res++;//if put <code>res++;</code> here, the res initial value should be 1
		}
        return -1;
    }
}
