package org.ict.algorithm.leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Design a Snake game that is played on a device with screen size height x width.
 * Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned in the top-left corner (0, 0) with a length of 1 unit.
 *
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat.
 *
 * When a snake eats a piece of food, its length and the game's score both increase by 1.
 *
 * Each piece of food appears one by one on the screen,
 * meaning the second piece of food will not appear until the snake eats the first piece of food.
 *
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
     *     SnakeGame(int width, int height, vector<pair<int, int>> food) {
     *         this->width = width;
     *         this->height = height;
     *         this->food = food;
     *         score = 0;
     *         snake.push_back({0, 0});
     *     }
     *
     *     int move(string direction) {
     *         auto head = snake.front(), tail = snake.back();
     *         snake.pop_back();
     *         if (direction == "U") --head.first;
     *         else if (direction == "L") --head.second;
     *         else if (direction == "R") ++head.second;
     *         else if (direction == "D") ++head.first;
     *         if (count(snake.begin(), snake.end(), head) || head.first < 0 || head.first >= height || head.second < 0 || head.second >= width) {
     *             return -1;
     *         }
     *         snake.insert(snake.begin(), head);
     *         if (!food.empty() && head == food.front()) {
     *             food.erase(food.begin());
     *             snake.push_back(tail);
     *             ++score;
     *         }
     *         return score;
     *     }
     *
     * private:
     *     int width, height, score;
     *     vector<pair<int, int>> food, snake;
     * };
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
     * --------------------------------------
     */
    static class SnakeGameV1 {
        private int m;
        private int n;
        private int[][] food;
        private int score;
        /**
         * food's index
         */
        private int idx;
        /**
         *  snake's body
         */
        private Deque<Integer> queue = new ArrayDeque<>();
        private Set<Integer> visited = new HashSet<>();

        public SnakeGameV1(int width, int height, int[][] food) {
            m = height;
            n = width;
            this.food = food;
            /**
             * Start from (0, 0), index = i * n + j
             * index:0
             */
            queue.offer(0);
            visited.add(0);
        }

        /**
         * e.g. [[3,3,[[2,0],[0,0]]], [“D”],[“D”],[“U”]]
         * width:3, height:3
         * food:[[2,0],[0,0]]
         * move("D")
         * move("D")
         * move("U")
         *   0 1 2
         * 0 s
         * 1
         * 2 f
         * ---------------
         *   0 1 2
         * 0
         * 1 s
         * 2 f
         * ---------------
         * @param direction
         * @return
         */
        public int move(String direction) {
            int p = queue.peekFirst();
            /**
             * Convert 1D p index into 2D (i, j) coordinate
             */
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
            /**
             * Go out of bounds.
             */
            if (x < 0 || x >= m || y < 0 || y >= n) {
                return -1;
            }
            if (idx < food.length && x == food[idx][0] && y == food[idx][1]) {
                ++score;
                ++idx;
            } else {
                /**
                 * If the snake cannot eat the food, then moving its tail-ass forward.
                 * Move forward:
                 * Remove the tail with one space, then add cur index at the head.
                 * Notice here:
                 * The removal of the snake's tail comes before the detection of the collision with the snake's body.
                 * See the above e.g.case.
                 * You can imagine that the snake moves its tail first, then its head can go forward.
                 */
                int t = queue.pollLast();
                visited.remove(t);
            }
            int cur = getId(x, y);
            /**
             * If the head occupies a space that its body occupies(this means that it has been visited) after moving,
             * then return -1
             */
            if (visited.contains(cur)) {
                return -1;
            }
            /**
             * Add cur index at the head(Mock the moving of head)
             */
            queue.offerFirst(cur);
            visited.add(cur);
            return score;
        }

        /**
         * Convert 2D (i, j) coordinate into 1D index
         * @param i
         * @param j
         * @return
         */
        private int getId(int i, int j) {
            return i * n + j;
        }
    }


    /**
     * class SnakeGame {
     *  public:
     *    SnakeGame(int width, int height, vector<vector<int>>& food)
     *       : width(width), height(height), food(food) {
     *     visited.insert(getId(0, 0));
     *     body.push_back(getId(0, 0));
     *   }
     *
     *   int move(string direction) {
     *     // Old head's position
     *     int i = body.front() / width;
     *     int j = body.front() % width;
     *
     *     // Update head's position and check if out of bound
     *     if (direction == "U" && --i < 0)
     *       return -1;
     *     if (direction == "L" && --j < 0)
     *       return -1;
     *     if (direction == "R" && ++j == width)
     *       return -1;
     *     if (direction == "D" && ++i == height)
     *       return -1;
     *
     *     const int newHead = getId(i, j);
     *
     *     // Case 1: eat food and increase size by 1
     *     if (k < food.size() && i == food[k][0] && j == food[k][1]) {
     *       visited.insert(newHead);
     *       body.push_front(newHead);
     *       ++k;
     *       return ++score;
     *     }
     *
     *     // Case 2: new head != old tail and eat body!
     *     if (newHead != body.back() && visited.count(newHead))
     *       return -1;
     *
     *     // Case 3: normal case
     *     // Remove old tail first (important), then add new head
     *     // Because new head may be in old tail's position
     *     visited.erase(body.back());
     *     visited.insert(newHead);
     *     body.pop_back();
     *     body.push_front(newHead);
     *
     *     return score;
     *   }
     *
     *   private:
     *     int width;
     *     int height;
     *     int score = 0;
     *     int k = 0;  // food's index
     *     vector<vector<int>> food;
     *     unordered_set<int> visited;
     *     deque<int> body;  // snake's body
     *
     *     int getId(int i, int j) {
     *       return i * width + j;
     *     }
     * };
     */
    class SnakeGame {
        /**
         * Initialize your data structure here.
         *
         * @param width  - screen width
         * @param height - screen height
         * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
         *               first food is positioned at [1,1], the second is at [1,0].
         */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            visited.add(getId(0, 0));
            body.offerLast(getId(0, 0));
        }

        /**
         * Moves the snake.
         * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         * @return The game's score after the move. Return -1 if game over. Game over
         *         when snake crosses the screen boundary or bites its body.
         */
        public int move(String direction) {
            // Old head's position
            int p = body.peekFirst();
            int i = p / width;
            int j = p % width;

            // Update head's position and check if out of bound
            if (direction.equals("U") && --i < 0) {
                return -1;
            }
            if (direction.equals("L") && --j < 0) {
                return -1;
            }
            if (direction.equals("R") && ++j == width) {
                return -1;
            }
            if (direction.equals("D") && ++i == height) {
                return -1;
            }

            int newHead = getId(i, j);
            // Case 1: eat food and increase size by 1
            if (k < food.length && i == food[k][0] && j == food[k][1]) {
                visited.add(newHead);
                body.offerFirst(newHead);
                ++k;
                return ++score;
            }

            // Case 2: new head != old tail and eat body!
            if (newHead != body.peekLast() && visited.contains(newHead)) {
                return -1;
            }

            // Case 3: normal case
            // Remove old tail first (important), then add new head
            // Because new head may be in old tail's position
            visited.remove(body.peekLast());
            visited.add(newHead);
            body.pollLast();
            body.offerFirst(newHead);

            return score;
        }

        private int width;
        private int height;
        private int score = 0;
        // food's index
        private int k = 0;
        private int[][] food;
        private Set<Integer> visited = new HashSet<>();
        private Deque<Integer> body = new ArrayDeque<>(); // snake's body

        private int getId(int i, int j) {
            return i * width + j;
        }
    }
    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */

}
