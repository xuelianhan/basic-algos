package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 *
 * Example 1:
 *             1
 *            / \
 *           2   3
 *          / \
 *         4   5
 *
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers
 * since per each level it does not matter the order on which elements are returned.
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 * @author sniper
 * @date 22 Apr, 2023
 * LC366, Medium, frequency=88
 */
public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;

        FindLeavesOfBinaryTree instance = new FindLeavesOfBinaryTree();
        List<List<Integer>> res = instance.findLeaves(root);
        System.out.println(res);
    }

    public List<List<Integer>> findLeavesV2(TreeNode root) {
        //todo
        return null;
    }

    public List<List<Integer>> findLeavesV1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();


        return ans;
    }


    /**
     * Understanding the following solution
     * Depth-First-Search Solution
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    /**
     * Depth-First-Search Post-Order-Traversal Solution
     *
     * Utilize the depth to put the node at depth index of array.
     * Each node from the left child node and the right child node separate walk can get two depths,
     * because the condition of becoming a leaf node is the left and right child nodes are empty,
     * so we take the greater value of the left and right child nodes plus 1 for the current node depth value,
     * know the depth value can be added to the node value to the correct location in the result.
     *
     * Time Complexity O(N)
     * Space Complexity O(h), h is the height of the binary tree
     * ------------------------------------------------------
     * C++
     * class Solution {
     * public:
     *     vector<vector<int>> findLeaves(TreeNode* root) {
     *         vector<vector<int>> res;
     *         helper(root, res);
     *         return res;
     *     }
     *     int helper(TreeNode* root, vector<vector<int>>& res) {
     *         if (!root) return -1;
     *         int depth = 1 + max(helper(root->left, res), helper(root->right, res));
     *         if (depth == res.size()) ans.push_back({});
     *         res[depth].push_back(root->val);
     *         return depth;
     *     }
     * };
     * -----------------------------------------------------
     * Depth of root (0-indexed)
     *
     * Example 1:
     *             1
     *            / \
     *           2   3
     *          / \
     *         4   5
     *
     *  helper(1, res)
     *        helper(2, res)
     *              helper(4, res), l:-1, r:-1, depth = 1 + max(l, r) = 0
     *                     res.size() == 0 == depth, res.add(list), res.get(0).add(4), res: [[4]]
     *              helper(5, res), l:-1, r:-1, depth = 1 + max(l, r) = 0
     *                     res.size() == 1, res.get(0).add(5), res:[[4, 5]]
     *              l:0, r:0, depth = 1 + max(l, r) = 1
     *              res.size() == 1 == depth, res.add(list), res.get(1).add(2), res:[[4, 5], [2]]
     *        helper(3, res)
     *              l:-1, r:-1, depth = 1 + max(l, r) = 0, res.size() == 2, res.get(0).add(3), res: [[4, 5, 3], [2]]
     *        l:1, r:0, depth = 1 + max(l, r)=2, res.size() == 2 == depth, res.add(list), res.get(2).add(1), res:[[4, 5, 3], [2], [1]]
     *
     * @param root
     * @param res
     * @return
     */
    private int helper(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        }
        int l = helper(root.left, res);
        int r = helper(root.right, res);
        int depth = 1 + Math.max(l, r);
        if (res.size() == depth) {
            /**
             * Meet the leaf node
             */
            res.add(new ArrayList<>());
        }
        res.get(depth).add(root.val);
        return depth;
    }
}
