package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 * A leaf is a node with no children.
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * @author sniper
 * @date 2021/12/2
 * LC113, Medium
 */
public class PathSumTwo {

    /**
     * Backtracking Solution
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackV1(root, targetSum, new LinkedList<>(), result);
        return result;
    }

    private void backtrackV1(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (root.val == sum && root.left == null && root.right == null) {
            path.add(root.val);
            result.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        path.add(root.val);
        backtrackV1(root.left, sum - root.val, path, result);
        backtrackV1(root.right, sum - root.val, path, result);
        path.removeLast();
    }

    private void backtrack(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
        }
        backtrack(root.left, sum - root.val, path, result);
        backtrack(root.right, sum - root.val, path, result);
        path.removeLast();
    }
}
