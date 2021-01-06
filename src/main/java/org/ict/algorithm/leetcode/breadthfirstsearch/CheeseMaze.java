package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @see https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * @see https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 * @see https://codereview.stackexchange.com/questions/127665/shortest-path-in-maze
 * @see https://stackoverflow.com/questions/16366448/maze-solving-with-breadth-first-search
 * @see https://www.careercup.com/question?id=5749266532270080
 * @see https://wdxtub.com/interview/14520850399861.html
 * 
 * There is a maze of size M*N. Tom is sitting at (0, 0). Jerry is sitting in another cell(
 * the position jerry is input 9). Then there are k pieces of cheese placed in k different cells
 * (k <= 1000).Some cells are blocked(marked with 0) while some are not(marked with 1).Tom can
 * move to 4 cells at any point of time(left, right, up, down one position).Tom has to collect
 * all the pieces of cheese and then reach Jerry's cell. You need to print the minimum number
 * of steps of this collecting path.
 * 
 * Starting from the top-left corner, traverse the area
 * Return an integer representing the total distance to the destination 9, if not found return -1.
 * cell value 1 means accessible, 0 means inaccessible.
 * 
 * Example1
 * Input: numRows = 3, numColumns = 3
 * area=
 * [
 *   [1, 0, 0],
 *   [1, 0, 0]
 *   [1, 9, 1]
 * ]
 * Output:3 means (0, 0) -> (1. 0) -> (2, 0) -> (2, 1)
 * 1<= numRows, numColumns <= 1000
 * 
 * Example2
 * {1,0,0,0,0,0,0,0,0},
 * {1,1,1,0,0,0,0,0,0},
 * {0,0,0,1,0,0,0,0,0},
 * {0,0,0,1,0,0,0,0,0},
 * {0,0,0,1,1,0,0,0,0},
 * {0,0,0,0,0,1,0,0,0},
 * {0,0,0,0,0,0,1,0,0},
 * {0,0,0,0,0,0,1,1,1},
 * {0,0,0,0,0,0,0,0,9},
 *
 * This problem is one kind of amz oa
 * 
 */
public class CheeseMaze {

	/**
	 * Unit Test 
	 * @param args
	 */
	public static void main(String[] args) {
		CheeseMaze maze = new CheeseMaze();
		List<List<Integer>> area = new ArrayList<>();
		// Test case
		/**
		int[] row1 = new int[] {1, 0, 0}; 
		int[] row2 = new int[] {1, 0, 0}; 
		int[] row3 = new int[] {1, 1, 1};
		*/
		
		// Test case
		/**
		int[] row1 = new int[] {1, 0, 0}; 
		int[] row2 = new int[] {1, 0, 0}; 
		int[] row3 = new int[] {1, 9, 1};
		*/
		
		// Test case
		
		int[] row1 = new int[] {0, 0, 0}; 
		int[] row2 = new int[] {1, 0, 0}; 
		int[] row3 = new int[] {1, 9, 1};
		
		
		//see https://www.techiedelight.com/convert-int-array-list-integer/
		List<Integer> r1 = Arrays.stream(row1).boxed().collect(Collectors.toList());
		area.add(r1);
		List<Integer> r2 = Arrays.stream(row2).boxed().collect(Collectors.toList());
		area.add(r2);
		List<Integer> r3 = Arrays.stream(row3).boxed().collect(Collectors.toList());
		area.add(r3);
		
		int numRows = 3;
		int numColumns = 3;
		
		int result4 = maze.minimumDistanceV4(numRows, numColumns, area);
		System.out.println("result4:" + result4);
		System.out.println("=========================");
		
		int result2 = maze.minimumDistanceV2(numRows, numColumns, area);
		System.out.println("result2:" + result2);
		System.out.println("=========================");
		
		
		int result1 = maze.minimumDistanceV1(numRows, numColumns, area);
		System.out.println("result1:" + result1);
		System.out.println("=========================");
		
		/**
		 * Notice:
		 * The following method v3 will change the original input array named area.
		 * So it's put at the last line of main test.
		 */
		int result3 = maze.minimumDistanceV3(numRows, numColumns, area);
		System.out.println("result3:" + result3);
	}
	
