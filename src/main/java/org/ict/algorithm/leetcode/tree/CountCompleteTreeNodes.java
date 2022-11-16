package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last,
 * is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * @author sniper
 * @date 16 Nov, 2022
 * LC222
 */
public class CountCompleteTreeNodes {

    /**
     * A Complete Binary Tree （CBT) is a binary tree in which every level, except possibly the last,
     * is completely filled, and all nodes are as far leftmost as possible.
     *           1     height=0
     *         /  \
     *        2    3   height=1
     *       / \
     *      4  5       height=2
     *
     * A Perfect Binary Tree(PBT) is a tree with all leaf nodes at the same depth.
     * All internal nodes have degree 2.
     *          1
     *         / \
     *        2   3
     *       /\   /\
     *      4 5  6 7
     *
     * A Full Binary Tree (FBT) is a tree in which every node other than the leaves has two children.
     *
     * The height of a tree can be found by just going left.
     * Let a single node tree have height 0. Find the height h of the whole tree.
     * If the whole tree is empty, i.e., has height -1, there are 0 nodes.
     *
     * Otherwise, check whether the height of the right subtree is just one less than that of the whole tree,
     * meaning left and right subtree have the same height.
     *
     * If yes, then the last node on the last tree row is in the right sub-tree, and the left subtree is a full tree of height h-1.
     * So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
     * If no, then the last node on the last tree row is in the left sub-tree, and the right subtree is a full tree of height h-2.
     * So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
     * Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)).
     * So overall O(log(n)^2).
     *
     *          1     height=0
     *        /  \
     *       2    3   height=1
     *      / \
     *     4  5       height=2
     *
     *         1
     *        / \
     *       2   3
     *      /\   /\
     *     4 5  6  7
     *    /\
     *   8 9
     *
     * @author StefanPochmann
     * @see <a href="https://leetcode.com/problems/count-complete-tree-nodes/solutions/61958/concise-java-solutions-o-log-n-2"></a>
     * @param root
     * @return
     */
    public int countNodesV3(TreeNode root) {
        int h = height(root);
        if (h < 0) {
            return 0;
        }
        int rightTreeHeight = height(root.right);
        /**
         * If rightTreeHeight is h-1, then the left-tree is perfect-binary-tree.
         * the number of left-tree-nodes is 2^h-1, plus the current node, the result is (1 << h)
         * the
         */
        if (rightTreeHeight == h - 1) {
            int leftAndRootCnt = (1 << h);
            return leftAndRootCnt + countNodesV3(root.right);
        }
        /**
         * todo
         * why?
         * If rightTreeHight is not h-1, it means the right-sub-tree is perfect tree with height of h-2.
         * So the total number of nodes is 2^(h-1) - 1, plus the root node ,the result is (1 <<(h-1)).
         */
        int rightSubTreeCnt = (1 << (h - 1));
        return rightSubTreeCnt + countNodesV3(root.left);
    }

    public int height(TreeNode root) {
        return root == null ? -1 : 1  + height(root.left);
    }


    public int countNodesV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftPathLength = 0, rightPathLength = 0;
        TreeNode left = root;
        TreeNode right = root;
        while (left != null) {
            leftPathLength++;
            left = left.left;
        }
        while (right != null) {
            rightPathLength++;
            right = right.right;
        }
        /**
         * If both length of path for left-tree and right-tree are equals.
         * This means that the tree is a perfect-full-binary-tree.
         * we can use formula to calculate the nodes: 2^h - 1, h is the height of the tree.
         */
        if (leftPathLength == rightPathLength) {
            return (int)Math.pow(2, leftPathLength) - 1;
        }
        return countNodesV2(root.left) + countNodesV2(root.right) + 1;
    }

    /**
     * Breadth-First-Search Solution
     * Time Complexity: O(N)
     * @param root
     * @return
     */
    public int countNodesV1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            result += size;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return result;
    }


    /**
     * Recursive-Count-Solution
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return (1 + countNodes(root.left) + countNodes(root.right));
    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
