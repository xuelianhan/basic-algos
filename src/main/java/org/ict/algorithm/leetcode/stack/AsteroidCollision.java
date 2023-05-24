package org.ict.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size,
 * and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions.
 * If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * Constraints:
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * @author sniper
 * @date 22 May 2023
 * LC735, Medium, frequency=19
 */
public class AsteroidCollision {


    /**
     * Understanding the following solution
     * FIFO-Queue solution
     * -------------------
     * class Solution:
     *     def asteroidCollision(self, asteroids: List[int]) -> List[int]:
     *         res = []
     *         for a in asteroids:
     *             if a > 0:
     *                 res.append(a)
     *             else:
     *                 while res and res[-1] > 0 and res[-1] < -a:
     *                     res.pop()
     *                 if res and res[-1] == -a:
     *                     res.pop()
     *                 elif not res or res[-1] < -a:
     *                     res.append(a)
     *         return res
     * ------------------------------------------------------
     * class Solution {
     * public:
     *     vector<int> asteroidCollision(vector<int>& asteroids) {
     *         vector<int> stack;
     *         for (int a : asteroids) {
     *             if (a > 0) {
     *                 stack.push_back(a);
     *             } else {
     *                 while (!stack.empty() && stack.back() > 0 and stack.back() < -a) {
     *                     stack.pop_back();
     *                 }
     *                 if (!stack.empty() && stack.back() == -a) {
     *                     stack.pop_back();
     *                 } else if (stack.empty() || stack.back() < 0) {
     *                     stack.push_back(a);
     *                 }
     *             }
     *         }
     *         return stack;
     *     }
     * };
     * -------------------------------------------------------
     * import scala.collection.mutable.Stack
     * object Solution {
     *     def asteroidCollision(asteroids: Array[Int]): Array[Int] = {
     *         var stack = Stack[Int]()
     *         for (a <- asteroids) {
     *             if (a > 0) {
     *                 stack.push(a)
     *             } else {
     *                 while (!stack.isEmpty && stack.top > 0 && stack.top < -a) {
     *                     stack.pop
     *                 }
     *                 if (!stack.isEmpty && stack.top == -a) {
     *                     stack.pop()
     *                 } else if (stack.isEmpty || stack.top < -a) {
     *                     stack.push(a)
     *                 }
     *             }
     *         }
     *         stack.reverse.toArray
     *     }
     * }
     * -----------------------------------------------------------
     * import scala.collection.mutable.Stack
     * object Solution {
     *     def asteroidCollision(asteroids: Array[Int]): Array[Int] = {
     *         var stack = Stack[Int]()
     *         for (a <- asteroids) {
     *             if (a > 0) {
     *                 stack.push(a)
     *             } else {
     *                 while (!stack.isEmpty && stack.top > 0 && stack.top < -a) {
     *                     stack.pop
     *                 }
     *                 if (!stack.isEmpty && stack.top == -a) {
     *                     stack.pop()
     *                 } else if (stack.isEmpty || stack.top < 0) {
     *                     stack.push(a)
     *                 }
     *             }
     *         }
     *         stack.reverse.toArray
     *     }
     * }
     * @param asteroids
     * @return
     */
    public int[] asteroidCollisionV1(int[] asteroids) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                queue.offerLast(a);
            } else {
                // Clear all the positives that less than -a.
                // One word like Chu Qing in Chinese, it means empty.
                while (!queue.isEmpty() && queue.peekLast() > 0 && queue.peekLast() < -a) {
                    queue.pollLast();
                }
                if (!queue.isEmpty() && queue.peekLast() > -a) {
                    //Destroy current element-a, so do nothing.
                } else if (!queue.isEmpty() && queue.peekLast() == -a) {
                    // Both explode
                    queue.pollLast();
                } else if (queue.isEmpty() || queue.peekLast() < -a) {
                    // [] or [-1], a:-2, so push a directly into the stack.
                    // e.g. [5,10,-5], expect result [5, 10]
                    // If the top element of the stack is less than -a
                    queue.offerLast(a);
                }
            }
        }
        return queue.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Understanding the following solution
     * FILO-Stack solution
     * --------------------
     * class Solution {
     * public:
     *     vector<int> asteroidCollision(vector<int>& asteroids) {
     *         vector<int> stack;
     *         for (int a : asteroids) {
     *             if (a > 0) {
     *                 stack.push_back(a);
     *             } else {
     *                 while (!stack.empty() && stack.back() > 0 and stack.back() < -a) {
     *                     stack.pop_back();
     *                 }
     *                 if (stack.empty() || stack.back() < 0) {
     *                     stack.push_back(a);
     *                 } else if (stack.back() == -a) {
     *                     stack.pop_back();
     *                 } else {
     *                     //do nothing.
     *                 }
     *             }
     *         }
     *         return stack;
     *     }
     * };
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                //[5,2], a:-6, so 5,2 is destroyed.
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
                //[] or [-1], a:-2, so push a directly into the stack.
                // Because while the top element of the stack is negative,
                // so the current negative can be pushed into the stack.
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                } else if (stack.peek() == -a) {
                    // Both explode
                    stack.pop();
                } else {
                    // stack.peek() > -a
                    // Destroy current element-a, so do nothing.
                }
            }
        }
        int n = stack.size();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
