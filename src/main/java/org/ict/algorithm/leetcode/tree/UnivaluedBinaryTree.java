package org.ict.algorithm.leetcode.tree;

import org.ict.algorithm.leetcode.breadthfirstsearch.BinaryTreeLevelOrderTraversal;

import java.util.*;

/**
 * A binary tree is uni-valued if every node in the tree has the same value.
 *
 * Given the root of a binary tree,
 * return true if the given tree is uni-valued, or false otherwise.
 * @author sniper
 * @date 2021/12/1
 * LC965
 */
public class UnivaluedBinaryTree {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            /* queue size indicates number of nodes at each level */
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                Integer frequency = map.get(cur.val);
                if (null == frequency) {
                    map.put(cur.val, 1);
                } else {
                    map.put(cur.val, frequency + 1);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return map.size() == 1;
    }
}
