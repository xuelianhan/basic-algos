package org.ict.algorithm.leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Design a Snake game that is played on a device with screen size height x width.
 * Play the game online if you are not familiar with the game.
 * The snake is initially positioned in the top-left corner (0, 0) with a length of 1 unit.
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat.
 * When a snake eats a piece of food, its length and the game's score both increase by 1.
 * Each piece of food appears one by one on the screen,
 * meaning the second piece of food will not appear until the snake eats the first piece of food.
 * When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving
 * (i.e. a snake of length 4 cannot run into itself).
 *
 * Implement the SnakeGame class:
 * SnakeGame(int width, int height, int[][] food)
 * Initializes the object with a screen of size height x width and the positions of the food.
 *
 * int move(String direction)
 * Returns the score of the game after applying one direction move by the snake.
 * If the game is over, return -1.
 *
 *
 * Example 1:
 * Input
 * ["SnakeGame", "move", "move", "move", "move", "move", "move"]
 * [[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
 * Output
 * [null, 0, 0, 1, 1, 2, -1]
 * Explanation
 * SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
 * snakeGame.move("R"); // return 0
 * snakeGame.move("D"); // return 0
 * snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
 * snakeGame.move("U"); // return 1
 * snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
 * snakeGame.move("U"); // return -1, game over because snake collides with border
 *
 * Constraints:
 * 1 <= width, height <= 10^4
 * 1 <= food.length <= 50
 * food[i].length == 2
 * 0 <= ri < height
 * 0 <= ci < width
 * direction.length == 1
 * direction is 'U', 'D', 'L', or 'R'.
 * At most 10^4 calls will be made to move.
 *
 * @author sniper
 * @date 26 Aug 2023
 * LC353, Medium, frequency=8
 */
public class DesignSnakeGame {


    /**
     * ----------------------------------
     * class SnakeGame:
     *     def __init__(self, width: int, height: int, food: List[List[int]]):
     *         self.m = height
     *         self.n = width
     *         self.food = food
     *         self.score = 0
     *         self.idx = 0
     *         self.q = deque([(0, 0)])
     *         self.vis = {(0, 0)}
     *
     *     def move(self, direction: str) -> int:
     *         i, j = self.q[0]
     *         x, y = i, j
     *         match direction:
     *             case "U":
     *                 x -= 1
     *             case "D":
     *                 x += 1
     *             case "L":
     *                 y -= 1
     *             case "R":
     *                 y += 1
     *         if x < 0 or x >= self.m or y < 0 or y >= self.n:
     *             return -1
     *         if (
     *             self.idx < len(self.food)
     *             and x == self.food[self.idx][0]
     *             and y == self.food[self.idx][1]
     *         ):
     *             self.score += 1
     *             self.idx += 1
     *         else:
     *             self.vis.remove(self.q.pop())
     *         if (x, y) in self.vis:
     *             return -1
     *         self.q.appendleft((x, y))
     *         self.vis.add((x, y))
     *         return self.score
     * ----------------------------------------
     * class SnakeGame {
     * public:
     *     SnakeGame(int width, int height, vector<vector<int>>& food) {
     *         m = height;
     *         n = width;
     *         this->food = food;
     *         score = 0;
     *         idx = 0;
     *         q.push_back(0);
     *         vis.insert(0);
     *     }
     *
     *     int move(string direction) {
     *         int p = q.front();
     *         int i = p / n, j = p % n;
     *         int x = i, y = j;
     *         if (direction == "U") {
     *             --x;
     *         } else if (direction == "D") {
     *             ++x;
     *         } else if (direction == "L") {
     *             --y;
     *         } else {
     *             ++y;
     *         }
     *         if (x < 0 || x >= m || y < 0 || y >= n) {
     *             return -1;
     *         }
     *         if (idx < food.size() && x == food[idx][0] && y == food[idx][1]) {
     *             ++score;
     *             ++idx;
     *         } else {
     *             int tail = q.back();
     *             q.pop_back();
     *             vis.erase(tail);
     *         }
     *         int cur = get_id(x, y);
     *         if (vis.count(cur)) {
     *             return -1;
     *         }
     *         q.push_front(cur);
     *         vis.insert(cur);
     *         return score;
     *     }
     *
     * private:
     *     int m;
     *     int n;
     *     vector<vector<int>> food;
     *     int score;
     *     int idx;
     *     deque<int> q;
     *     unordered_set<int> vis;
     *
     *     int get_id(int i, int j) {
     *         return i * n + j;
     *     }
     * };
     * ------------------------------------
     * type SnakeGame struct {
     * 	   m     int
     * 	   n     int
     * 	   food  [][]int
     * 	   score int
     * 	   idx   int
     * 	   q     []int
     * 	   vis   map[int]bool
     * }
     *
     * func Constructor(width int, height int, food [][]int) SnakeGame {
     * 	   return SnakeGame{height, width, food, 0, 0, []int{0}, map[int]bool{}}
     * }
     *
     * func (this *SnakeGame) Move(direction string) int {
     *     f := func(i, j int) int {
     *         return i*this.n + j
     *     }
     *     p := this.q[0]
     *     i, j := p/this.n, p%this.n
     *     x, y := i, j
     *     if direction == "U" {
     *         x--
     *     } else if direction == "D" {
     *     	   x++
     *     } else if direction == "L" {
     *     	   y--
     *     } else {
     *     	   y++
     *     }
     *     if x < 0 || x >= this.m || y < 0 || y >= this.n {
     *         return -1
     *     }
     *     if this.idx < len(this.food) && x == this.food[this.idx][0] && y == this.food[this.idx][1] {
     *         this.score++
     *     	   this.idx++
     *     } else {
     *         t := this.q[len(this.q)-1]
     *     	   this.q = this.q[:len(this.q)-1]
     *     	   this.vis[t] = false
     *     }
     *     cur := f(x, y)
     *     if this.vis[cur] {
     *         return -1
     *     }
     *     this.q = append([]int{cur}, this.q...)
     *     this.vis[cur] = true
     *     return this.score
     *
     */
    static class SnakeGame {
        private int m;
        private int n;
        private int[][] food;
        private int score;
        private int idx;
        /**
         *  snake's body
         */
        private Deque<Integer> queue = new ArrayDeque<>();
        private Set<Integer> visited = new HashSet<>();

        public SnakeGame(int width, int height, int[][] food) {
            m = height;
            n = width;
            this.food = food;
            queue.offer(0);
            visited.add(0);
        }

        public int move(String direction) {
            int p = queue.peekFirst();
            int i = p / n, j = p % n;
            int x = i, y = j;
            if ("U".equals(direction)) {
                --x;
            } else if ("D".equals(direction)) {
                ++x;
            } else if ("L".equals(direction)) {
                --y;
            } else {
                ++y;
            }
            if (x < 0 || x >= m || y < 0 || y >= n) {
                return -1;
            }
            if (idx < food.length && x == food[idx][0] && y == food[idx][1]) {
                ++score;
                ++idx;
            } else {
                int t = queue.pollLast();
                visited.remove(t);
            }
            int cur = getId(x, y);
            if (visited.contains(cur)) {
                return -1;
            }
            queue.offerFirst(cur);
            visited.add(cur);
            return score;
        }

        private int getId(int i, int j) {
            return i * n + j;
        }
    }
    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */

}
