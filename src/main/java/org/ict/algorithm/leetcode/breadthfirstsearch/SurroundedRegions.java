package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 * Surrounded regions shouldn’t be on the border, 
 * which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 *
 * LC130
 */
public class SurroundedRegions {
	
	public static void main(String[] args) {
		char[][] board = {{'X', 'X', 'X'},
				          {'X', 'O', 'X'},
				          {'X', 'X', 'X'}
				         };
		//char[][] board = {{'O'}};
		//char[][] board = {};
		SurroundedRegions sr = new SurroundedRegions();
		sr.solutionV1(board);
		System.out.println(Arrays.deepToString(board));
	}
	
	private static Map<String, Boolean> visited = new HashMap<>();
	
	private static final char FIPPING_CHAR = 'O';
	
	private static final char TARGET_CHAR = 'X';
	
	public void solutionV1(char[][] board) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return;
		}
		int[] dx = {-1, 0, 0, 1};
	    int[] dy = {0, 1, -1, 0};
		
		Queue<String> queue = new LinkedList<>();
		queue.offer("00");
		visited.put("00", true);
		while(!queue.isEmpty()) {
			String curPoint = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int x = (curPoint.charAt(0) - '0') + dx[i];
				int y = (curPoint.charAt(1) - '0') + dy[i];
				String nextPoint = x + "" + y;
				if (inRegion(x, y, board) && !isVisited(nextPoint)) {
					visited.put(nextPoint, true);
					queue.offer(nextPoint);
					if(insideRegion(x, y, board) && matchflipChar(x, y, board)) {
						board[x][y] = TARGET_CHAR;
					}
				}
			}
		}
	}
	
	private boolean matchflipChar(int x, int y, char[][] board) {
		return (board[x][y] == FIPPING_CHAR);
	}
	
	private boolean insideRegion(int x, int y, char[][] board) {
		if (x >0 && x < (board.length -1) && y >0 && y < (board[0].length - 1)) {
			return true;
		}
		return false;
	}
	
	private boolean inRegion(int x, int y, char[][] board) {
		if (x >=0 && x < board.length && y >=0 && y < board[0].length) {
			return true;
		}
		return false;
	}
	
	private boolean isVisited(String point) {
		if (point == null || point.length() == 0) {
			return true;
		}
		if (visited.get(point) == null || visited.get(point) == false) {
			return false;
		}
		return true;
	}
	
	private static class Point {
		private int x;
		private int y;
		
		private String val;
		
		public Point(int x, int y, String val) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "x = " + x + ", y = " + y + ", val = " + val;
		}
	}
}
