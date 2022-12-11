package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * Example 3:
 *
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * @author sniper
 * LC112
 */
public class BinaryTreePathSum {

    public boolean hasPathSumV1(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSumV1(root.left, sum - root.val) || hasPathSumV1(root.right, sum - root.val);
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
