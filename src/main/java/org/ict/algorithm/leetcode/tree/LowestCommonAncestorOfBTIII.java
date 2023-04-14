package org.ict.algorithm.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * Each node will have a reference to its parent node. The definition for Node is below:
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 *
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants
 * (where we allow a node to be a descendant of itself)."
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
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
 * p and q exist in the tree.
 *
 * @author sniper
 * @date 12 Apr, 2023
 * LC1650, Medium
 */
public class LowestCommonAncestorOfBTIII {

    public static void main(String[] args) {
        Node root = new Node(3);
        Node p = new Node(5);
        Node q = new Node(1);
        root.left = p;
        root.right = q;
        p.parent = root;
        q.parent = root;

        LowestCommonAncestorOfBTIII instance = new LowestCommonAncestorOfBTIII();
        Node res = instance.lowestCommonAncestorV1(p, q);
        System.out.println(res == null ? null : res.val);
    }

    /**
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @param p
     * @param q
     * @return
     */
    public Node lowestCommonAncestorV1(Node p, Node  q) {
        Node a = p;
        Node b = q;
        while (a != b) {
            a = (a == null ? q : a.parent);
            b = (b == null ? p : b.parent);
        }
        return a;
    }

    /**
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @param p
     * @param q
     * @return
     */
    public Node lowestCommonAncestor(Node p, Node  q) {
        Set<Node> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = p.parent;
        }
        while (q != null) {
            if (ancestors.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node() {

        }
        public Node(int val) {
            this.val = val;
        }

    }
}
