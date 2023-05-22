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
     * @param asteroids
     * @return
     */
    public int[] asteroidCollisionV1(int[] asteroids) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                queue.offerLast(a);
            } else {
                while (!queue.isEmpty() && queue.peekLast() > 0 && queue.peekLast() < -a) {
                    queue.pollLast();
                }
                if (!queue.isEmpty() && queue.peekLast() == -a) {
                    queue.pollLast();
                } else if (queue.isEmpty() || queue.peekLast() < -a) {
                    queue.offerLast(a);
                }
            }
        }
        return queue.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Understanding the following solution
     * FILO-Stack solution
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
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
