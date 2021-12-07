package org.ict.algorithm.leetcode.tree;

import org.ict.algorithm.leetcode.breadthfirstsearch.BinaryTreeLevelOrderTraversal;

import java.util.*;

/**
 * You are given the root of a binary tree where each node has a value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most significant bit.
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1,
 * then this could represent 01101 in binary, which is 13.
 *
 * For all leaves in the tree,
 * consider the numbers represented by the path from the root to that leaf.
 *
 * Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits integer.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val is 0 or 1.
 *
 * @author sniper
 * @date 2021/12/1
 * LC1022
 */
public class SumOfRootToLeafBinaryNumbers {

    public static void main(String[] args) {
        TreeNode leaf1 = new TreeNode(0);
        TreeNode leaf2 = new TreeNode(1);
        TreeNode leaf3 = new TreeNode(0);
        TreeNode leaf4 = new TreeNode(1);
        TreeNode middle1 = new TreeNode(0);
        TreeNode middle2 = new TreeNode(1);
        TreeNode root = new TreeNode(1);
        middle1.left = leaf1;
        middle1.right = leaf2;
        middle2.left = leaf3;
        middle2.right = leaf4;
        root.left = middle1;
        root.right = middle2;
        List<List<Integer>> result = bfs(root);
        System.out.println(result);
    }

    public int sumRootToLeaf(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 0;
    }

    public static List<List<Integer>> bfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            /* queue size indicates number of nodes at each level */
            int size = queue.size();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    result.add(temp);
                }
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);

                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            //result.add(temp);
        }
        return result;
    }



}
