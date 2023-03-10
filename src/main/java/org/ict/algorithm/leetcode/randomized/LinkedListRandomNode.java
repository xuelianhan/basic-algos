package org.ict.algorithm.leetcode.randomized;

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 *
 * Implement the Solution class:
 *
 * Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
 * int getRandom() Chooses a node randomly from the list and returns its value.
 * All the nodes of the list should be equally likely to be chosen.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // return 1
 * solution.getRandom(); // return 3
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 3
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 *
 *
 * Constraints:
 *
 * The number of nodes in the linked list will be in the range [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * At most 10^4 calls will be made to getRandom.
 *
 *
 * Follow up:
 *
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 * @author sniper
 * @date 10 Mar, 2023
 * LC382, Medium
 * Google
 */
public class LinkedListRandomNode {

    private ListNode head;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    /**
     * The i is the current Data number,
     * The first N Data kept, and then later Data kept with probability N/i,
     * and if it being kept, one of the original N Data is being randomly eliminated.
     * Finally, we can return these N data.
     *
     * Requirement:
     * The probability that the N numbers left being selected remains at N/L,
     * L keeps increasing for N < i <= L,
     * so that N/L is associated with i in all processes
     * N/L = N/(N+1) * (N+1)/(N+2) *... * (L-1)/L
     *
     * How to make the remaining N experience (i-1)/i,
     * so that the new i has 1/i probability to challenge each member in N,
     * 1/i = 1/N * N/i,
     * do N/i for i = (1-1/N *N/(N+1)) * (1-1/(N+2))*... *(1-1/L)
     *
     * @return
     */
    public int getRandom() {
        int res = head.val;
        int i = 2;
        ListNode cur = head.next;
        Random r = new Random();
        while (cur != null) {
            /**
             * Choose one number in [0, i - 1]
             */
            int j = r.nextInt(i);
            if (j == 0) {
                res = cur.val;
            }
            i++;
            cur = cur.next;
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {

        int val;

        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
