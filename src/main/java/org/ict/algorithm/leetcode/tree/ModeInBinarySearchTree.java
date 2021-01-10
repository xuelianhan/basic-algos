package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Note: If a tree has more than one mode, you can return them in any order.
 *
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 *
 * you can google "mode", and then you can see like this
 *
 * 3. STATISTICS
 * the value that occurs most frequently in a given set of data.
 *
 * LC501
 */
public class ModeInBinarySearchTree {

    private Integer prev;

    private int maxCount = 0;

    private int curCount = 1;

    private List<Integer> result = new ArrayList<>();


    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    /**
     * Inorder traversal of a BST will find the nodes in ascending order.
     * So just compare the current node to the previous, and if they match,
     * increase the current count of duplicate values by 1.
     * If they don't match, reset the current count to 1.
     * Compare the current count to the max count found so far.
     * If they match, append the current value to the result list.
     * If the current count of duplicates exceeds the max count,
     * create a new result list with just the current value.
     */
    private void dfs(TreeNode node) {
        if (null == node) {
            return;
        }
        dfs(node.left);
        if (prev != null) {
            if (node.val == prev) {
                curCount++;
            } else {
                curCount=1;
            }
        }
        if (curCount > maxCount) {
            maxCount = curCount;
            result.clear();
            result.add(node.val);
        } else if (curCount == maxCount) {
            result.add(node.val);
        }
        prev = node.val;
        dfs(node.right);
    }
}
