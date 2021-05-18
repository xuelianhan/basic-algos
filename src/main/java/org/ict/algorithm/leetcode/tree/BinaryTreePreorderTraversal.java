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

    }

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

    public static List<Integer> preorderTraversalV2(TreeNode root) {
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
                stack.push(cur.right);
                //left
                stack.push(cur.left);
            }
        }
        return list;
    }

    public List<Integer> preorderTraversalV3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return list;
    }

}