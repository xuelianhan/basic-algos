package org.ict.algorithm.leetcode.binarysearchtree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given the root node of a binary search tree and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 * @author sniper
 * @date 2021/12/1
 * LC938
 */
public class RangeSumOfBST {


    /**
     * Time cost 0ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV2(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        /**
         * Three situations:
         * 1.root.val < low < high, find in right part of the bst.
         * 2.low < high < root.val, find in the left part of the bst.
         * 3.low <= root.val <= high, find in both left and right part of the bst.
         */
        if (root.val < low) {
            return rangeSumBSTV2(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBSTV2(root.left, low, high);
        }
        return root.val + rangeSumBSTV2(root.left, low, high) + rangeSumBSTV2(root.right, low, high);
    }

    /**
     * Time cost 26ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV1(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        if (root.val < low) {
            List<Integer> result = inOrder(root.right, low, high);
            return sumList(result);
        }
        if (root.val > high) {
            List<Integer> result = inOrder(root.left, low, high);
            return sumList(result);
        }
        List<Integer> list1 = inOrder(root.left, low, high);
        List<Integer> list2 = inOrder(root.right, low, high);
        return root.val + sumList(list1) + sumList(list2);
    }

    /**
     * Time cost 15ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (null == root || low > high) {
            return 0;
        }
        List<Integer> result = inOrder(root, low, high);
        return sumList(result);
    }

    public List<Integer> inOrder(TreeNode root, int low, int high) {
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
                if (node.val >= low && node.val <= high) {
                    result.add(node.val);
                }
                cur = node.right;
            }
        }
        return result;
    }

    public int sumList(List<Integer> result) {
        if (null == result) {
            return 0;
        }
        return result.stream().mapToInt(i -> i).sum();
    }

}
