package org.ict.algorithm.leetcode.tree;

/**
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5],
 * which represents the following height balanced BST:
 *
 * LC108
 */
public class SortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        SortedArrayToBinarySearchTree instance = new SortedArrayToBinarySearchTree();
        instance.sortedArrayToBST(nums);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = low + (high - low)/2;// avoid overflow
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, low, mid - 1);
        node.right = helper(nums, mid + 1, high);
        return node;
    }

    public TreeNode bfs(int[] nums) {
        //todo
        //todo
        return null;
    }

}
