package org.ict.algorithm.leetcode.binarysearchtree;

/**
 * Given a root node reference of a BST and a key,
 * delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * Example 3:
 *
 * Input: root = [], key = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -10^5 <= key <= 10^5
 *
 *
 * Follow up: Could you solve it with time complexity O(height of tree)?
 * @author sniper
 * @date 28 Aug 2023
 * LC450, Medium
 * Amazon
 */
public class DeleteNodeInaBST {


    /**
     * Recursive Solution for BST Node delete.
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNodeV2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = getMin(root.right);
            root.right = deleteNodeV2(root.right, minNode.val);
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.val < key) {
            root.right = deleteNodeV2(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNodeV2(root.left, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Generalized solution for all binary trees
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNodeV1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.right == null) {
                TreeNode left = root.left;
                root = null;
                return left;
            } else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                swap(root, cur);
            }
        }
        root.left = deleteNodeV1(root.left, key);
        root.right = deleteNodeV1(root.right, key);
        return root;
    }

    private void swap(TreeNode root, TreeNode cur) {
        int temp = root.val;
        root.val = cur.val;
        cur.val = temp;
    }

    /**
     * Recursive Solution for BST Node delete.
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                root = (root.left == null ? root.right : root.left);
            } else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                root.val = cur.val;
                root.right = deleteNode(root.right, cur.val);
            }
        }
        return root;
    }
}
