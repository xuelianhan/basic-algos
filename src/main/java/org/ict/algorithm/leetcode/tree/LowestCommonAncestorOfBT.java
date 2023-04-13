package org.ict.algorithm.leetcode.tree;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 * @author sniper
 * @date 23 Nov, 2022
 * LC236, Medium, frequency=186
 * This problem is the follow-up of {@link LowestCommonAncestorOfBST}
 */
public class LowestCommonAncestorOfBT {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        root.left = p;
        root.right = new TreeNode(1);
        TreeNode pr1 = new TreeNode(2);
        TreeNode pr1r1 = new TreeNode(4);
        pr1.right = pr1r1;
        p.right = pr1;
        LowestCommonAncestorOfBT instance = new LowestCommonAncestorOfBT();
        TreeNode res = instance.lowestCommonAncestor(root, p, q);
        System.out.println(res);
    }


    /**
     * Time Complexity O(N)
     * Space Complexity O(H), H is the height of the binary tree.
     * e.g. root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * Because p and q will exist in the tree, so if we find p and q at the same, then the root is the LCA,
     * If we find p first, so the parent of p is the LCA.
     * If we find q first, so the parent of q is the LCA.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (null != left && null != right) {
            return root;
        }
        return (left == null ? right : left);
    }
}
