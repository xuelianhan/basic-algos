package org.ict.algorithm.leetcode.hoyoverse;

/**
 * Description
 * Determines if there is a ring in the given chain table.
 * Returns true if there is a ring, otherwise false.
 * Data range: length of the chain table
 * 0 ≤ n ≤ 10000, the value of any node in the chain table satisfies
 * ∣val∣ <= 100000
 * Requirement: space complexity O(1), time complexity O(n)
 *
 * The input is divided into two parts, the first part is the chain table, the second part represents whether there is a ring or not,
 * and then the composed head node is passed into the function.
 * 1 represents no ring, the other numbers represent a ring, these parameters are explained only to facilitate the reader to self-test debugging.
 * The actual reading in the programming is the head node of the chain table.
 * For example, if you enter {3,2,0,-4},1, the corresponding structure of the chain table is shown below:
 * You can see that the entry node of the ring is the 1st node from the head node (Note: the head node is the 0th node), so the output is true.
 * Example 1
 * Input: {3,2,0,-4},1
 * Return value: true
 * Explanation: The first part {3,2,0,-4} represents a chain table,
 * the second part 1 means that -4 to position 1 (note: the head node is position 0), that is, -4->2 there is a link,
 * the head of the incoming composition is a chain table with a ring, return true
 *
 * Example 2
 * Input: {1},-1
 * Return value: false
 * Description: The first part {1} represents a linked table, -1 represents no ring,
 * the incoming head is a single linked table with no ring, return false
 *
 * Example 3
 * Input:
 * {-1,-7,7,-4,19,6,-9,-5,-2,-5},6
 * Return value:
 * true
 * @author sniper
 * @date 21 Jun 2023
 * NC4, Easy
 */
public class DetectCycleInLinkedList {

    public boolean hasCycle(ListNode head) {
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
