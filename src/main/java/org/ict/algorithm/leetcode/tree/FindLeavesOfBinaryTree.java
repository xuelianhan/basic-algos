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
        List<List<Integer>> res = instance.findLeavesV1(root);
        System.out.println(res);
    }

    /**
     * Similar with findLeavesV1, but no need to return node in dfs
     * --------------------------
     * # Definition for a binary tree node.
     * class TreeNode:
     *     def __init__(self, val=0, left=None, right=None):
     *         self.val = val
     *         self.left = left
     *         self.right = right
     * class Solution:
     *     def findLeaves(self, root: TreeNode) -> List[List[int]]:
     *         def dfs(root, prev, t):
     *             if root is None:
     *                 return
     *             if root.left is None and root.right is None:
     *                 t.append(root.val)
     *                 if prev.left == root:
     *                     prev.left = None
     *                 else:
     *                     prev.right = None
     *             dfs(root.left, root, t)
     *             dfs(root.right, root, t)
     *
     *         res = []
     *         prev = TreeNode(left=root)
     *         while prev.left:
     *             t = []
     *             dfs(prev.left, prev, t)
     *             res.append(t)
     *         return res
     * ------------------------
     * Example 1:
     *             1
     *            / \
     *           2   3
     *          / \
     *         4   5
     * @param root
     * @return
     */
    public List<List<Integer>> findLeavesV2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        /**
         * Node-prev is a dummy node.
         */
        TreeNode prev = new TreeNode(0, root, null);
        while (prev.left != null) {
            List<Integer> leaves = new ArrayList<>();
            removeV2(prev.left, prev, leaves);
            res.add(leaves);
        }
        return res;
    }

    private void removeV2(TreeNode root, TreeNode prev, List<Integer> leaves) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
        removeV2(root.left, root, leaves);
        removeV2(root.right, root, leaves);
    }

    /**
     * Understanding the following solution
     *
     * Iterate through the binary tree,
     * find the leaf node, assign it to null,
     * and then add it to the leaves array
     * Example 1:
     *             1
     *            / \
     *           2   3
     *          / \
     *         4   5
     * root != null
     * remove(1, leaves)
     *       remove(2, leaves)
     *             remove(4, leaves), leaves:[4]
     *             node-2.left=null
     *             remove(5, leaves), leaves:[4, 5]
     *             node-2.right=null
     *             return node-2
     *       node-1.left=node-2
     *       remove(3, leaves), leaves:[4, 5, 3]
     *       node-1.right=null
     *       return node-1
     * root = node-1
     * res.add(leaves),res:[[4, 5, 3]]
     *
     * root != null, leaves = new list[]
     * remove(1, leaves)
     *     remove(2, leaves)
     *           leaves.add(2), return null
     *     node-1.left=null
     *     remove(null, leaves), return null
     *     node-1.right=null
     *     return node-1
     * root = node-1
     * res.add(leaves), res:[[4, 5, 3], [2]]
     *
     * root != null, leaves = new list[]
     * remove(1, leaves)
     *       node-1.left == null and node-1.right == null
     *       leaves.add(1), return null
     * root == null, res.add(leaves), res:[[4, 5, 3], [2], [1]]
     * return res
     * @param root
     * @return
     */
    public List<List<Integer>> findLeavesV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = remove(root, leaves);
            res.add(leaves);
        }
        return res;
    }

    private TreeNode remove(TreeNode node, List<Integer> leaves) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return null;
        }
        node.left = remove(node.left, leaves);
        node.right = remove(node.right, leaves);
        return node;
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
        /**
         * The depth of current node is 1 plus the maximum depth of left child and right child.
         */
        int depth = 1 + Math.max(l, r);
        if (res.size() == depth) {
            /**
             * Utilize the depth to put the node at depth index of array.
             * So when depth equals res.size(), we need to add new empty list.
             */
            res.add(new ArrayList<>());
        }
        res.get(depth).add(root.val);
        return depth;
    }
}
