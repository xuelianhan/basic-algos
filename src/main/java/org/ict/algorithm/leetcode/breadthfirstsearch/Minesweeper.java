package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Let's play the minesweeper game (Wikipedia, online game)!
You are given a 2D char matrix representing the game board. 
'M' represents an unrevealed mine, 
'E' represents an unrevealed empty square, 
'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, 
digit ('1' to '8') represents how many mines are adjacent to this revealed square, 
and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), 
return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.

If an empty square ('E') with no adjacent mines is revealed, 
then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.

If an empty square ('E') with at least one adjacent mine is revealed, 
then change it to a digit ('1' to '8') representing the number of adjacent mines.

Return the board when no more squares will be revealed.

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:

Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

 

Note:

The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'),
 which also means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. 
For example, you don't need to reveal all the unrevealed mines when the game is over, 
consider any cases that you will win the game or flag any squares.
 *
 * LC529
 */
public class Minesweeper {

	 public char[][] updateBoard(char[][] board, int[] click) {
		 
		 return null; 
	 }
	 
	 /**
	  * 2 ms 39.7 MB
	  * @param board
	  * @param click
	  * @return
	  */
	 public char[][] bfs(char[][] board, int[] click) {
		 int m = board.length, n = board[0].length;
		 Queue<int[]> queue = new LinkedList<>();
		 queue.add(click);
		 
		 // Total 8 directions coordinate.
		 int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
		 while (!queue.isEmpty()) {
			 int[] cell = queue.poll();
			 int row = cell[0], col = cell[1];
			 // If touch a mine, game over
			 if (board[row][col] == 'M') {
				 board[row][col] = 'X';
				 return board;
			 }
			 
			 // Count the adjacent mines
			 int mineCount = 0;
			 for (int[] dir : dirs) {
				 int x = row + dir[0], y = col + dir[1];
				 if (x < 0 || x >= m || y < 0 || y >= n) {
					 continue;
				 }
				 if (board[x][y] == 'M') {
					 mineCount++;
				 }
			 }
			 
			 if (mineCount > 0) {
				 // if current cell has a adjacent mine, change its value to mineCount
				 board[row][col] = (char)(mineCount + '0');
			 } else {
				 // if current cell has no adjacent mine, change its value to 'B', and add it's neighbors(at most 8 directions) to the queue. 
				 board[row][col] = 'B';
				 for (int[] dir : dirs) {
					 int x = row + dir[0], y = col + dir[1];
					 if (x < 0 || x >= m || y < 0 || y >= n) {
						 continue;
					 }
					 if (board[x][y] == 'E') {
						 board[x][y] = 'B';// used as a visit flag
						 queue.add(new int[] {x, y});
					 }
				 }
				 
			 }
		 }
		 return board;
	 }
	 
	 public char[][] dfs(char[][] board, int[] click) {
		 int m = board.length, n = board[0].length;
		 int row = click[0], col = click[1];
		 if (board[row][col] == 'M') {
			 board[row][col] = 'X';
			 return board;
		 }
		 
		 int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
		 int mineCount = 0;
		 for (int[] dir : dirs) {
			 int x = row + dir[0], y = col + dir[1];
			 if (x < 0 || x >= m || y < 0 || y >= n) {
				 continue;
			 }
			 if (board[x][y] == 'M') {
				 mineCount++;
			 }
		 }
		 
		 if (mineCount > 0) {
			 // if current cell has a adjacent mine, change its value to mineCount
			 board[row][col] = (char)(mineCount + '0');
		 } else {
			 board[row][col] = 'B';
			 for (int[] dir : dirs) {
				 int x = row + dir[0], y = col + dir[1];
				 if (x < 0 || x >= m || y < 0 || y >= n) {
					 continue;
				 }
				 if (board[x][y] == 'E') {
					 dfs(board, new int[] {x, y}); 
				 }
			 }
		 }
		 return board;
	 }
}
