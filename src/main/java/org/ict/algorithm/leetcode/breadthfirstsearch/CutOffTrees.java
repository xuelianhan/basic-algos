package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;


/**
 * 
 * You are asked to cut off trees in a forest for a golf event. 
 * The forest is represented as a non-negative 2D map, in this map:
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 * 
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. 
 * And after cutting, the original place has the tree will become a grass (value 1).
 * 
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. 
 * If you can't cut off all the trees, output -1 in that situation.
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 * 
 * Example 1:
Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6

Example 2:
Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
 
Example 3:
Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
Hint: size of the given matrix will not exceed 50x50.
 *
 * LC675
 */
public class CutOffTrees {
	
	public static void main(String[] args) {
		CutOffTrees maze = new CutOffTrees();
		List<List<Integer>> forest = new ArrayList<>();
		// Test case
		/**
		int[] row1 = new int[] {1, 2, 3}; 
		int[] row2 = new int[] {0, 0, 4}; 
		int[] row3 = new int[] {7, 6, 5};
		*/
		
		// Test case
		/**
		int[] row1 = new int[] {1, 2, 3}; 
		int[] row2 = new int[] {0, 0, 0}; 
		int[] row3 = new int[] {7, 6, 5};
		*/
		
		// Test case
		/**
		int[] row1 = new int[] {1, 1, 1}; 
		int[] row2 = new int[] {0, 0, 1}; 
		int[] row3 = new int[] {8, 1, 1};
		*/
		
		int[] row1 = new int[] {8, 9, 10}; 
		int[] row2 = new int[] {13, 12, 11}; 
		int[] row3 = new int[] {16, 15, 14};
		
		/**
        int[] row1 = new int[] {54581641,64080174,24346381,69107959};
        int[] row2 = new int[] {86374198,61363882,68783324,79706116};
        int[] row3 = new int[] {668150,92178815,89819108,94701471};
        int[] row4 = new int[] {83920491,22724204,46281641,47531096};
        int[] row5 = new int[] {89078499,18904913,25462145,60813308};
		*/
		//see https://www.techiedelight.com/convert-int-array-list-integer/
		List<Integer> r1 = Arrays.stream(row1).boxed().collect(Collectors.toList());
		forest.add(r1);
		List<Integer> r2 = Arrays.stream(row2).boxed().collect(Collectors.toList());
		forest.add(r2);
		List<Integer> r3 = Arrays.stream(row3).boxed().collect(Collectors.toList());
		forest.add(r3);
		/**
		List<Integer> r4 = Arrays.stream(row4).boxed().collect(Collectors.toList());
		forest.add(r4);
		List<Integer> r5 = Arrays.stream(row5).boxed().collect(Collectors.toList());
		forest.add(r5);
		 */
		
		int result1 = maze.cutOffTree(forest);
		System.out.println("result1:" + result1);
		
	}
	
	// The grass flag
	private static final int grassFlag = 1;
	// The inaccessible flag
	private static final int inaccessible = 0;
	
	private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};
    
    private static final Map<Point, Integer> distTo = new LinkedHashMap<>();
    
	public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0 || forest.get(0).size() == 0) {
            return -1;
        }
        if (forest.size() > 50 || forest.get(0).size() > 50) {
        	return -1;
        }
        if (forest.get(0).get(0) == inaccessible) {
        	return -1;
        }
        Queue<Point> queue = new PriorityQueue<Point> (new Comparator<Point> () {
            @Override
            public int compare(Point a, Point b) {
            	if (a.weight == b.weight) {
            		return 0;
            	}
                return ((a.weight > b.weight) ? 1 : -1);
            }
        });
        
		// Collect all tree nodes of forest into priority queue and sorted by weight.
		collectTrees(forest, queue);
		if (queue.isEmpty()) {
			return -1;
		}
		
		// Initialize the start cell (0, 0).
		Point src = new Point(0, 0, forest.get(0).get(0), null);
		int totalSteps = 0;
		while (!queue.isEmpty()) {
			Point des = queue.poll();
			Point p = bfs(forest, src, des);
			int distance = getDistance(p);
			totalSteps += distance;
			System.out.println("go to des " + des.weight + " from " + src.weight + ", distance:" + distance + ", lastPoint:" + p);
			src = des;
		}
		/**
		for (Integer v: distTo.values()) {
			totalSteps += v;
		}
		*/
        return totalSteps;
	}
	
	/**
	 * compute steps from src to des in forest with BFS search.
	 * @param forest
	 * @param src
	 * @param des
	 * @return
	 */
	private Point bfs(List<List<Integer>> forest, Point src, Point des) {
		Queue<Point> queue = new LinkedList<>();
		Map<Point, Boolean> marked = new LinkedHashMap<>();
		// Initialize the start cell (0, 0).
		queue.add(src);
		marked.put(src, true);
		int steps = 0;
		Point lastPoint = null;
		while (!queue.isEmpty()) {
			for (int j = 0; j < queue.size(); j++) {
				Point cur = queue.poll();
				cur.setParent(lastPoint);
				lastPoint = cur;
				
				forest.get(cur.x).set(cur.y, grassFlag);
				if (cur.equals(des)) {
					distTo.put(des, steps);
					return lastPoint;
				}
				for (int i = 0; i < dx.length; i++) {
					if (isFree(cur.x + dx[i], cur.y + dy[i], forest)) {
						Point next = new Point(cur.x + dx[i], cur.y + dy[i], forest.get(cur.x + dx[i]).get(cur.y + dy[i]), null);
						if (marked.get(next) != Boolean.TRUE) { 
							marked.put(next, true); 
							queue.add(next); 
						}
					}
				}
				System.out.println("queue: " + queue);
			}
			steps++;
		}
		return null;
	}
	
	private boolean isFree(int x, int y, List<List<Integer>> forest) {
		if ((x >=0 && x < forest.size()) && 
		    (y >=0 && y < forest.get(x).size()) &&
			(forest.get(x).get(y) >= grassFlag)) {
			return true;
		}
		return false;
	}
	
	public int getDistance(Point p) {
		int distance = 0;
		while (p != null && p.getParent() != null) {
			distance++;
			p = p.getParent();
		}
		return distance;
	}
	
	private void collectTrees(List<List<Integer>> forest, Queue<Point> queue) {
		for (int i = 0; i < forest.size(); i++) {
			for (int j = 0; j < forest.get(i).size(); j++) {
				Point p = new Point(i, j, forest.get(i).get(j), null);
				distTo.put(p, -1);
				if (forest.get(i).get(j) > grassFlag) {
					queue.add(p);
				}
			}
		}
		
	}
	 
	private static class Point {
		private int x;

		private int y;
		
		private long weight;
		
		Point parent;
		
		public Point(int x, int y, int weight, Point parent) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.parent = parent;
		}
		
		public Point getParent() {
			return this.parent;
		}
		
		public void setParent(Point parent) {
			this.parent = parent;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		public String toString() {
			return "(" + x + ", " + y + ", " + weight +")";
		}
	}
}
