package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.*;

/**
 * You are given the root of a binary tree and a positive integer k.
 *
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 *
 * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
 *
 * Note that two nodes are on the same level if they have the same distance from the root.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,8,9,2,1,3,7,4,6], k = 2
 * Output: 13
 * Explanation: The level sums are the following:
 * - Level 1: 5.
 * - Level 2: 8 + 9 = 17.
 * - Level 3: 2 + 1 + 3 + 7 = 13.
 * - Level 4: 4 + 6 = 10.
 * The 2nd largest level sum is 13.
 * Example 2:
 *
 *
 * Input: root = [1,2,null,3], k = 1
 * Output: 3
 * Explanation: The largest level sum is 3.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 10^5
 * 1 <= Node.val <= 10^6
 * 1 <= k <= n
 * @author sniper
 * @date 10 Mar, 2023
 * LC2583, Medium
 */
public class KthLargestSumInBinaryTree {

    public long kthLargestLevelSumV1(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Long> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            /**
             * Notice here using Long other than Integer.
             */
            Long levelSum = 0L;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelSum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            list.add(levelSum);
        }

        Collections.sort(list);
        return list.size() < k ? -1 : list.get(list.size() - k);
    }


    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        /**
         * Notice here using Long other than Integer.
         */
        PriorityQueue<Long> minHeap = new PriorityQueue<>(k);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Long levelSum = 0L;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelSum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            minHeap.offer(levelSum);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        /**
         * if the high of tree is less than k, return -1
         */
        return minHeap.size() < k ? -1 : minHeap.peek();
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


}
