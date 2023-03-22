package org.ict.algorithm.leetcode.greedy;

/**
 * You are given a 0-indexed integer array stones sorted in strictly increasing order representing the positions of stones in a river.
 * A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone.
 * However, it can jump to any stone at most once.
 * The length of a jump is the absolute difference between the position of the stone the frog is currently on,
 * and the position of the stone to which the frog jumps.
 * More formally, if the frog is at stones[i] and is jumping to stones[j],
 * the length of the jump is |stones[i] - stones[j]|.
 * The cost of a path is the maximum length of a jump among all jumps in the path.
 * Return the minimum cost of a path for the frog.
 *
 * Example 1:
 * Input: stones = [0,2,5,6,7]
 * Output: 5
 * Explanation: The above figure represents one of the optimal paths the frog can take.
 * The cost of this path is 5, which is the maximum length of a jump.
 * Since it is not possible to achieve a cost of less than 5, we return it.
 *
 * Example 2:
 * Input: stones = [0,3,9]
 * Output: 9
 * Explanation:
 * The frog can jump directly to the last stone and come back to the first stone.
 * In this case, the length of each jump will be 9. The cost for the path will be max(9, 9) = 9.
 * It can be shown that this is the minimum achievable cost.
 *
 * Constraints:
 * 2 <= stones.length <= 10^5
 * 0 <= stones[i] <= 10^9
 * stones[0] == 0
 * stones is sorted in a strictly increasing order.
 *
 * @author sniper
 * @date 22 Mar, 2023
 * LC2498, Medium
 */
public class FrogJumpII {



    /**
     * Intuition 1
     * "travel to the last stone and then return to the first stone."
     * It means there are two path from first to last stone,
     * and two path have no intersection in the middle.
     * We can image there are two frog jump from first to the last, there paths
     * cannot be cross(This way equals one frog jump from first to last, and back from last to first)
     *
     * Intuition 2
     * If one stone not in path-1 and path-2,
     * we can greedily add it to either of the path.
     * So it exists the best solution, that all stones are used.
     *
     *
     * Explanation
     * So we are going to assign stones to two frogs alternatively:
     * A[0] A[1] A[2] A[3] ...
     * start frog1 frog2 frog1 ...
     *
     * If we assign them not alternatively like:
     * frog1 frog2 frog2 frog1
     *
     * The distance for frog 1 is huge, and it's no better than
     * frog1 frog2 frog1 frog2
     *
     * So in this problem,
     * we need to calculate the maximum distance between A[i] - A[i-2].
     * Special case is only two stones, so we initial res = A[1] - A[0].
     *
     *
     * Complexity
     * Time O(n)
     * Space O(1)
     * @see <a href="https://leetcode.com/problems/frog-jump-ii/solutions/2897948/java-c-python-max-a-i-a-i-2"></a>
     * @author lee215
     * @param stones
     * @return
     */
    public int maxJumpV1(int[] stones) {
        int n = stones.length;
        int res = stones[1] - stones[0];
        for (int i = 2; i < n; i++) {
            res = Math.max(res, stones[i] - stones[i - 2]);
        }
        return res;
    }

    /**
     * Claim 1:
     * It is optimal that we make use of all rocks: There is never a benefit of not including a rock in Path 1 or Path 2,
     * it is always positive or neutral.
     *
     * Claim 2: It is never optimal to choose two consecutive rocks:
     * If we choose rock_i, and rock_i+1, then rock_i+2 will have to do (rock_i+2)-(rock_i) work to return.
     * Because the array is strictly increasing, it holds that (rock_i+2)-(rock_i) > (rock_i+1-rock_i)
     * Therefore one path must lie on each even index and the other path must lie on each odd index.
     * We iterate through them and find the maximum distance.
     *
     * @see <a href="https://leetcode.com/problems/frog-jump-ii/solutions/2897888/it-s-greedy-even-and-odd"></a>
     * @author t747
     * @param stones
     * @return
     */
    public int maxJump(int[] stones) {
        int res = stones[1] - stones[0];
        for (int i = 3; i < stones.length; i += 2) {
            res = Math.max(res, stones[i] - stones[i - 2]);
        }
        for (int i = 2; i < stones.length; i += 2) {
            res = Math.max(res, stones[i] - stones[i - 2]);
        }
        return res;
    }
}
