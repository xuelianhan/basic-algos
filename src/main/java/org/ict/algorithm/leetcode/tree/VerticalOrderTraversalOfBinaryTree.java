package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 * Example 3:
 *
 *
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 * @author sniper
 * @date 11 Apr, 2023
 * LC987, Hard
 * Very similar with {@link BinaryTreeVerticalOrderTraversal} but there is a little different:
 * In the same location and should be ordered by their values.
 */
public class VerticalOrderTraversalOfBinaryTree {

    /**
     * Understanding the following solution.
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversalV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeMap<Integer, List<Tuple>> dict = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(0, 0, root));

        while (!queue.isEmpty()) {
            Tuple cur = queue.poll();
            if (!dict.containsKey(cur.getSeqColumn())) {
                dict.put(cur.getSeqColumn(), new ArrayList<>());
            }
            /**
             * Group tuples by seqColumn firstly.
             */
            dict.get(cur.getSeqColumn()).add(cur);
            if (cur.getNode().left != null) {
                Tuple p = new Tuple(cur.getSeqColumn() - 1, cur.getSeqRow() + 1, cur.getNode().left);
                queue.offer(p);
            }
            if (cur.getNode().right != null) {
                Tuple p = new Tuple(cur.getSeqColumn() + 1, cur.getSeqRow() + 1, cur.getNode().right);
                queue.offer(p);
            }
        }
        for (Map.Entry<Integer, List<Tuple>> entry : dict.entrySet()) {
            List<Tuple> valList = entry.getValue();
            /**
             * Sort tuple-list by seqRow firstly, if seqRow equals, then sort tuple-list by node-val.
             */
            Collections.sort(valList, (o1, o2) -> {
                if (o1.getSeqRow() < o2.getSeqRow()) {
                    return -1;
                } else if (o1.getSeqRow() > o2.getSeqRow()) {
                    return 1;
                } else {
                    int v1 = o1.getNode().val;
                    int v2 = o2.getNode().val;
                    if (v1 < v2) {
                        return -1;
                    } else if (v1 > v2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            List<Integer> list = new ArrayList<>();
            for (Tuple tuple : valList) {
                list.add(tuple.node.val);
            }
            res.add(list);
        }
        return res;
    }

    /**
     * e.g root = [3,1,4,0,2,2], expected [[0],[1],[3,2,2],[4]]
     *                 3(0,0)
     *               /        \
     *          1(1,-1)        4(1, 1)
     *        /      |         /
     *     0(2,-2)  2(2, 0)   2(2, 0)
     * There may be multiple nodes in the same row and same column.
     * In such a case, sort these nodes by their values.
     *
     *
     * @see <a href="https://www.topcoder.com/thrive/articles/vertical-order-traversal-of-a-binary-tree"></a>
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeMap<Integer, List<Tuple>> dict = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(0, 0, root));

        while (!queue.isEmpty()) {
            Tuple cur = queue.poll();
            if (!dict.containsKey(cur.getSeqColumn())) {
                dict.put(cur.getSeqColumn(), new ArrayList<>());
            }
            /**
             * Group tuples by seqColumn firstly.
             */
            dict.get(cur.getSeqColumn()).add(cur);
            if (cur.getNode().left != null) {
                Tuple p = new Tuple(cur.getSeqColumn() - 1, cur.getSeqRow() + 1, cur.getNode().left);
                queue.offer(p);
            }
            if (cur.getNode().right != null) {
                Tuple p = new Tuple(cur.getSeqColumn() + 1, cur.getSeqRow() + 1, cur.getNode().right);
                queue.offer(p);
            }
        }
        for (Map.Entry<Integer, List<Tuple>> entry : dict.entrySet()) {
            List<Tuple> valList = entry.getValue();
            Collections.sort(valList, new Comparator<Tuple>() {
                @Override
                public int compare(Tuple o1, Tuple o2) {
                    if (o1.getSeqRow() < o2.getSeqRow()) {
                        return -1;
                    } else if (o1.getSeqRow() > o2.getSeqRow()) {
                        return 1;
                    } else {
                        int v1 = o1.getNode().val;
                        int v2 = o2.getNode().val;
                        if (v1 < v2) {
                            return -1;
                        } else if (v1 > v2) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            });
            List<Integer> list = new ArrayList<>();
            for (Tuple tuple : valList) {
                list.add(tuple.node.val);
            }
            res.add(list);
        }
        return res;
    }

    static class Tuple {
        private int seqColumn;

        private int seqRow;

        private TreeNode node;

        public Tuple() {}

        public Tuple(int seqColumn, int seqRow, TreeNode node) {
            this.seqColumn = seqColumn;
            this.seqRow = seqRow;
            this.node = node;
        }

        public int getSeqColumn() {
            return seqColumn;
        }

        public int getSeqRow() {
            return seqRow;
        }
        public TreeNode getNode() {
            return node;
        }
    }
}
