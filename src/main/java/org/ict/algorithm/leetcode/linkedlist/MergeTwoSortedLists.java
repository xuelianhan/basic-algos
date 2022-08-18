package org.ict.algorithm.leetcode.linkedlist;

/**
 *
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
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();

        return null;
    }
    
    public static ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                prev.next = l2;
                l2 = l2.next;
            } else if (l2.val > l1.val) {
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
        
        ListNode l3 = mergeSortedLists(l1, l2);
        printList(l3);
    }
    
}
