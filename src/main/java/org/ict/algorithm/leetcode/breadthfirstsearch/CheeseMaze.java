package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @see https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * @see https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 * @see https://codereview.stackexchange.com/questions/127665/shortest-path-in-maze
 * 
 * Starting from the top-left corner, traverse the area
 * Return an integer representing the total distance to the destination 9, if not found return -1.
 * cell value 1 means accessible, 0 means inaccessible.
 * 
 * Example
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
 */
public class CheeseMaze {

	public static void main(String[] args) {
		CheeseMaze maze = new CheeseMaze();
		List<List<Integer>> area = new ArrayList<>();
		int[] row1 = new int[] {1, 0, 0};
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
		int result1 = maze.minimumDistanceV1(numRows, numColumns, area);
		System.out.println(result1);
		
		int result2 = maze.minimumDistanceV1(numRows, numColumns, area);
		System.out.println(result2);
	}
	
	private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};
    
	public int minimumDistanceV2(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numRows > 1000) {
			return -1;
		}
		if (numColumns < 1 || numColumns > 1000) {
			return -1;
		}
		if (area == null || area.size() == 0 || area.get(0).size() == 0) {
			return -1;
		}
		if (area.get(0).get(0) == 9) {
			return 1;
		}
		
		// BFS
		int distance = 0;
		int m = area.size();
		int n = area.get(0).size();
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0, 0});
		area.get(0).set(0, 1);
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int[] next = {cur[0] + dx[i], cur[1] + dy[i]};
				if (next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n) {
                    if (area.get(next[0]).get(next[1]) == 9) {
                    	distance++;
                    	break;
                    } else if (area.get(next[0]).get(next[1]) == 0) {
                        queue.offer(next);
                        area.get(next[0]).set(next[1], 1);
                    }
                }
			}
		}
		return distance;
	}
	

	
	public int minimumDistanceV1(int numRows, int numColumns, List<List<Integer>> area) {
		int distance = 0;
		//An flag array to indicate whether current point has been visited.
        int[][] used = new int[numRows][numColumns];

        int currentRow = 0;
        int currentColumn = 0;
        do {
        	// start point is a corner case.
            if (area.get(currentRow).get(currentColumn) == 9) {
                return distance++;
            } else if (area.get(currentRow).get(currentColumn) == 1 && used[currentRow][currentColumn] == 0) {
            	// current point has not been visited, set to visited status 1.
                used[currentRow][currentColumn] = 1;
                distance++;
                
                if (currentRow - 1 >= 0 && area.get(currentRow - 1).get(currentColumn) != 0
                        && used[currentRow - 1][currentColumn] == 0) {
                	// go to last row
                    currentRow = currentRow - 1;
                } else if (currentRow + 1 < numRows && area.get(currentRow + 1).get(currentColumn) != 0
                        && used[currentRow + 1][currentColumn] == 0) {
                	// got to next row
                    currentRow = currentRow + 1;
                } else if (currentColumn - 1 >= 0 && area.get(currentRow).get(currentColumn - 1) != 0
                        && used[currentRow][currentColumn - 1] == 0) {
                	// go to last column
                    currentColumn = currentColumn - 1;
                } else if (currentColumn + 1 < numColumns && area.get(currentRow).get(currentColumn + 1) != 0
                        && used[currentRow][currentColumn + 1] == 0) {
                	// go to next column
                    currentColumn = currentColumn + 1;
                }
            } else {
                break;
            }
        } while (true);
		return -1;
	}
}
