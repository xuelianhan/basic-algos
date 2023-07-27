package org.ict.algorithm.leetcode.simulation;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:
 *
 * The north direction is the positive direction of the y-axis.
 * The south direction is the negative direction of the y-axis.
 * The east direction is the positive direction of the x-axis.
 * The west direction is the negative direction of the x-axis.
 * The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 * "R": turn 90 degrees to the right (i.e., clockwise direction).
 *
 * The robot performs the instructions given in order,
 * and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Example 1:
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
 * "G": move one step. Position: (0, 1). Direction: South.
 * "G": move one step. Position: (0, 0). Direction: South.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
 * Based on that, we return true.
 *
 * Example 2:
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
 * Based on that, we return false.
 *
 * Example 3:
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
 * "G": move one step. Position: (-1, 1). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
 * "G": move one step. Position: (-1, 0). Direction: South.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
 * "G": move one step. Position: (0, 0). Direction: East.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
 * Based on that, we return true.
 *
 *
 * Constraints:
 * 1 <= instructions.length <= 100
 * instructions[i] is 'G', 'L' or, 'R'.
 *
 * @author sniper
 * @date 25 Jul 2023
 * LC1041, Medium
 */
public class RobotBoundedInCircle {


    /**
     *
     * object Solution {
     *     def isRobotBounded(instructions: String): Boolean = {
     *         var x = 0
     *         var y = 0
     *         var i = 0
     *         val d = Array(Array(0, 1), Array(1, 0), Array(0, -1), Array(-1, 0))
     *         for (j <- 0 until instructions.length) {
     *             var c = instructions.charAt(j)
     *             if (c == 'R') {
     *                 i = (i + 1) % 4 }
     *             else if (c == 'L') {
     *                 i = (i + 3) % 4
     *             } else {
     *                 x += d(i)(0)
     *                 y += d(i)(1)
     *             }
     *         }
     *         x == 0 && y == 0 || i > 0
     *     }
     * }
     * -------------------------------------------
     * class Solution {
     * public:
     *     bool isRobotBounded(string instructions) {
     *         int x = 0, y = 0, idx = 0;
     *         vector<vector<int>> dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
     *         for (char& c : instructions) {
     *             if ('R' == c) {
     *                 idx = (idx + 1) % 4;
     *             } else if ('L' == c) {
     *                 idx = (idx + 4 - 1) % 4;
     *             } else {
     *                 x += dirs[idx][0];
     *                 y += dirs[idx][1];
     *             }
     *         }
     *         return x == 0 && y == 0 || idx > 0;
     *     }
     * };
     * --------------------------------------------------------
     * class Solution:
     *     def isRobotBounded(self, instructions: str) -> bool:
     *         x, y, dx, dy = 0, 0, 0, 1
     *         for c in instructions:
     *             if 'R' == c: dx, dy = dy, -dx
     *             if 'L' == c: dx, dy = -dy, dx
     *             if 'G' == c: x, y = x + dx, y + dy
     *         return (x, y) == (0, 0) or (dx, dy) != (0, 1)
     * --------------------------------------------------------
     * e.g.  "GL"
     * G: (0, 0) -> (0, 1), face the North, the y incremented by 1 unit, the x doesn't change
     * L: (0, 1) -> (0, 1), face the West, doesn't change the position
     * G: (0, 1) -> (0-1, 1) -> (-1, 1), face the West, the x go forward one step
     * L: (-1, 1) -> (-1, 1), face the South,
     * G: (-1, 1) -> (-1, 1-1)->(-1, 0), face the South, the y go forward on step.
     * L: (-1, 0) -> (-1, 0), face the East
     * G: (-1, 0) -> (-1+1, 0)->(0, 0), face the East, return to the original
     * ----------------------------
     * e.g. "GG"
     * G: (0, 0) -> (0, 1), face the North
     * G: (0, 1) -> (0, 2), face the North
     *
     * For a right turn, we can think of the directions are: north -> east -> south -> west -> north ->....
     * Making a right turn is equivalent to moving the current direction to its next.
     * So the new direction will be dirs[(i + 1) % 4].
     * For a left turn, it can be treated as moving the current direction to its previous one.
     * So the new direction will be dirs[(i-1) % 4], since we need to ensure index >= 0,
     * so we change dirs[(i-1) % 4] to dirs[(i - 1 + 4) % 4], which is dirs[(i + 3) % 4]
     * The second challenge is how can we determine the robot can be bounded in a circle.
     * There are two cases where the robot will be bounded in a circle.
     *
     * case 1: Robot is at the original position (0,0) after finishing the instruction.
     * case 1: Robot is not at the original position (0,0) && not facing towards north after finishing the instruction.
     * In case 1, the robot will go back the original position every time when the instruction is executed.
     * In case 2, we can think of the path that the robot moves as below.
     * So after executing the instruction at most 4 times,
     * the robot will go back to the original position.
     *
     *
     * @author lee215
     * @param instructions
     * @return
     */
    public boolean isRobotBoundedV2(String instructions) {
        /**
         * x and y represent the robot's current position,
         * and idx represents the robot's current direction.
         */
        int x = 0;
        int y = 0;
        int idx = 0;
        /**
         * two-by-two orthogonal
         * [-1, 0] * [0, 1] = 0
         * [0, 1] * [1, 0] = 0
         * [1, 0] * [0, -1] = 0
         * [0, -1] * [-1, 0] = 0
         * We first define a 2-dementional array dirs:{{0,1}, {1,0}, {0,-1}, {-1,0}}
         * to represent north, east, south and west in order, and also treat dirs as a cyclic array.
         * (0, 1)
         * (1, 0)
         * (0, -1)
         * (-1, 0)
         */
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (char c : instructions.toCharArray()) {
            if ('R' == c) {
                idx = (idx + 1) % 4;
            } else if ('L' == c) {
                idx = (idx + 4 - 1) % 4;
            } else {
                x += dirs[idx][0];
                y += dirs[idx][1];
            }
        }
        return x == 0 && y == 0 || idx > 0;
    }

