package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * For this problem,
 * a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 *
 * Example 1:
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 *
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in head is in the range [0, 2 * 104].
 * -10^5 <= Node.val <= 10^5
 *
 * @author sniper
 * @date 06 Sep, 2022
 * LC109
 */
public class ConvertSortedListToBinarySearchTree {


    public TreeNode sortedListToBST(ListNode head) {
        if (null == head) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        /**
         * slow point at the root
         * fast point at the last node
         */
        TreeNode root = new TreeNode(slow.val);
        TreeNode left = toBST(head, slow);
        TreeNode right = toBST(slow.next, tail);
        root.left = left;
        root.right = right;
        return root;
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

    /**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
