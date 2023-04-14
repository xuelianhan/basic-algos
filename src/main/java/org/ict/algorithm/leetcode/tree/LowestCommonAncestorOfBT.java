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
     * We can seem there are two boxs before us, our target is to find dish-p and dish q in the two boxs
     * from the top-down view. At each time, we choose two of the boxs and find the dishes numbered with p or q.
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
     * Iterative-Solution with Queue(BFS)
     * Note:
     * All Node.val are unique.
     * p != q
     * p and q will exist in the tree.
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
     * Iterative-Solution with Stack(DFS)
     * Note:
     * All Node.val are unique.
     * p != q
     * p and q will exist in the tree.
     *
     * @param root
     * @param p
     * @param q
     * @return
     * @author dietpepsi
     * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/65236/java-python-iterative-solution"></a>
     */
    public TreeNode lowestCommonAncestorV0(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
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
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}
