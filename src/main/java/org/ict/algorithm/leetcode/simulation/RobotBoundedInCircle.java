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
     * G: (0, 0) -> (0, 1)
     * L: (0, 1) -> (-1, 0)
     *
     * @author lee215
     * @param instructions
     * @return
     */
    public boolean isRobotBoundedV1(String instructions) {
        int x = 0;
        int y = 0;
        int idx = 0;
        /**
         * two-by-two orthogonal
         * [-1, 0] * [0, 1] = 0
         * [0, 1] * [1, 0] = 0
         * [1, 0] * [0, -1] = 0
         */
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
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
     * @param instructions
     * @return
     */
    public boolean isRobotBounded(String instructions) {
        /**
         * DIRECTION_ENUM
         */
        int idx = 0;
        int[] cur = new int[] {0, 0};
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
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

}
