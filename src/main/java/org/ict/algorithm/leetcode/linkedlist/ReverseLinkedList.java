package org.ict.algorithm.leetcode.linkedlist;

/**
 * p206. Reverse Linked List
 * 92.reverse-linked-list-ii
 * @see https://leetcode.com/problems/reverse-linked-list/discuss/58125/In-place-iterative-and-recursive-Java-solution
 * @see https://leetcode.com/problems/reverse-linked-list-ii/description/
 * @see https://www.geeksforgeeks.org/reverse-a-linked-list/
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
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.
     * 
     * For example:
     * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * return 1->4->3->2->5->NULL.
     * 
     * Note:
     * Given m, n satisfy the following condition:
     * 1 ≤ m ≤ n ≤ length of list.
     * 
     * @return
     */
    public static Node reverseBetween(Node head, int m, int n) {
        if (m < 1 || n < 1 || (m > n)) {
            throw new IllegalArgumentException("input m and n is not satisfied condition (1 <= m <= n <= length of list)");
        }
        if (head == null) {
            return null;
        }
        // create a dummy node to mark the head of this list
        Node dummy = new Node(0);
        dummy.next = head;
        // make a pointer pre as a marker for the node before reversing
        Node pre = dummy;
        for (int i = 0; i < m-1; i++) {
            pre = pre.next;
        }
        // start is a pointer to the beginning of a sub-list that will be reversed
        Node start = pre.next;
        // then is a pointer to a node that will be reversed
        Node then = start.next;
        
        // Init input: pre(A)-->start(B)-->then(C)-->D-->null
        //
        // procedure:
        // pre(A)-->start(B)-->then(C)-->D-->null
        // pre(A)-->(C)-->start(B)-->then(D)-->null
        // pre(A)-->(D)-->(C)-->start(B)-->then(null)
        for (int i = 0; i < n-m; i++) {
            start.next = then.next;//1
            then.next = pre.next;//2
            pre.next = then;//3
            then = start.next;//4
        }
        return dummy.next;
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
    
    public static Node reverse2(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
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
        
        Node newHeadTwo = reverse2(head);
        printList(newHeadTwo);
        
        /* recursive solution */
        Node newHead2 = reverse(newHead, null);
        printList(newHead2);
        
        Node newHead3 = reverseBetween(newHead2, 2, 6);
        printList(newHead3);
    }
    
}