	// The exit gate flag
	private static final int exitPointValue = 9;
	// The accesible flag
	private static final int accessible = 1;
	// The inaccessible flag
	private static final int inaccessible = 0;
	// Indicate the cell has been visited, it's value can be any number different with accessible, inaccessible and exitPointValue.
	private static final int visitedFlag = -1;
	
	// See from top to down view.
	// dx=-1, dy=0 means move left
	// dx=0, dy=1 means move up
	// dx=0, dy=-1 means move down
	// dx=1, dy=0 means move right
	private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};
    
	private static class Point {
		private int x;
		private int y;
		Point parent;
		
		public Point(int x, int y, Point parent) {
			this.x = x;
			this.y = y;
			this.parent = parent;
		}
		
		public Point getParent() {
			return this.parent;
		}
		
		public String toString() {
			return "x = " + x + ", y = " + y;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
			if (parent == null) {
				if (other.parent != null)
					return false;
			} else if (!parent.equals(other.parent))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	/**
	 * Nonrecursive Depth-First-Search method with an explicit stack instead of recursion.
	 * This method will not change the original input array area via introducing an auxiliary hash map space named marked.
	 * @param numRows
	 * @param numColumns
	 * @param area
	 * @return
	 */
	public int minimumDistanceV4(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numRows > 1000) {
			return -1;
		}
		if (numColumns < 1 || numColumns > 1000) {
			return -1;
		}
		if (area == null || area.size() == 0 || area.get(0).size() == 0 || area.get(0).get(0) == inaccessible) {
			return -1;
		}
		if (area.get(0).get(0) == exitPointValue) {
			return 1;
		}
		
		// use DFS to find the distance from start point(0, 0) to destination point with value of exitPointValue
		// use dy.length is ok too.
		int directionSize = dx.length;
		
		// Key in distTo is a point, value in distTo is a distance to start point(0, 0).
		Map<Point, Integer> distTo = new LinkedHashMap<>();
		// marked.get(currentPoint) = is there a startPoint-currentPoint path?
		Map<Point, Boolean> marked = new LinkedHashMap<>();
		// Initialize all points with false mark.
		for (int i = 0; i < area.size(); i++) {
			for (int j = 0; j < area.get(i).size(); j++) {
				marked.put(new Point(i, j, null), Boolean.FALSE);
			}
		}
		// Initialize a queue and put start point into this queue.
		Stack<Point> stack = new Stack<>();
		Point startPoint = new Point(0, 0, null);
		stack.push(startPoint);
		
		// set Point (0, 0) value with true to indicate it has been visited.
		marked.put(startPoint, true);
		// Point (0, 0) to itself's distance is 0.
		distTo.put(startPoint, 0);

		boolean hasExit = false;
		while (!stack.isEmpty()) {
			Point cur = stack.pop();
			System.out.println("cur is " + cur);
			if (area.get(cur.x).get(cur.y) == exitPointValue) {
				System.out.println("Exit is reached!");
				hasExit = true;
				break;
			}
			for (int i = 0; i < directionSize; i++) {
				Point next = new Point(cur.x + dx[i], cur.y + dy[i], null);
				if (isFree(next, area) && !marked.get(next)) {
					// Set flag with -1 to indicate this cell has been visited.
					marked.put(next, true);
					// Put Point next to queue.
                    stack.push(next);
                    int distance = (distTo.get(cur) == null ? 0 : distTo.get(cur) + 1);
                    distTo.put(next, distance);
                }
			}
		}
		System.out.println(distTo);
		return (hasExit ? distTo.size() - 1 : -1);
	}
	
	/**
	 * Use Breath-First-Search method to calculate the distance to exit cell.
	 * Notice: This method will change the original input array area.
	 * @param numRows
	 * @param numColumns
	 * @param area
	 * @return
	 */
	public int minimumDistanceV3(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numRows > 1000) {
			return -1;
		}
		if (numColumns < 1 || numColumns > 1000) {
			return -1;
		}
		if (area == null || area.size() == 0 || area.get(0).size() == 0 || area.get(0).get(0) == inaccessible) {
			return -1;
		}
		if (area.get(0).get(0) == exitPointValue) {
			return 1;
		}
		Queue<Point> queue = new LinkedList<Point>();
		// Initialize the start cell (0, 0).
		queue.add(new Point(0, 0, null));
		boolean[] hasExit = new boolean[] {false};
		Point p = pathBFS(queue, area, hasExit);
		System.out.println("hasExit:" + hasExit[0]);
		return getDistance(p, hasExit);
	}
	
	/**
	 * Use Breath-First-Search method to calculate the distance to exit cell.
	 * Notice: This method will change the original input array area.
	 * @param queue
	 * @param area
	 * @return
	 */
	public Point pathBFS(Queue<Point> queue, List<List<Integer>> area, boolean[] hasExit) {
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			System.out.println("current point is: " + p);
			if (area.get(p.x).get(p.y) == exitPointValue) {
				System.out.println("Exit is reached!");
				hasExit[0] = true;
				System.out.println("hasExit:" + hasExit[0]);
                return p;
			}
			
			// try to move left
			if (isFree(p.x + 1, p.y, area)) {
				area.get(p.x).set(p.y, visitedFlag);
				Point nextPoint = new Point(p.x + 1, p.y, p);
				queue.add(nextPoint);
			}
			
			// try to move right
			if (isFree(p.x - 1, p.y, area)) {
				area.get(p.x).set(p.y, visitedFlag);
				Point nextPoint = new Point(p.x - 1, p.y, p);
				queue.add(nextPoint);
			}
			
			// try to move up
			if (isFree(p.x, p.y + 1, area)) {
				area.get(p.x).set(p.y, visitedFlag);
				Point nextPoint = new Point(p.x, p.y + 1, p);
				queue.add(nextPoint);
			}
			
			// try to move down
			if (isFree(p.x, p.y - 1, area)) {
				area.get(p.x).set(p.y, visitedFlag);
				Point nextPoint = new Point(p.x, p.y - 1, p);
				queue.add(nextPoint);
			}
		}
		return null;
	}
	
	public int getDistance(Point p, boolean[] hasExit) {
		int distance = 0;
		while (p != null && p.getParent() != null) {
			System.out.println(p);
			distance++;
			p = p.getParent();
		}
		return (hasExit[0] ? distance : -1);
	}
	
	public boolean isFree(int x, int y, List<List<Integer>> area) {
		if ((x >=0 && x < area.size()) && 
		    (y >=0 && y < area.get(x).size()) &&
		    (area.get(x).get(y) == accessible || area.get(x).get(y) == exitPointValue)) {
			return true;
		}
		return false;
	}
	
	private boolean isFree(Point next, List<List<Integer>> area) {
		if ((next.x >= 0 && next.x < area.size()) && 
				(next.y >= 0 && next.y < area.get(0).size()) && 
				(area.get(next.x).get(next.y) == accessible || area.get(next.x).get(next.y) == exitPointValue)) {
			return true;
		}
		return false;
	}
	
	
    
    /**
     * Use Breath-First-Search method to calculate the distance to exit cell.
     * This method will not change the original input array area.
     * @param numRows
     * @param numColumns
     * @param area
     * @return
     */
	public int minimumDistanceV2(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numRows > 1000) {
			return -1;
		}
		if (numColumns < 1 || numColumns > 1000) {
			return -1;
		}
		if (area == null || area.size() == 0 || area.get(0).size() == 0 || area.get(0).get(0) == inaccessible) {
			return -1;
		}
		if (area.get(0).get(0) == exitPointValue) {
			return 1;
		}
		
		// use BFS to find the distance from start point(0, 0) to destination point with value of exitPointValue
		// use dy.length is ok too.
		int directionSize = dx.length;
		
		// Key in distTo is a point, value in distTo is a distance to start point(0, 0).
		Map<Point, Integer> distTo = new LinkedHashMap<>();
		// marked.get(currentPoint) = is there a startPoint-currentPoint path?
		Map<Point, Boolean> marked = new LinkedHashMap<>();
		// Initialize all points with false mark.
		for (int i = 0; i < area.size(); i++) {
			for (int j = 0; j < area.get(i).size(); j++) {
				marked.put(new Point(i, j, null), Boolean.FALSE);
			}
		}
		// Initialize a queue and put start point into this queue.
		Queue<Point> queue = new LinkedList<>();
		Point startPoint = new Point(0, 0, null);
		queue.offer(startPoint);
		
		// set Point (0, 0) value with -1 to indicate it has been visited.
		//area.get(0).set(0, visitedFlag);
		marked.put(startPoint, true);
		// Point (0, 0) to itself's distance is 0.
		distTo.put(startPoint, 0);

		boolean hasExit = false;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			System.out.println("cur is " + cur);
			if (area.get(cur.x).get(cur.y) == exitPointValue) {
				System.out.println("Exit is reached!");
				hasExit = true;
				break;
			}
			for (int i = 0; i < directionSize; i++) {
				Point next = new Point(cur.x + dx[i], cur.y + dy[i], null);
				if (isFree(next, area) && !marked.get(next)) {
					// Set flag with -1 to indicate this cell has been visited.
					//area.get(cur.x).set(cur.y, visitedFlag);
					marked.put(next, true);
					
					// Put Point next to queue.
                    queue.offer(next);
                    int distance = (distTo.get(cur) == null ? 0 : distTo.get(cur) + 1);
                    distTo.put(next, distance);
                }
			}
		}
		System.out.println(distTo);
		return (hasExit ? distTo.size() - 1 : -1);
	}
	
	
	
	/**
	 * Use DFS to find the path to exit point.
	 * This method will not change the original input array area via introducing an auxiliary 2-dimention array space with name of used.
	 * @param numRows
	 * @param numColumns
	 * @param area
	 * @return
	 */
	public int minimumDistanceV1(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numRows > 1000) {
			return -1;
		}
		if (numColumns < 1 || numColumns > 1000) {
			return -1;
		}
		if (area == null || area.size() == 0 || area.get(0).size() == 0 || area.get(0).get(0) == inaccessible) {
			return -1;
		}
		if (area.get(0).get(0) == exitPointValue) {
			return 1;
		}
		int distance = 0;
		int[][] used = new int[numRows][numColumns];

		int currentRow = 0;
		int currentColumn = 0;
		Stack<List<Integer>> routes = new Stack<>();

		traversal(numRows, numColumns, currentRow, currentColumn, area, used, routes);
		while (!routes.isEmpty()) {
			routes.pop();
			distance++;
		}
		// The following code will throw EmptyStackException if this stack is empty.
		/**
		 * do { 
		 *   routes.pop(); 
		 *   distance++; 
		 * } while (!routes.isEmpty());
		 */

		return distance - 1;
	}
	
	/**
	 * Recursive Depth-First-Search method with an explicit stack.
	 * @param numRows
	 * @param numColumns
	 * @param currentRow
	 * @param currentColumn
	 * @param area 
	 * @param used   An 2-dimention array to indicate whether the current cell has been visited or not.
	 * @param routes
	 * @return       The boolean flag to indicate whether has path to exit from current cell (currentRow, currentColumn).
	 */
	boolean traversal(int numRows, int numColumns, int currentRow, int currentColumn, List<List<Integer>> area,
			int[][] used, Stack<List<Integer>> routes) {
		if (used[currentRow][currentColumn] == 0) {
			// If current cell has not been visited, set it's flag with 1 to mark it has been accessed.
			used[currentRow][currentColumn] = 1;
			List<Integer> route = new ArrayList<>();
			route.add(currentRow);
			route.add(currentColumn);
			// Push current cell to the Stack.
			routes.push(route);
			if (area.get(currentRow).get(currentColumn) == exitPointValue) {
				return true;
			}

			if (currentRow - 1 >= 0 && area.get(currentRow - 1).get(currentColumn) != inaccessible) {
				boolean found = traversal(numRows, numColumns, currentRow - 1, currentColumn, area, used, routes);
				if (found) {
					return true;
				}
			}

			if (currentRow + 1 < numRows && area.get(currentRow + 1).get(currentColumn) != inaccessible) {
				boolean found = traversal(numRows, numColumns, currentRow + 1, currentColumn, area, used, routes);
				if (found) {
					return true;
				}
			}

			if (currentColumn - 1 >= 0 && area.get(currentRow).get(currentColumn - 1) != inaccessible) {
				boolean found = traversal(numRows, numColumns, currentRow, currentColumn - 1, area, used, routes);
				if (found) {
					return true;
				}
			}

			if (currentColumn + 1 < numColumns && area.get(currentRow).get(currentColumn + 1) != inaccessible) {
				boolean found = traversal(numRows, numColumns, currentRow, currentColumn + 1, area, used, routes);
				if (found) {
					return true;
				}
			}
			// If no route start with the current cell, pop this cell out from stack route and reset visit flag back to zero.
			routes.pop();
			used[currentRow][currentColumn] = 0;
		}

		return false;
	}

}
