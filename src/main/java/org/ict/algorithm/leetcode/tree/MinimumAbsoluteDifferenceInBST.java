package org.ict.algorithm.leetcode.tree;

import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST),
 * return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 * Example 1:
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 104].
 * 0 <= Node.val <= 105
 *
 * LC530 is same as LC783 MinimumDistanceBetweenBSTNodes
 */
public class MinimumAbsoluteDifferenceInBST {

    /**
     * Use in-order traversal and get first and second node
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        Integer min = Integer.MAX_VALUE;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                /**
                 * middle order traversal
                 * replace access middle val with min calculate.
                 */
                if (pre != null) {
                    min = Math.min(min, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return min;
    }

}
