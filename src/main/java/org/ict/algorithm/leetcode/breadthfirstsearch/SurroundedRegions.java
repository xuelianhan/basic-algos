package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
		//sr.solutionV2(board);
	}
	
	public static final char FIPPING_CHAR = 'O';
    
	public static final char TARGET_CHAR = 'X';

    public static final char TEMP_CHAR = 'N';

	public static final int[] dx = {-1, 0, 0, 1};
    
    public static final int[] dy = {0, 1, -1, 0};

    /**
     * Right Solution
     * 1.First replace all connected O to temp char N;
     * 2.Second replace reserved O to target char X;
     * 3.Third replace the temp char N back to O;
     *
     */
    public void solutionV2(char[][] board) {
    	System.out.println("input:" + Arrays.deepToString(board));
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return;
		}
		Queue<Integer> queue = new LinkedList<>();
		int m = board.length, n = board[0].length;
		// top row
		for (int i = 0; i < n; i++) {
			if (board[0][i] == FIPPING_CHAR) {
				bfs(board, 0, i, queue);
			}
		}
		// bottom row
		for (int i = 0; i < n; i++) {
			if (board[m-1][i] == FIPPING_CHAR) {
				bfs(board, m - 1, i, queue);
			}
		}
		// left column
		for (int i = 0; i < m; i++) {
			if (board[i][0] == FIPPING_CHAR) {
				bfs(board, i, 0, queue);
			}
		}
		// right column
		for (int i = 0; i < m; i++) {
			if(board[i][n-1] == FIPPING_CHAR) {
				bfs(board, i, n-1, queue);
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == FIPPING_CHAR) {
					board[i][j] = TARGET_CHAR;
				}
				if (board[i][j] == TEMP_CHAR) {
					board[i][j] = FIPPING_CHAR;
				}
			}
		}
		System.out.println("output:" + Arrays.deepToString(board));
	}

    /**
	 * position = row * colNum + col;
	 * row = position / colNum;
	 * col = position % colNum;
	 * 
	 * @param board
	 * @param row
	 * @param col
	 * @param queue
	 */
	private void bfs(char[][] board, int row, int col, Queue<Integer> queue) {
		queue.clear();
		int colNum = board[0].length;
		queue.add(row * colNum + col);
		board[row][col] = TEMP_CHAR;
		while (!queue.isEmpty()) {
			int position = queue.poll();
			int r = position / colNum;
			int c = position % colNum;
			for (int j = 0; j < dx.length; j++) {
				int x = r + dx[j];
				int y = c + dy[j];
				if (!inRegion(x, y, board)) {
					continue;
				}
				if (board[x][y] == FIPPING_CHAR) {//is flipping char means it has been not visited.
					queue.add(x * colNum + y);//here not write to x * colNum + r, otherwise is wrong.
					board[x][y] = TEMP_CHAR;// marked to temp_char as it has been visited.
				}
			}
		}
	}

    /**
     * Wrong solution
     *
     */
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
		Set<String> waitFlipSet = new HashSet<>();
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
				
				if (isFlipChar(curX, curY, board)&& onBoarder(curX, curY, board)) {
					hasBoarderO = true;
					System.out.println("(" + curX + ", " + curY + ")" + " is on BoarderO:" );
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
					waitFlipSet.add(curX + "" + curY);
				}
			}
		}//end-while-loop
		if (!waitFlipSet.isEmpty()) {
			for (String point : waitFlipSet) {
				int curX = (point.charAt(0) - '0');
				int curY = (point.charAt(1) - '0');
				board[curX][curY] = TARGET_CHAR;
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
		
		public Point(int x, int y, String val) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "x = " + x + ", y = " + y + ", val = " + val;
		}
	}
}
