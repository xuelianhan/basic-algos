package org.ict.algorithm.leetcode.linkedlist;

import org.ict.algorithm.leetcode.array.ClosestPointToOrigin;

import java.util.*;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10^4.
 *
 * @author sniper
 * @date 20 Sep, 2022
 * LC23
 */
public class MergeKSortedLists {

    /**
     * Using Priority Queue
     *
     *
     * @param lists
     * @return
     */
    public ListNode mergeKListsV2(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        /**
         * Natural Sort.
         */
        PriorityQueue<ListNode> queue = new PriorityQueue<> (lists.length, (o1, o2) -> {
            if (o1.val < o2.val) {
                return -1;
            } else if (o1.val > o2.val) {
                return 1;
            } else {
                return 0;
            }
        });

        for (ListNode head : lists) {
            if (null == head) {
                continue;
            }
            queue.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (!queue.isEmpty()) {
            pre.next = queue.poll();
            pre = pre.next;

            if (pre.next != null) {
                queue.add(pre.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        ListNode dummy = new ListNode(0);
        ListNode preCur = dummy;
        for (ListNode head : lists) {
            if (head == null) {
                continue;
            }
            if (preCur != null || preCur == dummy) {
                preCur.next = head;
            }
            ListNode cur = head;
            while (cur != null) {
                list.add(cur.val);
                preCur = cur;
                cur = cur.next;
            }

        }
        Collections.sort(list);

        ListNode cur = dummy.next;
        int i = 0;
        while (cur != null) {
            cur.val = list.get(i);
            i++;
            cur = cur.next;
        }
        return dummy.next;
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
