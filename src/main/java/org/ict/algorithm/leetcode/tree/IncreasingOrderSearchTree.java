package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * Given the root of a binary search tree,
 * rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
 * and every node has no left child and only one right child.
 * @author sniper
 * @date 2021/12/1
 * LC897
 */
public class IncreasingOrderSearchTree {

    public TreeNode increasingBST(TreeNode root) {
        if (null == root) {
            return null;
        }
        List<Integer> list = inOrder(root);
        TreeNode cur = null;
        TreeNode pre = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            cur = new TreeNode();
            cur.val = list.get(i);
            cur.right = pre;
            pre = cur;
        }
        return cur;
    }


    public List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                // Add after all left children
                result.add(node.val);
                cur = node.right;
            }
        }
        return result;
    }
}
