package org.ict.algorithm.leetcode.linkedlist;

import org.ict.algorithm.util.StdOut;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 *
 * @author sniper
 * @date 02 Sep, 2022
 * LC24
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            /**
             * First and Second Nodes of the Pair
             */
            ListNode first = current.next;
            ListNode second = current.next.next;

            first.next = second.next;
            /**
             * second's next point at first which is current's next,
             * So the following using <code>second.next = first;</code> is ok too.
             */
            second.next = current.next;
            current.next = second;

            /**
             *  Move the pointer two nodes ahead
             */
            current = current.next.next;
        }
        return dummy.next;
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

    public static class DelDuplicatedNodes {

        public static void delDupNodes(SingleLinkedList<String> list) {
           HashSet<String> set = new HashSet<String>();
           Iterator<String> iter = list.iterator();
           while (iter.hasNext()) {
                String s = iter.next();
                if (set.contains(s)) {
                    list.remove(s);
                } else {
                    set.add(s);
                }
           }
        }

        public static void main(String[] args) {
            SingleLinkedList<String> list = new SingleLinkedList<String>();
            list.add("A");
            list.add("B");
            list.add("B");
            list.add("C");
            list.add("C");
            list.add("D");
            list.add("D");

            StdOut.println("Before remove duplicated nodes:" + list.toString());

            delDupNodes(list);

            StdOut.println("After remove duplicated nodes:" + list);
        }

    }

    public static class FindTheKthNode<Item> {

        public static String nthTolastV2(LinkedList<String> list, int k) {
            String result = "";
            if (k <= 0) {
                return result;
            }

            Iterator<String> iter = list.descendingIterator();
            int i = 1;
            while (iter.hasNext()) {
                result = iter.next();
                if (i == k) {
                    break;
                }
                i++;
            }

            return result;
        }

       /* public static Node nthToLast(SingleLinkedList list, int k) {
            if (k <= 0) return null;

            Node p1 = list.getFirst();
            Node p2 = list.getFirst();

            for (int i = 0; i < k - 1; i++) {
                if (p2 == null) {
                    return null;
                }
                p2 = p2.next;
            }

            if (p2 == null) {
                return null;
            }

            while (p2 != null) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }*/

        public static void main(String[] args) {
            LinkedList<String> list = new LinkedList<String>();
            for (char i = 65; i < 69; i++) {
                list.add(Character.toString(i));
            }

            System.out.println(list);

            for (int i = 1; i <= list.size(); i++) {
                String s = nthTolastV2(list, i);
                System.out.println("The " + i + "th data to last is " + s);
            }
        }
    }

    /**
     *
     *
     * Tue Aug  2 10:19:08 CST 2016
     */
    public static class SingleLinkedList<Item> implements Iterable<Item> {

        private transient Node first;

        private transient Node last;

        private transient Node lastPrior;

        private transient int N;

        public SingleLinkedList() {
            this.first = null;
            this.last = null;
            this.lastPrior = null;
            N = 0;
        }

        private class Node {
            private Item item;
            private Node next;
        }

        public int size() {
            return N;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public void add(Item data) {
            Node oldlast = last;
            last = new Node();
            last.item = data;
            if (oldlast != null) {
                oldlast.next = last;
            }
            lastPrior = oldlast;
            N++;
            if (N == 1) {
                first = last;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Item item : this) {
                sb.append(" " + item);
            }
            return sb.toString();
        }

        public void remove(Item data) {
            Node prior = null;
            for (Node n = first; n != null;) {
                Node next = n.next;
                if (n.item == data) {
                    if (prior == null) {
                        prior = next;
                        first = prior;
                    } else {
                        prior.next = next;
                        prior = n;
                    }
                    N--;
                }
                prior = n;
                n = next;
            }
        }

        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {

            private Node cur = first;

            private Node lastReturned = first;

            public boolean hasNext() {
                return cur != null;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new IllegalArgumentException();
                }
                Item item = cur.item;
                lastReturned = cur;
                cur = cur.next;
                return item;
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }

        }

        public Node getFirst() {
            return this.first;
        }

        public static void main(String[] args) {
            SingleLinkedList<String> sl = new SingleLinkedList<String>();
            sl.add("A");
            sl.add("B");
            sl.add("C");
            sl.add("D");

            StdOut.println("Input:" + sl + "\nOutput:");
            Iterator<String> iter = sl.iterator();
            while (iter.hasNext()) {
                String s = iter.next();
                StdOut.print(s + ",");
            }
            String del = "D";
            sl.remove(del);
            StdOut.println("\nAfter " + del + " has been removed, Output:" + sl + "\n");
        }

    }
}
