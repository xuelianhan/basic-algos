package org.ict.algorithm.leetcode.linkedlist;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists in a one sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 * LC21
 */
public class MergeTwoSortedLists {

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
     * Iterative solution
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeSortedListsV2(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                prev.next = list2;
                list2 = list2.next;
            } else if (list2.val >= list1.val) {
                prev.next = list1;
                list1 = list1.next;
            }
            prev = prev.next;

        }
        if (list1 != null) {
            prev.next = list1;
        }
        if (list2 != null) {
            prev.next = list2;
        }
        return dummy.next;
    }

    public static ListNode mergeSortedListsV1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                prev.next = l2;
                l2 = l2.next;
            } else if (l2.val >= l1.val) {
                prev.next = l1;
                l1 = l1.next;
            }
            prev = prev.next;
        }
        if (l1 != null) {
            prev.next = l1;
        }
        if (l2 != null) {
            prev.next = l2;
        }
        return dummy.next;
    }

    /**
     * Recursive solution
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeSortedLists(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeSortedLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeSortedLists(list1, list2.next);
            return list2;
        }
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print("-->"); 
            } else {
                System.out.print("-->null"); 
            }
        }
        System.out.println();
    }
    
    public static ListNode constructLinkedList(int start, int end, int step) {
        ListNode l1 = new ListNode(start);
        ListNode previousNode = l1;
        for (int i = start; i < end; i+=step) {
            ListNode inode = new ListNode(i + step);
            previousNode.next = inode;
            previousNode = inode;
        }
        printList(l1);
        return l1;
    }
    
    /**
     * 
     * Execution result:
     * 
     * 0-->1-->2-->3-->4-->5-->null
     * 5-->4-->3-->2-->1-->0-->null
     * 
     */
    public static void main(String[] args) {
        /* construct LinkedList with nodes */
        ListNode l1 = constructLinkedList(0, 5, 1);
        ListNode l2 = constructLinkedList(5, 12, 1);

        MergeTwoSortedLists instance = new MergeTwoSortedLists();
        ListNode l3 = instance.mergeSortedLists(l1, l2);
        printList(l3);
    }
    
}
