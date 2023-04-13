package org.ict.algorithm.leetcode.tree;


/**
 * Given a binary search tree (BST),
 * find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that
 * has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 *
 * LC235, Medium, frequency=18
 *
 */
public class LowestCommonAncestorOfBST {

    /**
     * def lowestCommonAncestor(self, root, p, q):
     *     while (root.val - p.val) * (root.val - q.val) > 0:
     *         root = (root.left, root.right)[p.val > root.val]
     *     return root
     *
     *
     * @see <p>StefanPochmann's solution</p>
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorV4(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = (root.val < p.val ? root.left : root.right);
        }
        return root;
    }

    /**
     * def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
     *         while root:
     *             minV = min(p.val, q.val)
     *             maxV = max(p.val, q.val)
     *             if minV > root.val:
     *                 root = root.right
     *             elif maxV < root.val:
     *                 root = root.left
     *             else:
     *                 return root
     *         return None
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorV3(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
     *         node = root
     *         while node:
     *             parentVal = node.val
     *             minV = min(p.val, q.val)
     *             maxV = max(p.val, q.val)
     *             if minV > parentVal:
     *                 node = node.right
     *             elif maxV < parentVal:
     *                 node = node.left
     *             else:
     *                 return node
     *         return None
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
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

    /**
     * def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
     *         parentVal = root.val
     *         minV = min(p.val, q.val)
     *         maxV = max(p.val, q.val)
     *         if minV > parentVal:
     *             return self.lowestCommonAncestor(root.right, p, q)
     *         elif maxV < parentVal:
     *             return self.lowestCommonAncestor(root.left, p, q)
     *         else:
     *             return root
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorV1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        int parentVal = root.val;
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        if (min > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (max < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    /**
     * def lowestCommonAncestor(self, root, p, q):
     *     if p.val < root.val > q.val:
     *         return self.lowestCommonAncestor(root.left, p, q)
     *     if p.val > root.val < q.val:
     *         return self.lowestCommonAncestor(root.right, p, q)
     *     return root
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
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
