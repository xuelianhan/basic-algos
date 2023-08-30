package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 *
 * @author sniper
 * @date 23 Nov, 2022
 * LC236, Medium, frequency=186
 * This problem is the follow-up of {@link LowestCommonAncestorOfBST}
 * Amazon
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
     *
     * e.g. root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * Because p and q will exist in the tree, so if we find p and q at the same, then the root is the LCA,
     * If we find p first, so the parent of p is the LCA.
     * If we find q first, so the parent of q is the LCA.
     * We can seem there are two boxs before us, our target is to find dish-p and dish-q in the two boxs
     * from the top-down view.
     * At each time, we choose two of the boxs and find the dishes numbered with p or q.
     *
     * def lowestCommonAncestor(self, root, p, q):
     *     if root in (None, p, q): return root
     *     left, right = (self.lowestCommonAncestor(kid, p, q)
     *                    for kid in (root.left, root.right))
     *     return root if left and right else left or right
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

    /**
     * Not recommend.
     * Iterative-Solution with Queue(BFS)
     * Note:
     * All Node.val are unique.
     * p != q
     * p and q will exist in the tree.
     * ---------------------------
     * Python-Queue solution occurs Time limited Exception. Why?
     * Bard's answer:
     * The bug in the Python code is that it is using a breadth-first search (BFS) to
     * find the lowest common ancestor (LCA) of two nodes in a binary tree.
     * BFS is a good algorithm for finding the LCA of two nodes in a balanced binary tree,
     * but it is not a good algorithm for finding the LCA of two nodes in an unbalanced binary tree.
     * In an unbalanced binary tree, the LCA of two nodes may be far away from the root of the tree.
     * This means that a BFS will have to visit many nodes in the tree before it finds the LCA.
     * This can take a long time, especially for large binary trees.
     *
     * A better algorithm for finding the LCA of two nodes in an unbalanced binary tree is a depth-first search (DFS).
     * DFS is a recursive algorithm that starts at the root of the tree and explores the tree in a depth-first manner.
     * DFS will eventually reach the LCA of two nodes, regardless of whether the tree is balanced or unbalanced.
     * The following is a Python implementation of the DFS algorithm for finding the LCA of two nodes in a binary tree:
     * -------------------------------------------
     * ef lowest_common_ancestor(root, p, q):
     *     if root is None:
     *         return None
     *
     *     if root == p or root == q:
     *         return root
     *
     *     left_lca = lowest_common_ancestor(root.left, p, q)
     *     right_lca = lowest_common_ancestor(root.right, p, q)
     *
     *     if left_lca is not None and right_lca is not None:
     *         return root
     *
     *     return left_lca if left_lca is not None else right_lca
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorV1(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root, null);
        queue.offer(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    parent.put(cur.left, cur);
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    parent.put(cur.right, cur);
                    queue.offer(cur.right);
                }
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }


    /**
     * Understanding the following solution
     *
     * Iterative-Solution with Stack(DFS)
     * Note:
     * All Node.val are unique.
     * p != q
     * p and q will exist in the tree.
     * -----------------------------------
     * # Definition for a binary tree node.
     * # class TreeNode:
     * #     def __init__(self, x):
     * #         self.val = x
     * #         self.left = None
     * #         self.right = None
     *
     * class Solution:
     *     def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
     *         stack = [root]
     *         parent = {root: None}
     *         while p not in parent or q not in parent:
     *             node = stack.pop()
     *             if node.left:
     *                 parent[node.left] = node
     *                 stack.append(node.left)
     *             if node.right:
     *                 parent[node.right] = node
     *                 stack.append(node.right)
     *         ancestors = set()
     *         while p:
     *             ancestors.add(p)
     *             p = parent[p]
     *         while q not in ancestors:
     *             q = parent[q]
     *         return q
     *--------------------------------------
     * @param root
     * @param p
     * @param q
     * @return
     * @author dietpepsi
     * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/65236/java-python-iterative-solution"></a>
     */
    public TreeNode lowestCommonAncestorV0(TreeNode root, TreeNode p, TreeNode q) {
        /**
         * parent content:(child, parent)
         */
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        /**
         * Very important, root's parent is null.
         * this will be used as terminated condition in the second while-loop.
         */
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            /**
             * Add current p firstly, then go up to parent.
             */
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}
