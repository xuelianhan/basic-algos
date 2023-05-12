package org.ict.algorithm.leetcode.tree;

/**
 * Description
 * Given the root of a binary search tree and a target value,
 * return the value in the BST that is closest to the target.
 * If there are multiple answers, print the smallest.
 *
 * Example 1:
 *          4
 *        /   \
 *       2     5
 *     /  \
 *    1   3
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 *
 * Example 2:
 * Input: root = [1], target = 4.428571
 * Output: 1
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * -10^9 <= target <= 10^9
 * @author sniper
 * @date 11 May 2023
 * LC270, Easy
 */
public class ClosestBinarySearchTreeValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        two.left = one;
        two.right = three;
        root.left = two;
        root.right = five;
        double target = 3.714286;
        ClosestBinarySearchTreeValue instance = new ClosestBinarySearchTreeValue();
        int res = instance.closestValue(root, target);
        System.out.println(res);
    }


    /**
     * Understanding the following Iterative solution
     * e.g. root = [4,2,5,1,3], target = 3.714286
     *          4
     *        /   \
     *       2     5
     *     /  \
     *    1   3
     * ----------------------------------------
     * # Definition for a binary tree node.
     * # class TreeNode:
     * #     def __init__(self, val=0, left=None, right=None):
     * #         self.val = val
     * #         self.left = left
     * #         self.right = right
     * class Solution:
     *     def closestValue(self, root: Optional[TreeNode], target: float) -> int:
     *         res, mi = root.val, inf
     *         while root:
     *             diff = abs(root.val - target)
     *             if diff < mi:
     *                 mi = t
     *                 res = root.val
     *             if root.val > target:
     *                 root = root.left
     *             else:
     *                 root = root.right
     *         return res
     * @param root
     * @param target
     * @return
     */
    public int closestValueV1(TreeNode root, double target) {
        int res = root.val;
        double min = Double.MAX_VALUE;
        while (root != null) {
            double diff = Math.abs(root.val - target);
            if (diff < min) {
                min = diff;
                res = root.val;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }

    /**
     * Recursive Solution
     * --------------------------------
     * class Solution:
     *   def closestValue(self, root: Optional[TreeNode], target: float) -> int:
     *     # If target < root.val, search left subtree
     *     if target < root.val and root.left:
     *       left = self.closestValue(root.left, target)
     *       if abs(left - target) < abs(root.val - target):
     *         return left
     *
     *     # If target > root.val, search right subtree
     *     if target > root.val and root.right:
     *       right = self.closestValue(root.right, target)
     *       if abs(right - target) < abs(root.val - target):
     *         return right
     *
     *     return root.val
     * @param root
     * @param target
     * @return
     */
    public int closestValue(TreeNode root, double target) {
        if (target < root.val && root.left != null) {
            int left = closestValue(root.left, target);
            if (Math.abs(left - target) < Math.abs(root.val - target)) {
                return left;
            }
        }

        if (target > root.val && root.right != null) {
            int right = closestValue(root.right, target);
            if (Math.abs(right - target) < Math.abs(root.val - target)) {
                return right;
            }
        }
        return root.val;
    }
}
