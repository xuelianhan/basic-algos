package org.ict.algorithm.leetcode.tree;

import java.util.*;

public class BinaryTreeAllOrderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                result.add(cur.val);  // Add before going to children
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                cur = node.right;
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                result.add(node.val);  // Add after all left children
                cur = node.right;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                result.addFirst(cur.val);  // Reverse the process of preorder
                cur = cur.right;           // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                cur = node.left;           // Reverse the process of preorder
            }
        }
        return result;
    }
}
