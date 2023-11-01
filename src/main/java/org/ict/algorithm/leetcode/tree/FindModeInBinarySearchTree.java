package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary search tree (BST) with duplicates,
 * return all the mode(s) (i.e., the most frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: root = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 *
 *
 * Follow up: Could you do that without using any extra space?
 * (Assume that the implicit stack space incurred due to recursion does not count).
 * @author sniper
 * @date 01 Nov 2023
 * LC501, Easy
 */
public class FindModeInBinarySearchTree {

    private Integer prev;

    private int maxCount = 0;

    private int curCount = 1;

    private List<Integer> result = new ArrayList<>();

    /**
     * Time Cost 0ms
     * @param root
     * @return
     */
    public int[] findModeV1(TreeNode root) {
        dfs(root);
        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    /**
     * Time Cost 5ms
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        int[] max = new int[1];
        Map<Integer, Integer> freq = new HashMap<>();
        inOrder(root, freq, max);
        freq.forEach((k, v) -> {
            if (v == max[0]) {
                vals.add(k);
            }
        });
        // Or using is OK too:
        // return vals.stream().mapToInt(Integer::intValue).toArray();
        return vals.stream().mapToInt(i -> i).toArray();
    }

    private void inOrder(TreeNode root, Map<Integer, Integer> freq, int[] max) {
        if (root == null) {
            return;
        }
        inOrder(root.left, freq, max);
        freq.put(root.val, freq.getOrDefault(root.val, 0) + 1);
        max[0] = Math.max(max[0], freq.get(root.val));
        inOrder(root.right, freq, max);
    }

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
