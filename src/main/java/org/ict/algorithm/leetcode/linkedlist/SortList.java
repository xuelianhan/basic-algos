package org.ict.algorithm.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 * @author sniper
 * @date 20 Sep, 2022
 * LC148
 */
public class SortList {


    /**
     * Top-Down-Merge-Sort.
     * @param head
     * @return
     */
    public ListNode sortListV2(ListNode head) {
        //todo
        return null;
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        Collections.sort(list);

        cur = head;
        int i = 0;
        while (cur != null) {
            cur.val = list.get(i);
            i++;
            cur = cur.next;
        }

        return head;
    }

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
}
