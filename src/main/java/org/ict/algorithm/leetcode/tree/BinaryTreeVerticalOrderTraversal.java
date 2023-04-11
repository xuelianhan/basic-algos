package org.ict.algorithm.leetcode.tree;


import java.util.*;

/**
 * Given a binary tree, return the  vertical order  traversal of its nodes’ values.
 * (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 * Examples 1:
 * Input: [3,9,20,null,null,15,7]
 *    3
 *   / \
 *  /   \
 * 9     20
 *      / \
 *     /   \
 *    15    7
 *
 * Output:
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 *
 * Examples 2:
 * Input: [3,9,8,4,0,1,7]
 *
 *      3
 *     / \
 *    /   \
 *    9    8
 *   /\   /\
 *  /  \ /  \
 *  4  01    7
 * Output:
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 * Examples 3:
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *      3
 *     / \
 *    /   \
 *    9    8
 *   / \  / \
 *  /   \/   \
 *  4   01    7
 *      /\
 *     /  \
 *    5    2
 * Output:
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 * @author sniper
 * @date 11 Apr, 2023
 * LC314, Medium, frequency=188
 * Google
 * Similar Problem
 * @see VerticalOrderTraversalOfBinaryTree
 */
public class BinaryTreeVerticalOrderTraversal {

    public static void main(String[] args) {
        BinaryTreeVerticalOrderTraversal instance = new BinaryTreeVerticalOrderTraversal();
        TreeNode root = new TreeNode(3);
        TreeNode l1 = new TreeNode(9);
        TreeNode r1 = new TreeNode(20);
        TreeNode rl1 = new TreeNode(15);
        TreeNode rr1 = new TreeNode(7);
        r1.left = rl1;
        r1.right = rr1;
        root.left = l1;
        root.right = r1;
        List<List<Integer>>  res = instance.verticalOrder(root);
        System.out.println(res);
    }


    /**
     * @see <a href="https://walkccc.me/LeetCode/problems/0314/"></a>
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrderV2(TreeNode root) {
        //todo
        return null;
    }

    /**
     *
     * @see <a href="https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleMedium/314.html"></a>
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrderV1(TreeNode root) {
        //todo
        return null;
    }

    /**
     * e.g. root = [3,9,20,null,null,15,7], expected:[[9], [3,15], [20], [7]]
     *    3
     *   / \
     *  /   \
     * 9     20
     *      / \
     *     /   \
     *    15    7
     *
     *
     * @see <a href="https://grandyang.com/leetcode/314/"></a>
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeMap<Integer, List<Integer>> dict = new TreeMap<>();

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, root));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (!dict.containsKey(cur.getSeq())) {
                dict.put(cur.getSeq(), new ArrayList<>());
            }
            dict.get(cur.getSeq()).add(cur.getNode().val);
            if (cur.getNode().left != null) {
                Pair p = new Pair(cur.getSeq() - 1, cur.getNode().left);
                queue.offer(p);
            }
            if (cur.getNode().right != null) {
                Pair p = new Pair(cur.getSeq() + 1, cur.getNode().right);
                queue.offer(p);
            }
        }
        res.addAll(dict.values());
        return res;
    }

    static class Pair {
        private Integer seq;

        private TreeNode node;

        public Pair() {}

        public Pair(Integer seq, TreeNode node) {
            this.seq = seq;
            this.node = node;
        }

        public Integer getSeq() {
            return seq;
        }
        public TreeNode getNode() {
            return node;
        }
    }
}
