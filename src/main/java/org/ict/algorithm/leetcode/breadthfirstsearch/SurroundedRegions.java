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
 * e.g:
 * X X X X
 * X O X X
 * X O X X
 * X O X X
 * 
 * After flipping the board:
 * X X X X
 * X X X X
 * X O X X
 * X O X X
 * 
 * Before flipping:
 * [   0   1   2   3   4
 * 0 ["O","X","X","O","X"],
 * 1 ["X","O","O","X","O"],
 * 2 ["X","O","X","O","X"],
 * 3 ["O","X","O","O","O"],
 * 4 ["X","X","O","X","O"]]
 * 
 * After flipping:
 * [   0   1   2   3   4
 * 0 ["O","X","X","O","X"],
 * 1 ["X","X","X","X","O"],
 * 2 ["X","X","X","O","X"],
 * 3 ["O","X","O","O","O"],
 * 4 ["X","X","O","X","O"]]
 *
 * LC130
 */
public class SurroundedRegions {
	
	public static void main(String[] args) {
		char[][] board = {{'O','X','X','O','X'},
				          {'X','O','O','X','O'},
				          {'X','O','X','O','X'},
				          {'O','X','O','O','O'},
				          {'X','X','O','X','O'}
				         };
		//char[][] board = {{'O'}};
		//char[][] board = {};
		SurroundedRegions sr = new SurroundedRegions();
		sr.solutionV1(board);
	}
	
	public static final char FIPPING_CHAR = 'O';
	public static final char TARGET_CHAR = 'X';
	
	public void solutionV1(char[][] board) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return;
		}
		int[] dx = {-1, 0, 0, 1};
	    int[] dy = {0, 1, -1, 0};
	    Map<String, Boolean> visited = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer("00");
		visited.put("00", true);
		System.out.println("input:" + Arrays.deepToString(board));
		while(!queue.isEmpty()) {
			String curPoint = queue.poll();
			boolean hasNeighborX = false;
			boolean hasBoarderO = false;
			int curX = (curPoint.charAt(0) - '0');
			int curY = (curPoint.charAt(1) - '0');
			for (int j = 0; j < dx.length; j++) {
				int x = curX + dx[j];
				int y = curY + dy[j];
				if (!inRegion(x, y, board)) {
					continue;
				}
				String nextPoint = x + "" + y;
				System.out.println("curPoint: (" + curX + ", " + curY + ")" + ", has nextPoint:" + "(" + x + ", " + y + ")" );
				if (board[x][y] == TARGET_CHAR) {
					hasNeighborX = true;
					System.out.println("(" + curX + ", " + curY + ")" + " has NeighborX:" + "(" + x + ", " + y + ")" );
				}
				
				if (isFlipChar(x, y, board)&& onBoarder(x, y, board)) {
					hasBoarderO = true;
					System.out.println("(" + x + ", " + y + ")" + " is on BoarderO:" );
				}
				if (isVisited(nextPoint, visited)) {
					continue;
				} else {
					visited.put(nextPoint, true);
					queue.offer(nextPoint);
				}
				if(insideRegion(curX, curY, board) 
						&& (board[curX][curY] == FIPPING_CHAR) 
						&& hasNeighborX 
						&& !hasBoarderO) {
					board[curX][curY] = TARGET_CHAR;
				}
			}
		}
		System.out.println("output:" + Arrays.deepToString(board));
	}
	
	private boolean onBoarder(int x, int y, char[][] board) {
		if (x == 0 || y == 0 || (x == (board.length - 1)) || (y == (board[0].length - 1))) {
			return true;
		}
		return false;
	}
	
	private boolean isFlipChar(int x, int y, char[][] board) {
		if (board[x][y] == FIPPING_CHAR) {
			return true;
		}
		return false;
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
	
	private boolean isVisited(String point, Map<String, Boolean> visited) {
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
		
		private boolean visited;
		
		private boolean hasExit;
		
		public Point(int x, int y, String val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
		
		public String toString() {
			return "{\"x\":" + x + ",\"y\":" + y + ",\"val\":" + val + ",\"visited\":" + visited + ",\"hasExit\":" + hasExit + "}";
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public boolean isHasExit() {
			return hasExit;
		}

		public void setHasExit(boolean hasExit) {
			this.hasExit = hasExit;
		}
	}
}