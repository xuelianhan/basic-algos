package org.ict.algorithm.leetcode.linkedlist;

public class MergeTwoSortedLists {

    /**
     * Definition for singly-linked list.
     * */
    private static class Node {
         int val;
         
         Node next;
         
         Node(int x) { 
             val = x; 
         }
    }
    
    public static Node mergeSortedLists(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node prev = dummy;
        
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
    
    public static void printList(Node head) {
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
    
    public static Node constructLinkedList(int start, int end, int step) {
        Node l1 = new Node(start);
        Node previousNode = l1;
        for (int i = start; i < end; i+=step) {
            Node inode = new Node(i + step);
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
        Node l1 = constructLinkedList(0, 5, 1);
        Node l2 = constructLinkedList(5, 12, 1);
        
        Node l3 = mergeSortedLists(l1, l2);
        printList(l3);
    }
    
}
