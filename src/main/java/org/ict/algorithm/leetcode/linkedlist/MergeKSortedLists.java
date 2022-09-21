package org.ict.algorithm.leetcode.linkedlist;


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

    public ListNode mergeKListsV3(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        /**
         * Natural Sort using MinPQ.
         * The root in MinPQ is the smallest element.
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

        /**
         * Add head of every list into the queue.
         * Notice we only add the head, not add all nodes into the queue.
         */
        for (ListNode head : lists) {
            if (null == head) {
                continue;
            }
            queue.offer(head);
        }

        /**
         * When dequeue from the queue, the output is natural order.
         */
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
         * Natural Sort using MinPQ.
         * The root in MinPQ is the smallest element.
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

        /**
         * Add head of every list into the queue.
         * Notice we only add the head, not add all nodes into the queue.
         */
        for (ListNode head : lists) {
            if (null == head) {
                continue;
            }
            queue.offer(head);
        }

        /**
         * When dequeue from the queue, the output is natural order.
         * Because each list has been ordered.
         * we process the ListNode here list by list.
         * Take three lists for example:
         * list1: 4->5->null
         * list2: 1->2->3->null
         * list3: 9->null
         *
         * ----------head added into queue-------
         * queue add three heads firstly.
         * queue: 1 9 4, root is 1
         * --------------------------------------
         *
         * ---------list2 sort ----------------
         * node-1 of list2 dequeue
         * dummy->1->null
         * queue: 4 9
         * node-1 of list2 has next node-2, so node-2 of list2 enqueue
         * queue: 2 9 4
         *
         * node-2 of list2 dequeue
         * dummy->1->2->null
         * queue: 4 9
         * node-2 of list2 has next node-3, so node-3 of list2 enqueue
         * queue: 3 4 9
         *
         * node-3 of list2 dequeue
         * dummy->1->2->3->null
         * queue: 4 9
         * node-3 of list2 has no next node, so list2 ends
         * --------------------------------------
         *
         * ---------list1 sort ------------------
         * node-4 of list1 dequeue
         * dummy->1->2->3->4->null
         * queue: 9
         * node-4 of list1 has next node-5, so node-5 of list1 enqueue
         * queue: 5 9
         *
         * node-5 of list1 dequeue
         * dummy->1->2->3->4->5->null
         * queue:9
         * node-5 of list1 has no next node, so list1 ends.
         * --------------------------------------
         *
         * ---------list3 sort ------------------
         * node-9 of list3 dequeue
         * dummy->1->2->3->4->5->9->null
         * node-9 of list3 has no next node, so list3 ends.
         * --------------------------------------
         * queue has no more elements, final result:
         * dummy->1->2->3->4->5->9->null
         *
         */
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (!queue.isEmpty()) {
            ListNode cur =  queue.poll();
            pre.next = cur;
            pre = cur;

            if (cur.next != null) {
                queue.add(cur.next);
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
