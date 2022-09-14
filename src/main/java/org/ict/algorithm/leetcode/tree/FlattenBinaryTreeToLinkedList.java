package org.ict.algorithm.leetcode.tree;

import java.util.Stack;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to
 * the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Input: root = []
 * Output: []
 *
 * Input: root = [0]
 * Output: [0]
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 *
 * @author sniper
 * @date 2021/12/2
 * LC114
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * Recursion Tree
     * @param args
     */
    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        TreeNode six = new TreeNode(6);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);

        two.left = three;
        two.right = four;
        five.right = six;
        one.left = two;
        one.right = five;
        flatten(one);
    }

    /**
     * We need to flat tree with order of root-left-right,
     * So we should push the node in reverse order like right-left-root
     *
     * Preorder (Root Left Right) = Reverse PostOrder(Right,Left,Root)
     * The output tree(Linked List) is formed from Bottom to top(or you can say tail to head)
     * since post-order has the property to go deep in the tree and then process the nodes
     * We also exploit the backtracking abilities of dfs post-order to connect the links.
     *
     * Running output:
     * before root right:root:1, prev:null
     * before root right:root:5, prev:null
     * before root right:root:6, prev:null
     * before return:root:null, prev:null
     * before root left:root:6, prev:null
     * before return:root:null, prev:null
     * before root:root:6, prev:null
     * before root left:root:5, prev:6
     * before return:root:null, prev:6
     * before root:root:5, prev:6
     * before root left:root:1, prev:5
     * before root right:root:2, prev:5
     * before root right:root:4, prev:5
     * before return:root:null, prev:5
     * before root left:root:4, prev:5
     * before return:root:null, prev:5
     * before root:root:4, prev:5
     * before root left:root:2, prev:4
     * before root right:root:3, prev:4
     * before return:root:null, prev:4
     * before root left:root:3, prev:4
     * before return:root:null, prev:4
     * before root:root:3, prev:4
     * before root:root:2, prev:3
     * before root:root:1, prev:2
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        flatten(root, null);
    }

    public static TreeNode flatten(TreeNode root, TreeNode prev) {
       if (root == null) {
           System.out.print("before return:");
           System.out.println("root:" + (root == null ? null : root.val) + ", prev:" + (prev == null ? null : prev.val));
           return prev;
       }
       System.out.print("before root right:");
       printTreeNode(root, prev);
       prev = flatten(root.right, prev);

       System.out.print("before root left:");
       printTreeNode(root, prev);
       prev = flatten(root.left, prev);

       root.right = prev;
       root.left = null;
       System.out.print("before root:");
       printTreeNode(root, prev);
       prev = root;
       return root;
    }

    private static void printTreeNode(TreeNode root, TreeNode prev) {
        System.out.println("root:" + (root == null ? null : root.val) + ", prev:" + (prev == null ? null : prev.val));
    }
}
