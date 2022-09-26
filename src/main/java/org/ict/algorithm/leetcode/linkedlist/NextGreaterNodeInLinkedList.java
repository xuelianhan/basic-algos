package org.ict.algorithm.leetcode.linkedlist;

import java.util.*;

/**
 * You are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node.
 * That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed).
 * If the ith node does not have a next greater node, set answer[i] = 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 *
 * Input: head = [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 *
 * @author sniper
 * @date 20 Sep, 2022
 * LC1019
 */
public class NextGreaterNodeInLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode seven = new ListNode(7);
        head.next = seven;
        ListNode four = new ListNode(4);
        seven.next = four;
        ListNode three = new ListNode(3);
        four.next = three;
        ListNode five = new ListNode(5);
        three.next = five;
        int[] res = nextLargerNodes(head);
        System.out.println(Arrays.toString(res));
    }

    public int[] nextLargerNodesV1(ListNode head) {
        return null;
    }

    /**
     *
     * Monotonic Bottom-Top Decreasing Stack.
     *
     * Use a Bottom-Top Decreasing Stack to store the current most big value.
     * 
     * Time Complexity O(2*N).
     * Space Complexity O(N).
     *
     * @param head
     * @return
     */
    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new int[]{0};
        }
        Map<ListNode, Integer> map = new HashMap<>();
        Stack<ListNode> stack = new Stack<>();
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            while (!stack.isEmpty() && stack.peek().val < cur.val) {
                ListNode top = stack.pop();
                map.put(top, cur.val);
            }
            stack.push(cur);
            cur = cur.next;
            len++;
        }
        int[] res = new int[len];
        int i = 0;
        ListNode iter = head;
        while (iter != null) {
            int x = map.getOrDefault(iter, 0);
            res[i] = x;
            iter = iter.next;
            i++;
        }
        return res;
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
