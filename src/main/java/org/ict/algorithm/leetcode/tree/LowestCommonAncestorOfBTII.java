package org.ict.algorithm.leetcode.tree;

import java.util.Arrays;

/**
 * Description
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
 * If either node p or q does not exist in the tree, return null.
 * All values of the nodes in the tree are unique.
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node
 * that has both p and q as descendants (where we allow a node to be a descendant of itself)”.
 * A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5.
 * A node can be a descendant of itself according to the definition of LCA.
 *
 * Example 3:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 *
 * Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
 *
 * @author sniper
 * @date 12 Apr, 2023
 * LC1644, Medium
 * Similar with {@link LowestCommonAncestorOfBT} but a little different point:
 * p and q will not always exist in the tree.
 * Amazon
 */
public class LowestCommonAncestorOfBTII {

    public static void main(String[] args) {
        /**
         *                   3
         *                5     1
         *                  2
         *                    4
         *
         */
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        root.left = p;
        root.right = new TreeNode(1);

        TreeNode pr1 = new TreeNode(2);
        TreeNode pr1r1 = new TreeNode(4);
        pr1.right = pr1r1;
        p.right = pr1;

        //TreeNode q = pr1r1;
        TreeNode q = new TreeNode(8);

        LowestCommonAncestorOfBTII instance = new LowestCommonAncestorOfBTII();
        TreeNode res = instance.lowestCommonAncestor(root, p, q);
        System.out.println(res == null ? null : res.val);
    }

    /**
     * Notice that p and q will not always exist in the tree.
     * This problem is similar to problem #236,
     * with the difference that node p and node q are not always in the binary tree.
     * First do depth first search on the binary tree to find the nodes p and q.
     * If either node does not exist, return null.
     * If both p and q are in the binary tree,
     * then do depth first search again to find the lowest common ancestor.
     * --------------------------------------------------------------------
     * Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
     * If both nodes are in the tree, refer to problem 236-Lowest-Common-Ancestor-of-a-Binary-Tree.
     * During DFS, if the root is equal to p or q,
     * it cannot immediately return the root like in problem 236-Lowest-Common-Ancestor-of-a-Binary-Tree,
     * because we cannot determine if the other node is in the tree.
     * Therefore, our solution is to adopt post-order traversal to ensure that each node is visited.
     * The logic is then the same as when both nodes are in the tree.
     * Additionally, when we encounter p or q during the search,
     * we keep a count.
     * Finally, if the count equals 2,
     * it means that both nodes have been found, and we can return the answer.
     *
     * @see <a href="https://leetcode.ca/2020-05-31-1644-Lowest-Common-Ancestor-of-a-Binary-Tree-II/"></a>
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int[] count = new int[1];
        TreeNode res = helper(root, p, q, count);
        System.out.println("count:" + Arrays.toString(count));
        return count[0] == 2 ? res : null;
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q, int[] count) {
        if (root == null) {
            return null;
        }
        TreeNode left = helper(root.left, p, q, count);
        TreeNode right = helper(root.right, p, q, count);

        if (root == p || root == q) {
            count[0] += 1;
            return root;
        }

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
