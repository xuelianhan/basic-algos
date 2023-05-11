package org.ict.algorithm.leetcode.tree;


/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -1000 <= Node.val <= 1000
 * LC124, Hard, frequency=39
 */
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode root = test1();
        BinaryTreeMaximumPathSum instance = new BinaryTreeMaximumPathSum();
        int result = instance.maxPathSumV1(root);
        System.out.println(result);
    }




    /**
     * Time Cost 1ms
     * Recursive Solution without Global Variable.
     * @param root
     * @return
     */
    public int maxPathSumV1(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        helperV1(root, res);
        return res[0];
    }

    /**
     *    1
     *   / \
     *  2   3
     * @return max
     */
    public int helperV1(TreeNode root, int[] res) {
        if (null == root) {
            return 0;
        }
        int left = Math.max(0, helperV1(root.left, res));
        int right = Math.max(0, helperV1(root.right, res));
        res[0] = Math.max(res[0], left + right + root.val);
        /**
         * At most one child can be used.
         */
        return Math.max(left, right) + root.val;
    }


    private int ans;

    /**
     * Time Cost 0ms
     * Recursive Solution with Global Variable.
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        helper(root);
        return ans;
    }

    public int helper(TreeNode root) {
         if (null == root) {//1
             return 0;
         }
         int left = Math.max(0, helper(root.left));//2
         int right = Math.max(0, helper(root.right));//3
        /**
         * compare branch-sum(ans) with full-sum(left + right + root.val)
         */
        ans = Math.max(ans, left + right + root.val);//4
        /**
         * choose only left or right to add with root
         */
        return Math.max(left, right) + root.val;//used in 2 and 3
    }

    /**
     * [5,4,8,11,null,13,4,7,2,null,null,null,1]
     *               5
     *             /   \
     *           4      8
     *          /      / \
     *         11     13  4
     *        / \          \
     *       7   2          1
     *
     * if you sum children value to parent, you will get this:
     *               55
     *             /   \
     *           24    26
     *          /      / \
     *         20     13  5
     *        / \          \
     *       7   2          1
     *
     *  but the path definition is that
     *  each pair of adjacent nodes in the sequence has an edge connecting them.
     *  So the path is like this
     *  7--11--4--5--8--13
     *
     *
     * @return
     */
    public static TreeNode test5() {
        return null;
    }

    /**
     *    1
     *   / \
     * -2   3
     *
     *
     *    2
     *   / \
     * -2   3
     * @return
     */
    public static TreeNode test4() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(-2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        return root;
    }

    public static TreeNode test3() {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(-1);
        root.left = left;
        return root;
    }


    /**
     * nums = [1,-2,-3,1,3,-2,null,-1]
     *           1
     *         /   \
     *       -2    -3
     *      / \    /
     *     1  3  -2
     *    /
     *   -1
     *
     *  After sum the children value to the root:
     *
     *           -3
     *         /   \
     *       1    -5
     *      / \    /
     *     0  3  -2
     *    /
     *   -1
     * @return
     */
    public static TreeNode test2() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(-2);
        TreeNode l1 = new TreeNode(1);
        TreeNode r1 = new TreeNode(3);
        left.left = l1;
        left.right = r1;
        TreeNode l3 = new TreeNode(-1);
        l1.left = l3;
        TreeNode right = new TreeNode(-3);
        TreeNode l2 = new TreeNode(-2);
        right.left = l2;
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     *    1
     *   / \
     *  2   3
     * @return
     */
    public static TreeNode test1() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        return root;
    }

}
