package org.ict.algorithm.leetcode.tree;

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

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (null == root || low > high) {
            return 0;
        }
        List<Integer> result = inOrder(root, low, high);
        if (null == result) {
            return 0;
        }
        return result.stream().mapToInt(i -> i).sum();
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
}
