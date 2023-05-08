package org.ict.algorithm.leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of theirs nodes contain a single
 * digit.Add the two numbers and return it as a linked list.
 * You amy assume the two numbers do not contain any leading zero,except the number
 * 0 itself.
 * 
 * Algorithm
 * Just like how you would sum two numbers on a piece of paper, we begin by summing the
 * least-significant digits, which is the head of l1 and l2. Since each digit is in the
 * range of 0...9, summing tow digits may "overflow".For example 5 + 7 = 12. In this case,
 * we set the current digit to 2 and bring over the carry = 1 to the next iteration.carry
 * must be eithe 0 or 1 because the largest possible sum of two digits(including the carry)
 * is 9 + 9 + 1 = 19.
 * 
 * 342 + 465 = 708
 * Input:(2 -> 4 -> 3) + (5 -> 6 ->4)
 * Output:7 -> 0 -> 8
 * 
 * Complexity Analysis
 * Time complexity:O(max(m, n))
 * Space complexity:O(max(m, n));
 *
 */
public class TwoNumbersAdd {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, current = dummyHead;
        int carry = 0;
        int mod = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.data : 0;
            int y = (q != null) ? q.data : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            mod = sum % 10;
            current.next = new ListNode(mod);
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    
    static class ListNode {
        private Integer data;
        private ListNode next;
        public ListNode (Integer data) {
            this.data = data;
            this.next = null;
        }
        public Integer getData() {
            return data;
        }
        public void setData(Integer data) {
            this.data = data;
        }
        public ListNode getNext() {
            return next;
        }
        public void setNext(ListNode next) {
            this.next = next;
        }
    }
    
    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
        }
        System.out.println("");
    }
    /**
     * Insert node at the end without dummy head
     * @param array
     * @return
     */
    public static ListNode createAtBeginning(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode first = null;
        ListNode oldfirst = null;
        for(int i = 0; i < array.length; i++) {
            oldfirst = first;
            first = new ListNode(array[i]);
            first.next = oldfirst;
        }
        return first;
    }
    
    /**
     * Insert node at the end without dummy head
     * @param array
     * @return
     */
    public static ListNode createAtEnd(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode first = null;
        ListNode last = null;
        ListNode oldlast = null;
        for(int i = 0; i < array.length; i++) {
            oldlast = last;
            last = new ListNode(array[i]);
            if (oldlast != null) {
                oldlast.next = last;
            } else {
                first = last;
            }
        }
        return first;
    }
    
    public static void main(String[] args) {
        
        ListNode l1 = createAtBeginning(new int[] {0, 1});
        ListNode l2 = createAtBeginning(new int[] {0, 1, 2});
        print(l1);
        print(l2);
        
        //10 + 210 = 220
        ListNode l3 = createAtEnd(new int[] {0, 1});
        ListNode l4 = createAtEnd(new int[] {0, 1, 2});
       
        ListNode result1 = addTwoNumbers(l3, l4);
        print(l3);
        print(l4);
        print(result1);
        //null + 10 = 10
        
        //99 + 1 = 100
    }
    
}
