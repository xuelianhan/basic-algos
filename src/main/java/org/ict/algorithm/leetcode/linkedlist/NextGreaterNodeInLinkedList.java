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


    /**
     * Convert List to Array and Use Monotonic Stack.
     *
     * Input: head =[2,7,4,3,5]
     *
     * 0 1 2 3 4
     * 2 7 4 3 5
     *
     * i:0, stack:0
     * i:1, stack:0, list[0] < list[1]=7, pop 0, res[0] = list[1] = 7
     * i:2, stack:null, push 2 into the stack, stack:2
     * i:3, stack:2, list[2]=4 > list[3]=3, push index 3 into the stack.
     * i:4, stack:2,3, peek=3, list[peek] = 3 < list[4] = 5, pop 3 out of the stack, res[3] = list[4] = 5
     *      stack:2, peek=2, list[peek]=4 < list[4]=5, pop 2 out of the stack, res[2]= list[4] = 5
     *
     * res:
     * 0 1 2 3 4
     * 7 0 5 5 0
     *
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     * @param head
     * @return
     */
    public int[] nextLargerNodesV1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            list.add(cur.val);
        }

        int[] res = new int[list.size()];
        /**
         * Notice we use stack store the index instead of node's value here.
         */
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < list.size(); i++) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
                Integer top = stack.pop();
                res[top] = list.get(i);
            }
            stack.push(i);
        }
        return res;
    }

    /**
     *
     * Monotonic Bottom-Top Decreasing Stack.
     *
     * Use a Bottom-Top Decreasing Stack to store the current most big value.
     *
     * Input: head = [2,7,4,3,5]
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
