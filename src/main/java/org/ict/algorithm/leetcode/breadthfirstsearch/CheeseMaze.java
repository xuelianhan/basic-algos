package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.List;

/**
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
		
	}
	
	public int minimumDistanceV2(int numRows, int numColumns, List<List<Integer>> area) {
		
		return -1;
	}
	
	public int minimumDistanceV1(int numRows, int numColumns, List<List<Integer>> area) {
		int distance = 0;
        int[][] used = new int[numRows][numColumns];

        int currentRow = 0;
        int currentColumn = 0;
        do {
        	// start point is a corner case.
            if (area.get(currentRow).get(currentColumn) == 9) {
                return distance++;
            } else if (area.get(currentRow).get(currentColumn) == 1 && used[currentRow][currentColumn] == 0) {
                used[currentRow][currentColumn] = 1;
                distance++;
                if (currentRow - 1 >= 0 && area.get(currentRow - 1).get(currentColumn) != 0
                        && used[currentRow - 1][currentColumn] == 0) {
                    currentRow = currentRow - 1;
                } else if (currentRow + 1 < numRows && area.get(currentRow + 1).get(currentColumn) != 0
                        && used[currentRow + 1][currentColumn] == 0) {
                    currentRow = currentRow + 1;
                } else if (currentColumn - 1 >= 0 && area.get(currentRow).get(currentColumn - 1) != 0
                        && used[currentRow][currentColumn - 1] == 0) {
                    currentColumn = currentColumn - 1;
                } else if (currentColumn + 1 < numColumns && area.get(currentRow).get(currentColumn + 1) != 0
                        && used[currentRow][currentColumn + 1] == 0) {
                    currentColumn = currentColumn + 1;
                }
            } else {
                break;
            }
        } while (true);
		return -1;
	}
}
