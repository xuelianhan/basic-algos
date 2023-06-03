package org.ict.algorithm.leetcode.tree;


import java.util.*;

/**
 * Given a binary tree, return the  vertical order  traversal of its nodes’ values.
 * (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column,
 * the order should be from left to right.
 * 
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
        List<List<Integer>>  res = instance.verticalOrderV1(root);
        System.out.println(res);
    }


    /**
     * Level-Order-Traversal(TreeMap + Queue)
     * @see <a href="https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleMedium/314.html"></a>
     * @see <a href="https://walkccc.me/LeetCode/problems/0314/"></a>
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrderV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> dict = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> seqQueue = new LinkedList<>();
        nodeQueue.offer(root);
        seqQueue.offer(0);

        while (!nodeQueue.isEmpty()) {
            /**
             * The seq and the TreeNode-cur make a pair.
             */
            TreeNode cur = nodeQueue.poll();
            int seq = seqQueue.poll();
            if (!dict.containsKey(seq)) {
                dict.put(seq, new ArrayList<>());
            }
            dict.get(seq).add(cur.val);

            if (cur.left != null) {
                nodeQueue.offer(cur.left);
                seqQueue.offer(seq - 1);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
                seqQueue.offer(seq + 1);
            }
        }
        res.addAll(dict.values());
        return res;
    }

    /**
     * Understanding the following solution
     * Level-Order-Traversal(Queue+TreeMap)
     *
     * e.g. root = [3,9,20,null,null,15,7], expected:[[9], [3,15], [20], [7]]
     *    3
     *   / \
     *  /   \
     * 9     20
     *      / \
     *     /   \
     *    15    7
     * queue:[(0, Node-3)], dict:{}
     * poll cur: (0, Node-3), dict not contains 0, dict:{{0, [3]}}, queue:[]
     * offer cur.Node.left:Node-9, queue:[(-1, Node-9)]
     * offer cur.Node.right:Node-20, queue:[(-1, Node-9), (1, Node-20)]
     * poll cur:(-1, Node-9), queue:[(1, Node-20)], dict not contains -1, dict:{{-1, [9]}, {0, [3]}}
     * Node-9.left is null, Node-9.right is null, queue:[(1, Node-20)]
     * poll cur:(1, Node-20), queue:[], dict not contains 1, dict:{{-1, [9]}, {0, [3]}, {1, [20]}}
     * offer cur.Node.left:Node-15, queue:[(0, Node-15)]
     * offer cur.Node.right:Node-7, queue:[(0, Node-15), (2, Node-7)]
     * poll cur:(0, Node-15),  queue:[(2, Node-7)], dict contains 0, dict:{{-1, [9]}, {0, [3, 15]}, {1, [20]}}
     * Node-15.left is null, Node-15.right is null, queue:[(2, Node-7)]
     * poll cur:(2, Node-7), queue:[], dict not contains 2, dict:{{-1, [9]}, {0, [3, 15]}, {1, [20]}, {2, [7]}}
     * dict.values:[9],[3, 15], [20], [7]
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
