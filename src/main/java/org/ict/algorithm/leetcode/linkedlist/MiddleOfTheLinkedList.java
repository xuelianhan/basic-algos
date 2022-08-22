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
        int stop = (cnt % 2 == 0 ? (cnt / 2 + 2) : (cnt / 2 + 1));
        ListNode pre = head;
        int i = 1;
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
