package org.ict.algorithm.leetcode.linkedlist;

/**
 * Write a function to delete a node in a singly-linked list.
 * You will not be given access to the head of the list,
 * instead you will be given access to the node to be deleted directly.
 *
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5,
 * the linked list should become 4 -> 1 -> 9 after calling your function.
 * Example 2:
 *
 *
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1,
 * the linked list should become 4 -> 5 -> 9 after calling your function.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [2, 1000].
 * -1000 <= Node.val <= 1000
 * The value of each node in the list is unique.
 * The node to be deleted is in the list and is not a tail node
 * @author sniper
 * @date 2022/8/23
 * LC237
 */
public class DeleteNodeInLinkedList {

    /**
     * The approach for the problem “Delete a Node from linked list without head pointer”
     * would be to swap the data of the node to be deleted and the next node in the linked list.
     * Each node in the linked list stores which node is next.
     * So this operation has only O(1) time complexity.
     * Now when the data has been swapped.
     * Once again, we have changed the problem to the deletion of the node whose parent’s address is given.
     * So now it’s easier for us to solve the problem.
     * But there is one edge case when we have to delete the end node but we won’t be able to delete that.
     * Because there is no next node.
     * @param node
     */
    public void deleteNode(ListNode node) {
        if (null == node) {
            return;
        }
        if (node.next == null) {
            return;
        }
        ListNode prev = node;
        ListNode cur = prev.next;
        prev.val = cur.val;
        prev.next = cur.next;
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
