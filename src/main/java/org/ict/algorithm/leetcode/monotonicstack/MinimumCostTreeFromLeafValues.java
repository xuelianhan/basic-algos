package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 *
 * A node is a leaf if and only if it has zero children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation: There are two possible trees shown.
 * The first has a non-leaf node sum 36, and the second has non-leaf node sum 32.
 * Example 2:
 *
 *
 * Input: arr = [4,11]
 * Output: 44
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (i.e., it is less than 2^31).
 * @author sniper
 * @date 18 Oct, 2022
 * LC1130
 */
public class MinimumCostTreeFromLeafValues {


    /**
     * Get AK in every contest and send your resume to leecode@google.com
     * Solution provided by <a href="https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space">lee215</a>.
     *
     * DP Solution
     * Find the cost for the interval [i,j].
     * To build up the interval [i,j],
     * we need to split it into left subtree and sub tree,
     * dp[i, j] = dp[i, k] + dp[k + 1, j] + max(A[i, k]) * max(A[k + 1, j])
     *
     * If you don't understand dp solution,
     * I won't explain it more and you won't find the answer here.
     * Take your time,
     * read any other solutions,
     * and come back at your own will.
     *
     * If you got it, continue to read.
     *
     * DP Complexity
     * Second question after this dp solution,
     * what's the complexity?
     * N^2 states and O(N) to find each.
     * So this solution is O(N^3) time and O(N^2) space.
     *
     * You thought it's fine.
     * After several nested for loop, you got a happy green accepted.
     * You smiled and released a sigh as a winner.
     *
     * What a great practice for DP skill!
     * Then you noticed it's medium.
     * That's it, just a standard medium problem of dp.
     * Nothing can stop you. Even dp problem.
     *
     *
     * True story.
     * So you didn't Read and Upvote this post.
     * (upvote is a good mark of having read)
     * One day, you meet exactly the same solution during an interview.
     * Your heart welled over with joy,
     * and you bring up your solution with confidence.
     *
     * One week later, you receive an email.
     * The second paragraph starts with a key word "Unfortunately".
     *
     * What the heck!?
     * You solved the interview problem perfectly,
     * but the company didn't appreciate your talent.
     * What's more on earth did they want?
     * WHY?
     *
     *
     * Why
     * Here is the reason.
     * This is not a dp problem at all.
     *
     * Because dp solution test all ways to build up the tree,
     * including many unnecessay tries.
     * Honestly speaking, it's kinda of brute force.
     * Yes, brute force testing, with memorization.
     *
     *
     * Intuition
     * Let's review the problem again.
     *
     * When we build a node in the tree, we compared the two numbers a and b.
     * In this process,
     * the smaller one is removed and we won't use it anymore,
     * and the bigger one actually stays.
     * For example, the given array is [6, 2, 4]
     * When you compare 2 and 4 to build nodes for the tree,
     * after calculating the cost (2*4),
     * you only need to care about 4, and 2 is dropped.
     * Because the value of next up-level node is max of left subtree * max of right subtree.
     *
     * The problem can translated as following:
     * Given an array A, choose two neighbors in the array a and b,
     * we can remove the smaller one min(a,b) and the cost is a * b.
     * What is the minimum cost to remove the whole array until only one left?
     *
     * To remove a number a, it needs a cost a * b, where b >= a.
     * So a has to be removed by a bigger number.
     * We want minimize this cost, so we need to minimize b.
     *
     * b has two candidates, the first bigger number on the left,
     * the first bigger number on the right.
     *
     * The cost to remove a is a * min(left, right).
     *
     * Solution 1
     * With the intuition above in mind,
     * the explanation is short to go.
     * We remove the element form the smallest to bigger.
     * We check the min(left, right),
     * For each element a, cost = min(left, right) * a
     * Time O(N^2)
     * Space O(N)
     *
     * Solution 2: Stack Soluton
     * we decompose a hard problem into reasonable easy one:
     * Just find the next greater element in the array, on the left and one right.
     * Refer to the problem 503. Next Greater Element II.
     * Time O(N) for one pass
     * Space O(N) for stack in the worst cases
     *
     * e.g. arr=[6, 2, 4]
     *      24
     *      /|
     *     6 8
     *      /\
     *     2 4
     * i:0, push 6 into the stack, stack:Max,6
     * i:1, push 2 into the stack, stack:Max,6,2
     * i:2, peek=2, peek <= 4, pop 2 from the stack, top:2, res= 0 + top*min(6, 4)=2*4=8
     *      push 4 into the stack, stack:Max,6,4
     *
     * stack:Max,6,4, stack size is 3
     * res = 8 + 6*4 = 8 + 24 = 32
     *
     *
     * e.g. arr=[6, 7, 8]
     *    56
     *   /\
     *  42 8
     *  /\
     * 6 7
     * i:0, push 6 into the stack, stack:Max,6
     * i:1, peek=6, peek<=7, pop 6 from the stack, top=6, res=0 + 6*min(Max,7)=42, push 7 into the stack, stack:Max,7
     * i:2, peek=7, peek<=8, pop 7 from the stack, top=7, res=42 +7*min(Max, 8)=98, push 8 into the stack, stack:Max,8
     * stack size is 2
     * return res=98
     *
     *
     * @author lee215
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.MAX_VALUE);
        for (int num : arr) {
            /**
             * Why use peek <= num, not peek < num?
             * Consider a case: arr = [2, 2, 2]
             *     4
             *    /|
             *   4 2
             *  /\
             * 2 2
             */
            while (!stack.isEmpty() && stack.peek() <= num) {
                int top = stack.pop();
                res += top * Math.min(stack.peek(), num);
            }
            stack.push(num);
        }

        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}
