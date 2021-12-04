package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree and a sum,
 * determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 * @author sniper
 * LC112
 */
public class BinaryTreePathSum {

    public boolean hasPathSumRecursion(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSumRecursion(root.left, sum - root.val) || hasPathSumRecursion(root.right, sum - root.val);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (null == cur) {
                    continue;
                }
                //check whether current node is a leaf.
                if (cur.left == null && cur.right == null) {
                    if (sum == cur.val) {
                        return true;
                    }
                }
                if (null != cur.left) {
                    cur.left.val = cur.val + cur.left.val;
                    queue.offer(cur.left);
                }
                if (null != cur.right) {
                    cur.right.val = cur.val + cur.right.val;
                    queue.offer(cur.right);
                }
            }
        }
        return false;
    }
}
