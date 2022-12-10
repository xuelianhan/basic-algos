package org.ict.algorithm.leetcode.tree;

import java.util.*;

public class BinaryTreeAllOrderTraversal {

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                // Add before going to children
                result.add(cur.val);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                cur = node.right;
            }
        }
        return result;
    }

    public List<Integer> inOrderTraversal(TreeNode root) {
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

    public List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                // Reverse the process of preorder
                result.addFirst(cur.val);
                // Reverse the process of preorder
                cur = cur.right;
            } else {
                TreeNode node = stack.pop();
                // Reverse the process of preorder
                cur = node.left;
            }
        }
        return result;
    }
}
