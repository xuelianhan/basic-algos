package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a singly linked list,
 * sort the list using insertion sort,
 * and return the sorted list's head.
 *
 * The steps of the insertion sort algorithm:
 *
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data,
 * finds the location it belongs within the sorted list and inserts it there.
 * It repeats until no input elements remain.
 * The following is a graphical example of the insertion sort algorithm.
 * The partially sorted list (black) initially contains only the first element in the list.
 * One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 *
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
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 5000].
 * -5000 <= Node.val <= 5000
 * @author sniper
 * @date 17 Sep, 2022
 * LC147
 */
public class InsertionSortList {


    /**
     * Solution in insertionSortList always reset pre to dummy and looking forward the
     * next insert location from beginning.
     * To tune this problem, we no longer reset pre to dummy.
     * In another way, we compare pre value with cur value before finding the insert location.
     * If pre.value >= cur.value, we reset pre to dummy again.
     * If pre.value < cur.value, we don't need to reset pre to beginning, because pre next is the right insert location.
     *
     * Recommend this solution.
     * Time Cost 2ms.
     *
     * @param head
     * @return
     */
    public ListNode insertionSortListV1(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode cur = head;

        ListNode next = null;
        while (cur != null) {
            next = cur.next;

            if (pre.val >= cur.val) {
                pre = dummy;
            }
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }

            cur.next = pre.next;
            pre.next = cur;

            cur = next;
        }
        return dummy.next;
    }

    /**
     *    ListNode pre = dummy;
     *    ListNode cur = head;
     *    ListNode next = cur.next;
     *
     *    dummy[0]--->null
     *          ^
     *          |
     *          pre
     *
     *         head
     *          |
     *          V
     *          3--->2--->1--->null
     *          ^    ^
     *          |    |
     *         cur  next
     *-----------------------------------
     *    cur.next = pre.next;
     *    pre.next = cur;
     *    pre = dummy;
     *    cur = next;
     *
     *    dummy[0]--->3--->null
     *          ^
     *          |
     *          pre
     *
     *          2--->1--->null
     *          ^
     *          |
     *         cur
     *------------------------------------
     *    ListNode next = cur.next;
     *
     *    dummy[0]--->3--->null
     *          ^
     *          |
     *          pre
     *
     *          2--->1--->null
     *          ^    ^
     *          |    |
     *         cur  next
     *
     *    cur.next = pre.next;
     *    pre.next = cur;
     *    pre = dummy;
     *    cur = next;
     *
     *    dummy[0]--->2--->3--->null
     *          ^
     *          |
     *          pre
     *
     *          1--->null
     *          ^
     *          |
     *         cur
     * -----------------------------------
     *    dummy[0]--->2--->3--->null
     *          ^
     *          |
     *          pre
     *
     *          1--->null
     *          ^     ^
     *          |     |
     *         cur   next
     *
     *    dummy[0]--->1--->2--->3--->null
     *          ^
     *          |
     *          pre
     *
     *         null
     *          ^
     *          |
     *         cur
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        /**
         * when head == null or list has only one element, no need to sort.
         */
        if (null == head || head.next == null) {
            return head;
        }
        /**
         * dummy points at the new sorted list.
         */
        ListNode dummy = new ListNode(0);

        /**
         * pre points at the location to insert.
         */
        ListNode pre = dummy;
        /**
         * cur points at the node need to be comparing with.
         */
        ListNode cur = head;

        /**
         * next points at the next node to be comparing with.
         */
        ListNode next = null;

        while (cur != null) {
            /**
             * Mark the next node to be comparing with.
             */
            next = cur.next;

            /**
             * Scan the insert location.
             */
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            /**
             * Insert cur after pre
             */
            cur.next = pre.next;
            pre.next = cur;

            /**
             * Let pre point at scanning start again.
             */
            pre = dummy;

            /**
             * Move cur to next node.
             */
            cur = next;
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
