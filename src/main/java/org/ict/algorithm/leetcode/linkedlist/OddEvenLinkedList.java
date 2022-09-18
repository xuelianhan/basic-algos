package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices,
 * and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * Example 2:
 *
 *
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 *
 * Constraints:
 *
 * The number of nodes in the linked list is in the range [0, 104].
 * -10^6 <= Node.val <= 10^6
 * @author sniper
 * @date 18 Sep, 2022
 * LC328
 *
 */
public class OddEvenLinkedList {


    public ListNode oddEvenListV2(ListNode head) {
        //todo
        return null;
    }

    /**
     * We can get an idea from problem LC138.
     *
     * An intuitive idea is to separate the original list into two parts.
     * One part is all with odd index, another is all with even index.
     * At last, we concat the tail of odd list to the head of the even list together.

     * @param head of the list
     * @return head of the list which has been adjusted.
     */
    public ListNode oddEvenList(ListNode head) {
        if (null == head || head.next == null || head.next.next == null) {
            return head;
        }

        /**
         * odd pointer points at the current node of odd list.
         * preOdd is the previous node before current odd.
         */
        ListNode preOdd = null;
        ListNode odd = head;

        /**
         * even pointer points at the current even node.
         */
        ListNode even = null;
        ListNode dummy = new ListNode(0);
        ListNode evenPre = dummy;

        while (odd != null) {
            /**
             * Mark the next odd node to be picked up.
             */
            ListNode nextOdd = (odd.next == null ? null : odd.next.next);

            /**
             * Extract the even node from original list.
             */
            even = odd.next;
            evenPre.next = even;
            evenPre = even;

            /**
             * Connect the odd node in original list and move cur to the next odd node.
             */
            odd.next = nextOdd;
            preOdd = odd;
            odd = nextOdd;
        }
        /**
         * Link the last node in odd list to the start of the even list.
         */
        preOdd.next = dummy.next;

        return head;
    }

    private static class ListNode {

        private int val;

        private ListNode next;

        public ListNode() {}

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
