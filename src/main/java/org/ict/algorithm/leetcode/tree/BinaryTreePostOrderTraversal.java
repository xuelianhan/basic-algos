package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * LC145
 */
public class BinaryTreePostOrderTraversal {

    public static void main(String[] args) {
        TreeNode two = new TreeNode(2, null,  null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode one = new TreeNode(1, two, three);
        List<Integer> result = postOrderTraversalV3(one);
        System.out.println(result);
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    public static void helper(TreeNode node, List<Integer> list) {
        if (null == node) {
            return;
        }
        if (node.left != null) {
            helper(node.left, list);
        }
        if (node.right != null) {
            helper(node.right, list);
        }
        list.add(node.val);
    }

    /**
     * Recommend version
     * Left-->Right-->Root
     * Recommend version
     * @param root
     * @return
     */
    public static List<Integer> postOrderTraversalV2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur);
                // Add before going to children
                // Reverse the process of preorder
                list.addFirst(cur.val);
                // Reverse the process of preorder
                cur = cur.right;
            } else {
                TreeNode node = stack.pop();
                // Reverse the process of preorder
                cur = node.left;
            }
        }
        return list;
    }

    public static List<Integer> postOrderTraversalV3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !visited.contains(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !visited.contains(node.right)) {
                stack.push(node.right);
            } else {
                TreeNode cur = stack.pop();
                visited.add(cur);
                list.add(cur.val);
            }
        }
        return list;
    }

}
