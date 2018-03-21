package org.ict.algorithm.leetcode;

/**
 * 206. Reverse Linked List
 *
 * @see https://leetcode.com/problems/reverse-linked-list/discuss/58125/In-place-iterative-and-recursive-Java-solution
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 *
 */
public class ReverseLinkedList {
    
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
    
    /**
     * iterative solution
     * @param head
     */
    public static Node reverse(Node head) {
        Node newHead = null;
        while (head != null) {
            Node next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    
    /**
     * recursive solution
     * @param head
     */
    public static Node reverse(Node head, Node newHead) {
        if (head == null) {
            return newHead;
        }
        Node next = head.next;
        head.next = newHead;
        return reverse(next, head);
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
        Node head = new Node(0);
        Node previousNode = head;
        for (int i = 0; i < 5; i++) {
            Node inode = new Node(i + 1);
            previousNode.next = inode;
            previousNode = inode;
        }
        printList(head);
        
        /* iterative solution */
        Node newHead = reverse(head);
        printList(newHead);
        
        /* recursive solution */
        Node newHead2 = reverse(newHead, null);
        printList(newHead2);
    }
    
}


