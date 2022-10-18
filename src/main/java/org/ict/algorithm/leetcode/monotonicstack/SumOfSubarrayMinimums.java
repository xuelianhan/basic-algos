package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers arr,
 * find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
 * Since the answer may be large,
 * return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 *
 * The number 1e9 + 71e9+7 fits nicely into a signed 32-bit integer.
 * It is also the first 10-digit prime number.
 * In some problems we need to compute the Modular Multiplicative Inverse and it helps very much that this number is prime.
 *
 * In fact any prime number less then 2^{30} will be fine in order to prevent possible overflows.
 * But this one can be easily written as 1e9 + 71e9+7.
 * This reasoning almost uniquely determined this number.
 *
 * @author sniper
 * @date 27 Sep, 2022
 * LC907
 */
public class SumOfSubarrayMinimums {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        sumSubarrayMinsV3(arr);
    }

    /**
     * Cost 43ms
     * Same as sumSubarrayMinsV3 but add a sentinel node zero at the end of the array copy,
     * and remove the condition i == arr.length in sumSubarrayMinsV3.
     * If we add another zero node at the beginning of the array copy,
     * then we can simplify the left statement to this:
     * int left = mid - stack.peek();
     * So the solution is same as sumSubarrayMinsV1 and sumSubarrayMinsV2
     * 
     * @param arr
     * @return
     */
    public static int sumSubarrayMinsV4(int[] arr) {
        long res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] copy = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        for (int i = 0; i < copy.length; i++) {
            /**
             * Notice here i == arr.length must put before condition of arr[peek] > arr[i].
             */
            while (!stack.isEmpty() && copy[stack.peek()] > copy[i]) {
                int mid = stack.pop();
                int left = mid - (stack.isEmpty() ? -1 : stack.peek());
                int right = i - mid;
                res += (long)copy[mid] * left * right;
            }
            stack.push(i);
        }
        /**
         * In Java SE 7 and later, any number of underscore characters (_) can appear anywhere between digits in a numerical literal.
         * This feature enables you,
         * for example,
         * to separate groups of digits in numeric literals, which can improve the readability of your code.
         * <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html">Underscores in Numeric Literals</a>
         */
        return (int)(res % 1_000_000_007);
    }

    /**
     * Solution provided by <a href="https://leetcode.com/shk10/">Shahid Hussain Khan</a>
     *
     * e.g. given [3,1,2,4], expected 17
     * 0 1 2 3 4
     * 3 1 2 4
     *
     * i:0, stack is empty, push 0 into the stack, stack:0
     * i:1, stack:0, arr[peek] > arr[1], pop 0 from the stack, stack:empty
     *      mid = 0, left = mid - (-1) = 1, right = 1 - 0 = 1, res = 0 + arr[0]*1*1 = 3
     *      push 1 into the stack, stack:1
     * i:2, stack:1, arr[peek] < arr[2], push 2 into the stack, stack:1,2
     * i:3, stack:1,2, arr[peek] < arr[3], push 3 into the stack, stack:1,2,3
     * i:4, 4==arr.length,
     *      pop 3 from the stack, stack:1,2
     *      mid = 3, left = mid - peek = 3 - 2 = 1, right = i - mid = 4 - 3 = 1, res = 3 + arr[3]*1*1 = 3 + 4*1 = 7
     *
     *      pop 2 from the stack, stack:1
     *      mid = 2, left = mid - peek = 2 - 1 = 1, right = i - mid = 4 - 2 = 2, res = 7 + arr[2]*1*2 = 7 + 2*2 = 11
     *
     *      pop 1 from the stack, stack:empty
     *      mid = 1, left = mid - empty = 1 - (-1) = 2, right = i - mid = 4 - 1 = 3, res = 11 + arr[1]*2*3 = 11 + 6 = 17
     * the stack is empty, while-loop-end, push 4 into the stack, stack:4
     * for-loop-end.
     *
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMinsV3(int[] arr) {
        long res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= arr.length; i++) {
            /**
             * Notice here i == arr.length must put before condition of arr[peek] > arr[i].
             */
            while (!stack.isEmpty() && (i == arr.length || arr[stack.peek()] > arr[i])) {
                int mid = stack.pop();
                int left = mid - (stack.isEmpty() ? -1 : stack.peek());
                int right = i - mid;
                res += (long)arr[mid] * left * right;
            }
            stack.push(i);
        }
        /**
         * In Java SE 7 and later, any number of underscore characters (_) can appear anywhere between digits in a numerical literal.
         * This feature enables you,
         * for example,
         * to separate groups of digits in numeric literals, which can improve the readability of your code.
         * <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html">Underscores in Numeric Literals</a>
         */
        return (int)(res % 1_000_000_007);
    }

    /**
     * Same as sumSubarrayMinsV1.
     * Adding two sentinels node zero at the beginning and the tail of the array.
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMinsV2(int[] arr) {
        int[] copy = new int[arr.length + 2];
        for (int i = 1; i <= arr.length; i++) {
            copy[i] = arr[i-1];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        long res = 0;
        int mod = (int)1e9 + 7;
        for (int i = 0; i < copy.length; i++) {
            while (!stack.isEmpty() && copy[stack.peek()] > copy[i]) {
                int cur = stack.pop();
                int left = cur - stack.peek();
                int right = i - cur;
                /**
                 * Notice the conversion to long here is very important.
                 */
                res += (long)copy[cur] * left * right;
            }
            stack.push(i);
        }
        return (int)(res % mod);
    }

    /**
     *
     * Solution provided by <a href="https://leetcode.com/cindyzhou/">cindyzhou</a>
     * Cost 22ms, adding two sentinels node zero at the beginning and the tail of the array
     *
     * float(“inf”) represents a positive infinite number.
     * So by appending -inf and -inf at the head and tail,
     * you can compute the number of consecutive number > arr[i] from right and left of i with one stack.
     *
     * I use a monotonous non-decreasing stack to store the left boundary and right boundary where a number is the minimal number in the sub-array
     *
     * The boundary is the maximum contiguous array where an element would be minimum.
     * so for 1 it is | 3 1 2 4|
     *
     * e.g. given [3,1,2,4],
     * For 3, if 3 is the minimum number, then the boundary is: | 3 | ...
     * For 1, if 1 is the minimum number, then the boundary is: | 3 1 2 4 |
     * For 2, if 2 is the minimum number, then the boundary is: ... | 2 4 |
     * For 4, if 4 is the minimum number, then the boundary is: ... | 4 |
     *
     * The times a number n occurs in the minimums is |left_boundary-indexOf(n)| * |right_boundary-indexOf(n)|
     *
     * The total sum is sum([n * |left_boundary - indexOf(n)| * |right_boundary - indexOf(n)| for n in the array])
     *
     * After a number n pops out from an increasing stack, the current stack top is n's left_boundary,
     * the number forcing n to pop is n's right_boundary.
     *
     * A tricky technique here is to add a MIN_VALUE at the head and end of the original array.
     *
     * e.g. given [3,1,2,4], expected 17
     * 0 1 2 3 4 5
     * 0 3 1 2 4 0
     * i: 0, stack is empty, push 0 into the stack
     * i: 1, stack:0, arr[0] < arr[1], push 1 into the stack, stack:0,1
     * i: 2, stack:0,1, arr[1] > arr[2], pop 1 from the stack, cur:1, res = arr[1] * (2 - 1) * (1 - 0) = 3
     *       push 2 into the stack, stack:0,2
     * i: 3, stack:0,2, arr[2] < arr[3], push 3 into the stack, stack:0,2,3
     * i: 4, stack:0,2,3, arr[3] < arr[4], push 4 into the stack, stack:0,2,3,4
     * i: 5, stack:0,2,3,4,
     *       arr[4] > arr[5], pop 4 from the stack, cur:4, stack:0,2,3, res = 3 + arr[4]*(5-4)*(4-3) = 3 + 4 = 7
     *       arr[3] > arr[5], pop 3 from the stack, cur:3, stack:0,2, res = 7 + arr[3]*(5-3)*(3-2) = 7 + 2*2 = 11
     *       arr[2] > arr[5], pop 2 from the stack, cur:2, stack:0, res = 11 + arr[2]*(5-2)((2-0) = 11 + 1*3*2 = 17
     *
     * <code>
     *      def sumSubarrayMins(self, arr: List[int]) -> int:
     *         res = 0
     *         stack = []  #  non-decreasing
     *         arr = [float('-inf')] + arr + [float('-inf')]
     *         for i, n in enumerate(arr):
     *             while stack and arr[stack[-1]] > n:
     *                 cur = stack.pop()
     *                 res += arr[cur] * (i - cur) * (cur - stack[-1])
     *                 #print ("i:", i, ",cur:", cur, ",stack top:", stack[-1], ",res:", res)
     *             stack.append(i)
     *         return res % (10**9 + 7)
     * </code>
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMinsV1(int[] arr) {
        int[] copy = new int[arr.length + 2];
        for (int i = 1; i <= arr.length; i++) {
            copy[i] = arr[i-1];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        long res = 0;
        int mod = (int)1e9 + 7;
        for (int i = 0; i < copy.length; i++) {
            while (!stack.isEmpty() && copy[stack.peek()] > copy[i]) {
                int cur = stack.pop();
                /**
                 * Notice the conversion to long here is very important.
                 */
                res += (long)copy[cur] * (i - cur) * (cur - stack.peek());
            }
            stack.push(i);
        }
        return (int)(res % mod);
    }

    /**
     * Solution provided by <a href="https://leetcode.com/wangzi6147/">Zane Wang</a>
     *
     * stack: An increasing stack, store the index
     * dp[i + 1]: Sum of minimum of sub-arrays which end with A[i]
     * dp[i + 1] = dp[prev + 1] + (i - prev) * A[i], where prev is the last number which is less than A[i],
     * since we maintain a monotonous increasing stack, prev = stack.peek()
     *
     * eg. [3, 1, 2, 4, 3]:
     * 0 1 2 3 4
     * 3 1 2 4 3
     *
     * i = 0, all contiguous sub-arrays end with A[0] = 3:
     * [3]
     * stack:-1, dp[0 + 1] = dp[0] + (0- (-1))*A[0] = 0 + 1 * 3 = 3
     * push 0 into the stack, stack: -1, 0
     * res: 0 + dp[1] = 3
     *
     * i = 1, all contiguous sub-arrays end with A[1] = 1:
     * [3, 1], [1]
     * stack: -1, 0, stack peek is 0, arr[i] = arr[1] < arr[0], pop 0 from the stack, stack: -1
     * dp[1 + 1] = dp[-1 + 1] + (1 - (-1)) * arr[1] = dp[0] + 2 * arr[1] = 0 + 2 * 1 = 1 + 1 = 2
     * push 1 into the stack, stack: -1, 1
     * res = 3 + dp[2] = 3 + 2 = 5
     *
     * i = 2, all contiguous sub-arrays end with A[2] = 2:
     * [3, 1, 2], [1, 2], [2]
     * stack: -1, 1, arr[2] > arr[stack.peek()] --> arr[2] > arr[1]
     * dp[2 + 1] = dp[1 + 1] + (2 - 1) * arr[2] = dp[2] + arr[2] = 2 + 2 = 1 + 1 + 2 = 4
     * push 2 into the stack, stack: -1, 1, 2
     * res = 5 + 4 = 9
     *
     * i = 3, all contiguous sub-arrays end with A[3] = 4:
     * [3, 1, 2, 4], [1, 2, 4], [2, 4], [4]
     * stack: -1, 1, 2, arr[3] > arr[stack.peek()] --> arr[3] > arr[2]
     * dp[4] = dp[2 + 1] + (3 - 2) * arr[3] = 4 + 1 * 4 = 1 + 1 + 2 + 4 = 8
     * push 3 into the stack, stack: -1, 1, 2, 3
     * res = 9 + 8 = 17
     *
     * i = 4, all contiguous sub-arrays end with A[4] = 3:
     * [3, 1, 2, 4, 3], [1, 2, 4, 3], [2, 4, 3], [4, 3], [3]
     * stack: -1, 1, 2, 3, arr[4] < arr[stack.peek()] --> arr[4] < arr[3], pop 3 from the stack, stack: -1, 1, 2
     * dp[5] = dp[2 + 1] + (4 - 2) * arr[4] = 4 + 2 * 3 = 1 + 1 + 2 + 3 + 3 = 10
     * push 4 into the stack, stack: -1, 1, 2, 4
     * res = 17 + 10 = 27
     * for-loop end
     * return res = 27
     * (
     *   In this case, stack.peek() = 2, A[4] = 3.
     *   For the last 2 sub-arrays [3] and [4, 3], sum of minimum = (i - stack.peek()) * A[i] = (4 - 2)*3 = 6,
     *   For the first 3 sub-arrays [2, 4, 3], [1, 2, 4, 3] and [3, 1, 2, 4, 3],
     *   since they all contain a 2 which is less than 3, sum of minimum = dp[stack.peek() + 1] = 4.
     *   Then, dp[4 + 1] = 4 + 6 = 10
     * )
     *
     * We see in the whole
     * e.g.[3, 1, 2, 4, 3]:
     *
     * 0: [3]
     * 1: [3, 1], [1]
     * 2: [3, 1, 2], [1, 2], [2]
     * 3: [3, 1, 2, 4], [1, 2, 4], [2, 4], [4]
     * 4: [3, 1, 2, 4, 3], [1, 2, 4, 3], [2, 4, 3], [4, 3], [3]
     *
     * 0: sum(min) = 3
     * 1: sum(min) = 1 + 1 = 2
     * 2: sum(min) = 1 + 1 + 2 = 4
     * 3: sum(min) = 1 + 1 + 2 + 4 = 8
     * 4: sum(min) = 1 + 1 + 2 + 3 + 3 = 10
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[arr.length + 1];
        /**
         *
         */
        stack.push(-1);
        int res = 0;
        /**
         * 1e9 + 7 = 10^9 + 7, to prevent res overflow, so modulo (1e9 + 7)
         * @see <a href="https://wingkwong.github.io/leetcode-the-hard-way/tutorials/basic-topics/mod"></a>
         */
        int mod = (int)1e9 + 7;
        for (int i = 0; i < arr.length; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            dp[i + 1] = (dp[stack.peek() + 1] + (i - stack.peek()) * arr[i]) % mod;
            stack.push(i);
            res += dp[i + 1];
            res %= mod;
            //System.out.println("i:" + i + ", dp[" + (i + 1) + "]:" + dp[i+1] + ", res:" + res);
        }
        return res;
    }
}
