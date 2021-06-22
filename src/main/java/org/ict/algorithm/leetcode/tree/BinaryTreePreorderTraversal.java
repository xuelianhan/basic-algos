package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hxl
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode two = new TreeNode(2, null,  null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode one = new TreeNode(1, two, three);
        System.out.println(preOrderTraversalV3(one));
    }

    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    public static void helper(TreeNode node, List<Integer> list) {
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

    /**
     * root-->left-->right
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversalV2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                //middle
                list.add(cur.val);
                //right
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                //left
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return list;
    }

    /**
     * Recommend version
     * root-->left-->right
     * Recommend version
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversalV3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur);
                // Add before going to children
                list.add(cur.val);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return list;
    }

}