package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    public void helper(TreeNode node, List<Integer> list) {
        if (null == node) {
            return;
        }
        list.add(node.val);
        if (node.left != null) {
            helper(node.left, list);
        }
        if (node.right != null) {
            helper(node.right, list);
        }
    }

}