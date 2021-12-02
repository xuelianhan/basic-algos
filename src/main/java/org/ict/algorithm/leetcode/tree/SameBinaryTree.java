package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author sniper
 * LC100
 */
public class SameBinaryTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        }
        if (null == p || null == q) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static boolean isSameTreeV2(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        }
        if (null == p || null == q) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        if (p != null && q != null) {
            queue.offer(p);
            queue.offer(q);
        }
        if (p.val != q.val) {
            return false;
        }
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (null == first && null == second) {
                continue;
            }
            if (null == first || null == second) {
                return false;
            }
            if (first.val != second.val) {
                return false;
            }
            queue.offer(first.left);
            queue.offer(second.left);
            queue.offer(first.right);
            queue.offer(second.right);
        }
        return true;
    }
}
