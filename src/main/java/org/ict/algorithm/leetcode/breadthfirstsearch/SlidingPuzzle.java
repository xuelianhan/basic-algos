package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. 
 * If it is impossible for the state of the board to be solved, return -1.
 * 
 * Examples:
 * 
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * 
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * 
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],
 *                [5,0,3]]
 * After move 1: [[4,1,2],
 *                [0,5,3]]
 * After move 2: [[0,1,2],
 *                [4,5,3]]
 * After move 3: [[1,0,2],
 *                [4,5,3]]
 * After move 4: [[1,2,0],
 *                [4,5,3]]
 * After move 5: [[1,2,3],
 *                [4,5,0]]
 * 
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * Note:
 * 
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 *
 * 0 1 2
 * 3 4 5 --> 0 can go to index of {1, 3}
 * 
 * 1 0 2
 * 3 4 5 --> 0 can go to index of {0,2,4}
 * 
 * 1 2 0
 * 3 4 5 --> 0 can go to index of {1, 5}
 * 
 * 1 2 3
 * 0 4 5 --> 0 can go to index of {0,4}
 * 
 * 1 2 3
 * 4 0 5 --> 0 can go to index of {1, 3, 5}
 * 
 * 1 2 3
 * 4 5 0 --> 0 can go to index of {2, 4}
 * 
 * LC773
 *
 */
public class SlidingPuzzle {
	
	public static void main(String[] args) {
		int[][] board = {{4,1,2}, {5,0,3}};
		int result = slidingPuzzle(board);
		System.out.println(result);
	}

	public static int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		start += board[i][j];
        	}
        }
        
        int res = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        // All positions of zero can move to, the sequence cannot be modified.
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	// level control is necessary, can't remove.
        	// If remove level control, the res will count all variable situations.
        	// we want traversal graph level by level(also known as level order traversal) and stop at the level when we meet the target.
        	// we can see the movement as a tree from top view:
        	// 1. start from 412503, it's a root of the tree
        	// 2. we get 402513, 412053 at second level
        	// 3. we get 042513, 420513, 012453 at third level
        	// 4. we get 542013, 423510, 102453 at fourth level
        	// 5. we get 542103, 423501, 120453 at fifth level
        	// 6. we get 423051, 123450 at the sixth level, at this time we meet the target, this is the end.
        	// the depth of level-traversal is 5.
        	for (int i = 0; i < size; i++) {
        		String cur = queue.poll();
            	if (cur.equals(target)) {
            		return res;
            	}
            	int zeroIdx = cur.indexOf('0');
            	for (int dir : dirs[zeroIdx]) {
            		String next = swap(cur, zeroIdx, dir);
            		if (visited.contains(next)) {
            			continue;
            		}
            		visited.add(next);
            		queue.offer(next);
            	}
        	}// level traversal ending.
        	res++;
        }
        return -1;
    }
	
	private static String swap(String str, int i, int j) {
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(i, str.charAt(j));
		sb.setCharAt(j, str.charAt(i));
		return sb.toString();
	}
}
