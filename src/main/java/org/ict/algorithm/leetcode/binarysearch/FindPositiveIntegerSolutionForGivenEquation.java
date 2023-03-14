package org.ict.algorithm.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a callable function f(x, y) with a hidden formula and a value z,
 * reverse engineer the formula and return all positive integer pairs x and y where f(x,y) == z.
 * You may return the pairs in any order.
 *
 * While the exact formula is hidden, the function is monotonically increasing, i.e.:
 *
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 * The function interface is defined like this:
 *
 * interface CustomFunction {
 * public:
 *   // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
 *   int f(int x, int y);
 * };
 * We will judge your solution as follows:
 *
 * The judge has a list of 9 hidden implementations of CustomFunction,
 *
 * along with a way to generate an answer key of all valid pairs for a specific z.
 * The judge will receive two inputs: a function_id (to determine which implementation to test your code with), and the target z.
 * The judge will call your findSolution and compare your results with the answer key.
 * If your results match the answer key, your solution will be Accepted.
 *
 *
 * Example 1:
 *
 * Input: function_id = 1, z = 5
 * Output: [[1,4],[2,3],[3,2],[4,1]]
 * Explanation: The hidden formula for function_id = 1 is f(x, y) = x + y.
 * The following positive integer values of x and y make f(x, y) equal to 5:
 * x=1, y=4 -> f(1, 4) = 1 + 4 = 5.
 * x=2, y=3 -> f(2, 3) = 2 + 3 = 5.
 * x=3, y=2 -> f(3, 2) = 3 + 2 = 5.
 * x=4, y=1 -> f(4, 1) = 4 + 1 = 5.
 * Example 2:
 *
 * Input: function_id = 2, z = 5
 * Output: [[1,5],[5,1]]
 * Explanation: The hidden formula for function_id = 2 is f(x, y) = x * y.
 * The following positive integer values of x and y make f(x, y) equal to 5:
 * x=1, y=5 -> f(1, 5) = 1 * 5 = 5.
 * x=5, y=1 -> f(5, 1) = 5 * 1 = 5.
 *
 *
 * Constraints:
 *
 * 1 <= function_id <= 9
 * 1 <= z <= 100
 * It is guaranteed that the solutions of f(x, y) == z will be in the range 1 <= x, y <= 1000.
 * It is also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000.
 * @author sniper
 * @date 14 Mar, 2023
 * LC1237, Medium
 */
public class FindPositiveIntegerSolutionForGivenEquation {


    /**
     * Intuition
     * Rephrase the problem:
     * Given a matrix, each row and each column is increasing.
     * Find all coordinates (i,j) that A[i][j] == z
     *
     * Complexity
     * In binary search,
     * time complexity is O(XlogY) or O(YlogX)
     *
     * In this solution,
     * time complexity is stable O(X + Y).
     *
     * Bianry search is really an unnecessay operation,
     * and it won't help improve the conplexity at all.
     *
     * Space is O(X)
     * @see <a href="https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/solutions/414249/java-c-python-o-x-y/?orderBy=most_votes"></a>
     * @author lee215
     * @param customfunction
     * @param z
     * @return
     */
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        /**
         *  1 <= x, y <= 1000
         */
        int x = 1;
        int y = 1000;
        while (x <= 1000 && y >= 1) {
            int v = customfunction.f(x, y);
            if (v > z) {
                y--;
            } else if (v < z) {
                x++;
            } else {
                res.add(Arrays.asList(x++, y--));
            }
        }
        return res;
    }

    interface CustomFunction {

        // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
        int f(int x, int y);
    }
}