    /**
     * If the robot deviates from the starting position,
     * just look at the direction of the robot at this time,
     * as long as it is not to the north, then it will eventually return to the starting point,
     * you can bring a few more examples to try.
     * Knowing the relationship between the final state and the loop path,
     * now is how to execute these instructions.
     * It is not difficult to use a variable to indicate the current direction,
     * 0 for the north,
     * 1 for the east,
     * 2 for the south,
     * 3 for the west,
     * in this order to write an array of offsets dirs,
     * that is, in the labyrinth traversal of the time often used in that array.
     * Then record the current position cur, initialized to (0, 0),
     * then you can execute the instruction,
     * if you encounter G instruction, according to the idx from the dirs array of offsets added to cur.
     * If you encounter an L instruction, the idx is decremented by 1.
     * In order to avoid negative numbers, you add a 4, then decrement it by 1, and then take the remainder of the 4.
     * Similarly, if an R instruction is encountered, idx is added by 1 and then remaindered by 4.
     * Finally, if you are still at the origin, or if the direction is not north, return true.
     *
     * If anyone has trouble understanding the dx, dy transform:
     * Right is 90° and Left is -90°
     *            ^
     *          dy|
     *            |
     *            | here
     * -------------------------->dx
     *            |
     *            |
     *            |
     *
     * (transform 90 degrees)
     *
     *            ^
     *            |
     *            |
     *            |
     * -------------------------->dy
     *            | here
     *            |
     *            |
     *          -dx
     *------------------------------
     * Let's say the robot starts with facing up. It moves [dx, dy] by executing the instructions once.
     * If the robot starts with facing
     * right, it moves [dy, -dx];
     * left, it moves [-dy, dx];
     * down, it moves [-dx, -dy]
     *
     * If the robot faces right (clockwise 90 degree) after executing the instructions once,
     * the direction sequence of executing the instructions repeatedly will be up -> right -> down -> left -> up
     * The resulting move is [dx, dy] + [dy, -dx] + [-dx, -dy] + [-dy, dx] = [0, 0] (back to the original starting point)
     *
     * All other possible direction sequences:
     * up -> left -> down -> right -> up. The resulting move is [dx, dy] + [-dy, dx] + [-dx, -dy] + [dy, -dx] = [0, 0]
     * up -> down -> up. The resulting move is [dx, dy] + [-dx, -dy] = [0, 0]
     * up -> up. The resulting move is [dx, dy]
     *
     *
     * @param instructions
     * @return
     */
    public boolean isRobotBoundedV1(String instructions) {
        /**
         * DIRECTION_ENUM
         */
        int idx = 0;
        int[] cur = new int[] {0, 0};
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (char c : instructions.toCharArray()) {
            if ('G' == c) {
                int x = cur[0] + dirs[idx][0];
                int y = cur[1] + dirs[idx][1];
                cur[0] = x;
                cur[1] = y;
            } else if ('L' == c) {
                idx = (idx + 4 - 1) % 4;
            } else if ('R' == c) {
                idx = (idx + 1) % 4;
            }
        }
        return (cur[0] == 0 && cur[1] == 0) || idx > DIRECTION_ENUM.north.code;
    }

    public enum DIRECTION_ENUM {
        north(0),
        east(1),
        south(2),
        west(3);

        DIRECTION_ENUM(int code) {
            this.code = code;
        }
        private int code;

    }

    /**
     * Understanding the following solution
     *          N(0)
     *          |
     *          |
     * W(3)-------------E(1)
     *          |
     *          |
     *          S(2)
     * @param instructions
     * @return
     */
    public boolean isRobotBounded(String instructions) {
        if (null == instructions || instructions.length() == 0) {
            return false;
        }
        int x = 0;
        int y = 0;
        char direction = 'N';
        for (char c: instructions.toCharArray()) {
            if ('G' == c) {
                if ('N' == direction) {
                    y += 1;
                } else if ('S' == direction) {
                    y -= 1;
                } else if ('E' == direction) {
                    x += 1;
                } else if ('W' == direction) {
                    x -= 1;
                }
            } else if ('L' == c) {
                if ('N' == direction) {
                    direction = 'W';
                } else if ('W' == direction) {
                    direction = 'S';
                } else if ('S' == direction) {
                    direction = 'E';
                } else if ('E' == direction) {
                    direction = 'N';
                }
            } else if ('R' == c) {
                if ('N' == direction) {
                    direction = 'E';
                } else if ('E' == direction) {
                    direction = 'S';
                } else if ('S' == direction) {
                    direction = 'W';
                } else if ('W' == direction) {
                    direction = 'N';
                }
            }
        }
        if (x == 0 && y == 0) {
            return true;
        }
        if (direction == 'N') {
            return false;
        }
        return true;
    }



}
