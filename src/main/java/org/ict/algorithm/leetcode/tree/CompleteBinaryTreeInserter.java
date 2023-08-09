package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * A complete binary tree is a binary tree in which every level,
 * except possibly the last, is completely filled,
 * and all nodes are as far left as possible.
 *
 * Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
 *
 * Implement the CBTInserter class:
 *
 * CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete,
 * and returns the value of the parent of the inserted TreeNode.
 * TreeNode get_root() Returns the root node of the tree.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * Output
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * Explanation
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // return 1
 * cBTInserter.insert(4);  // return 2
 * cBTInserter.get_root(); // return [1, 2, 3, 4]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [1, 1000].
 * 0 <= Node.val <= 5000
 * root is a complete binary tree.
 * 0 <= val <= 5000
 * At most 104 calls will be made to insert and get_root.
 * @author sniper
 * @date 08 Aug 2023
 * LC919, Medium
 */
public class CompleteBinaryTreeInserter {

    /**
     * Time Cost 14ms
     */
    class CBTInserter {

        private List<TreeNode> tree = new ArrayList<>();

        /**
         * Construct Complete Binary Tree by level order.
         * new CBTInserter([1, 2]);
         *     1
         *    /
         *  2
         * @param root
         */
        public CBTInserter(TreeNode root) {
            tree.add(root);
            for (int i = 0; i < tree.size(); i++) {
                TreeNode node = tree.get(i);
                if (node.left != null) {
                    tree.add(node.left);
                }
                if (node.right != null) {
                    tree.add(node.right);
                }
            }
        }

        /**
         * insert(4):
         *          1
         *         / \
         *        2   3
         *       /
         *      4
         *
         * @param val
         * @return
         */
        public int insert(int val) {
            final int n = tree.size();
            TreeNode node = new TreeNode(val);
            /**
             * Notice (n - 1) / 2 here
             */
            TreeNode parent = tree.get((n - 1) / 2);
            tree.add(node);
            /**
             * Notice n % 2 == 1
             */
            if (n % 2 == 1) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return tree.get(0);
        }
    }
    /**
     * Your CBTInserter object will be instantiated and called as such:
     * CBTInserter obj = new CBTInserter(root);
     * int param_1 = obj.insert(val);
     * TreeNode param_2 = obj.get_root();
     */

}
