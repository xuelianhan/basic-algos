package org.ict.algorithm.leetcode.backtrack;

import org.ict.algorithm.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
     * Breadth-First-Search Solution
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSumV2(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, targetSum, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Tuple t = queue.poll();
            t.path.add(t.node.val);
            if (t.sum - t.node.val == 0 && t.node.left == null && t.node.right == null) {
                result.add(t.path);
            }
            if (t.node.left != null) {
                queue.offer(new Tuple(t.node.left, t.sum - t.node.val, new ArrayList<>(t.path)));
            }
            if (t.node.right != null) {
                queue.offer(new Tuple(t.node.right, t.sum - t.node.val, new ArrayList<>(t.path)));
            }
        }
        return result;
    }


    static class Tuple {
        TreeNode node;
        int sum;

        List<Integer> path;

        public Tuple(TreeNode node, int sum, List<Integer> path) {
            this.node = node;
            this.sum = sum;
            this.path = path;
        }
    }

    /**
     * Backtracking Solution
     * Time Complexity O(N*logN)
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        //backtrack(root, targetSum, new LinkedList<>(), result);
        //backtrackV1(root, targetSum, new LinkedList<>(), result);
        backtrackV2(root, targetSum, new LinkedList<>(), result);
        return result;
    }

    private void backtrackV2(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        path.add(root.val);
        if (0 == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
        }
        backtrackV2(root.left, sum, path, result);
        backtrackV2(root.right, sum, path, result);
        path.removeLast();
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
