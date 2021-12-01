package org.ict.algorithm.leetcode.tree;

import org.ict.algorithm.leetcode.breadthfirstsearch.BinaryTreeLevelOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given two binary trees root1 and root2.
 *
 * Imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 * You need to merge the two trees into a new binary tree.
 * The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of the new tree.
 *
 * Return the merged tree.
 *
 * Note: The merging process must start from the root nodes of both trees.
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 *
 * Input: root1 = [1], root2 = [1,2]
 * Output: [2,2]
 *
 * Constraints:
 *
 * The number of nodes in both trees is in the range [0, 2000].
 * -10^4 <= Node.val <= 10^4
 *
 * @author sniper
 * @date 2021/12/1
 * LC617
 */
public class MergeTwoBinaryTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = null;
        TreeNode root = mergeTrees(root1, root2);
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (null == root1 && null == root2) {
            return null;
        }
        if (null == root1 && null != root2) {
            return root2;
        }
        if (null != root1 && null == root2) {
            return root1;
        }
        TreeNode root = new TreeNode();
        if (null != root1 && null != root2) {
            root.val = root1.val + root2.val;
            TreeNode left = mergeTrees(root1.left, root2.left);
            TreeNode right = mergeTrees(root1.right, root2.right);
            root.left = left;
            root.right = right;
        }
        return root;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            /* queue size indicates number of nodes at each level */
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            result.add(temp);
        }
        return result;
    }


}
