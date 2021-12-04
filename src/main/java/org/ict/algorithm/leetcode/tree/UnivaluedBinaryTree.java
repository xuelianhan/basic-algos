package org.ict.algorithm.leetcode.tree;


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


    /**
     * Breadth/Depth first search to check
     * if any node's value is different from the root's.
     * @param root
     * @return
     */
    public boolean isUnivalTreeV3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.val != root.val) { return false; }
            if (cur.left != null) { q.offer(cur.left); }
            if (cur.right != null) { q.offer(cur.right); }
        }
        return true;
    }

    /**
     * Check left and right children have the same value as parent.
     * @param root
     * @return
     */
    public boolean isUnivalTreeV2(TreeNode root) {
        return (root.left == null || root.left.val == root.val && isUnivalTree(root.left)) &&
                (root.right == null || root.right.val == root.val && isUnivalTree(root.right));
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
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
