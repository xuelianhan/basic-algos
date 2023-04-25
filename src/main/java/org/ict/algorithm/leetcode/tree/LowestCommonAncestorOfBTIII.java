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
 * "The lowest common ancestor of two nodes p and q in a tree T
 * is the lowest node that has both p and q as descendants
 * (where we allow a node to be a descendant of itself)."
 *                             3
 *                          /     \
 *                        5         1
 *                      /   \      /  \
 *                     6     2    0    8
 *                          /  \
 *                         7    4
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
 * LC1650, Medium, frequency=160
 * Similar with {@link LowestCommonAncestorOfBT} but a little different on the definition of Node.
 *
 */
public class LowestCommonAncestorOfBTIII {

    /**
     * """
     * # Definition for a Node.
     * class Node:
     *     def __init__(self, val):
     *         self.val = val
     *         self.left = None
     *         self.right = None
     *         self.parent = None
     * """
     *
     * class Solution:
     *     def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
     *         a, b = p, q
     *         while a != b:
     *             a = a.parent if a.parent else q
     *             b = b.parent if b.parent else p
     *         return a
     *
     * @param args
     */
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
     * This method is a bit tricky,
     * and most people won’t be able to come up with it in a short amount of time.
     *
     * The idea is as follows:
     * we will use 2 pointers (pointerA, pointerB) that go from nodeA and nodeB upwards respectively.
     * Assume nodeA locates at a shallower level than nodeB,
     * i.e. depth(nodeA) < depth(nodeB), pointerA will reach the top quicker than pointerB.
     * Suppose the difference in depth between nodeA and nodeB is diff,
     * by the time pointerA reaches the top, pointerB will be diff levels behind.
     * Now if pointerA resets its path and continues upwards from nodeB instead of nodeA,
     * it will need diff steps to reach the level of nodeA,
     * by which time pointerB has already caught up and will be at the same level of pointerA
     * (pointerB restarts from nodeA after reaching the top).
     * Now the only thing to do is to compare pointerA and pointerB on the way up.
     * If pointerA and pointerB points to the same node, we’ve found the lowest common ancestor.
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * e.g.root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     *                             3
     *                          /     \
     *                        5         1
     *                      /   \      /  \
     *                     6     2    0    8
     *                          /  \
     *                         7    4
     * p:5, q:4
     * a:p, b:q,
     * a != b, a = a.parent, a:3, b = b.parent, b:2
     * a != b, a = a.parent, a:null, b = b.parent, b:5
     * a != b, a == null, a = q, a:4, b = b.parent, b:3
     * a != b, a = a.parent, a:2, b = b.parent, b:null
     * a != b, a = a.parent, a:5, b == null, b = p, b:5
     * a == b, a:5, b:5
     * return a:5
     *
     * a: 5-->3-->null-->4-->2   -->5
     * b: 4-->2--> 5  -->3-->null-->5
     * You can imagine this as a cycle.
     *
     * This solution is same as LC160
     * @see org.ict.algorithm.leetcode.linkedlist.IntersectionOfTwoLinkedLists
     * @see <a href="https://iamageek.medium.com/leetcode-1650-lowest-common-ancestor-of-a-binary-tree-iii-6d008b93376c"></a>
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
     * One way to solve this problem is to traverse from one of the nodes all the way up to the top,
     * and save all the ancestors in a hash set.
     * Afterwards, we will go from the other node to the top of the tree,
     * and return the node if it’s already in the hash set.
     * If so, we have found the lowest common ancestor.
     * Since we will go from nodeA to the root once and also from nodeB upwards once,
     * the time complexity is O(N), N being the number of nodes in the tree.
     * This is because the tree can be skewed if we’re out of luck.
     * And as we need to store the list of ancestors,
     * the space complexity is also O(N).
     *
     * @see <a href="https://iamageek.medium.com/leetcode-1650-lowest-common-ancestor-of-a-binary-tree-iii-6d008b93376c"></a>
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

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

    }
}
