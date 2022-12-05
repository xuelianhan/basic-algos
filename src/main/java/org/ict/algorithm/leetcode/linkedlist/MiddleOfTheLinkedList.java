package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
 *
 * @author sniper
 * @date 2022/8/22
 * LC876
 */
public class MiddleOfTheLinkedList {

    public static void main(String[] args) {

    }

    /**
     * Definition for singly-linked list.
     * */
    private static class ListNode {
        int val;

        ListNode next;

        ListNode(){}

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }

    /**
     * Time Cost 0ms
     * e.g.head = [1,2,3,4,5]
     * slow:1, fast:1
     * slow:2, fast:3
     * slow:3, fast:5
     * fast != null but fast.next == null, while-loop-end
     * return slow
     *
     * e.g.head = [1,2,3,4,5,6]
     * slow:1, fast:1
     * slow:2, fast:3
     * slow:3, fast:5
     * slow:4, fast:null
     * return slow
     *
     * e.g. head = [1,2]
     * slow:1, fast:1
     * slow:2, fast:null
     * return slow
     *
     * @param head
     * @return
     */
    public ListNode middleNodeV1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        /**
         * Notice condition: fast != null and fast.next != null
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Time Cost 11ms
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (null == head) {
            return null;
        }

        int cnt = 0;
        ListNode cur = head;
        while(cur != null) {
            cur = cur.next;
            cnt++;
        }
        /**
         * 1,2,3,4,5  cnt=5, stop at 2 index started from zero(0,1,2)
         * 1,2,3,4,5,6  cnt=6, stop at 3 index started from zero(0,1,2,3)
         */
        int stop = (cnt / 2);
        ListNode pre = head;
        int i = 0;
        while (pre != null) {
            if (i == stop) {
                break;
            }
            pre = pre.next;
            i++;
        }

        return pre;
    }
}
