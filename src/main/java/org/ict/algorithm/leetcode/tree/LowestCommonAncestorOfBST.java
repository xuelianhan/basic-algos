package org.ict.algorithm.leetcode.tree;


/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that
 * has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 *
 * LC235
 *
 */
public class LowestCommonAncestorOfBST {

    /**
     * @see <p>StefanPochmann's solution</p>
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lcaEnhanced(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = (root.val < p.val ? root.left : root.right);
        }
        return root;
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            int parentVal = node.val;
            if (p.val > parentVal && q.val > parentVal) {
                node = node.right;
            } else if (p.val < parentVal && q.val < parentVal) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        if (p.val > parentVal && q.val > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < parentVal && p.val < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

}
