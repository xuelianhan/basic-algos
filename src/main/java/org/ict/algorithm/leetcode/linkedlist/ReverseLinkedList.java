package org.ict.algorithm.leetcode.linkedlist;

/**
 * p206. Reverse Linked List
 * 92.reverse-linked-list-ii
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/discuss/58125/In-place-iterative-and-recursive-Java-solution"></a>
 * @see <a href="https://leetcode.com/problems/reverse-linked-list-ii/description/"></a>
 * @see <a href="https://www.geeksforgeeks.org/reverse-a-linked-list/"></a>
 * LC206
 */
public class ReverseLinkedList {
    
    /**
     * Definition for singly-linked list.
     * */
    private static class ListNode {
         int val;
         
         ListNode next;
         
         ListNode(int x) {
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m < 1 || n < 1 || (m > n)) {
            throw new IllegalArgumentException("input m and n is not satisfied condition (1 <= m <= n <= length of list)");
        }
        if (head == null) {
            return null;
        }
        // create a dummy node to mark the head of this list
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // make a pointer pre as a marker for the node before reversing
        ListNode pre = dummy;
        for (int i = 0; i < m-1; i++) {
            pre = pre.next;
        }
        // start is a pointer to the beginning of a sub-list that will be reversed
        ListNode start = pre.next;
        // then is a pointer to a node that will be reversed
        ListNode then = start.next;
        
        // Init input: pre(A)-->start(B)-->then(C)-->D-->null
        //
        // procedure:
        // pre(A)-->start(B)-->then(C)-->D-->null
        // pre(A)-->(C)-->start(B)-->then(D)-->null
        // pre(A)-->(D)-->(C)-->start(B)-->then(null)
        for (int i = 0; i < n-m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }
    
    /**
     * iterative solution
     * use 3 pointer: newHead, current, next
     * @param current
     */
    public ListNode reverse(ListNode current) {
        ListNode newHead = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = newHead;
            newHead = current;
            current = next;
        }
        return newHead;
    }
    
    /**
     * iterative solution
     * user 3 pointer: prev, current, next
     * @param head
     * @return
     */
    public ListNode reverse2(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
    
    /**
     * recursive solution
     * @param head
     */
    public ListNode reverse(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverse(next, head);
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

  
    /**
     * 
     * Execution result:
     * 
     * 0-->1-->2-->3-->4-->5-->null
     * 5-->4-->3-->2-->1-->0-->null
     * 0-->1-->2-->3-->4-->5-->null
     * 5-->4-->3-->2-->1-->0-->null
     * 5-->0-->1-->2-->3-->4-->null
     * 
     */
    public static void main(String[] args) {
        ReverseLinkedList instance = new ReverseLinkedList();
        /* construct LinkedList with nodes */
        ListNode head = new ListNode(0);
        ListNode previousNode = head;
        for (int i = 0; i < 5; i++) {
            ListNode inode = new ListNode(i + 1);
            previousNode.next = inode;
            previousNode = inode;
        }
        printList(head);
        
        /* iterative solution */
        ListNode newHead = instance.reverse(head);
        printList(newHead);
        
        ListNode newHead1 = instance.reverse2(newHead);
        printList(newHead1);
        
        /* recursive solution */
        ListNode newHead2 = instance.reverse(newHead1, null);
        printList(newHead2);
        
        ListNode newHead3 = instance.reverseBetween(newHead2, 2, 6);
        printList(newHead3);
    }
    
}


