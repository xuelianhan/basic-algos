package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * LC145
 */
public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        TreeNode two = new TreeNode(2, null,  null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode one = new TreeNode(1, two, three);
        List<Integer> result = postOrderTraversalV2(one);
        System.out.println(result);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    public void helper(TreeNode node, List<Integer> list) {
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

    public static List<Integer> postOrderTraversalV2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                //left
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            while (cur != null) {
                //right
                stack.push(cur);
                cur = cur.right;
            }
            //middle
            if (cur != null) {
                list.add(cur.val);
            }
        }
        return list;
    }

}
