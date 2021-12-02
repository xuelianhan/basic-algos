package org.ict.algorithm.leetcode.tree;

import org.ict.algorithm.leetcode.array.TwoSum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a Binary Search Tree and a target number k,
 * return true if there exist two elements
 * in the BST such that their sum is equal to the given target.
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 * Input: root = [2,1,3], k = 4
 * Output: true
 *
 * Input: root = [2,1,3], k = 1
 * Output: false
 *
 * Input: root = [2,1,3], k = 3
 * Output: true
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -10^4 <= Node.val <= 10^4
 * root is guaranteed to be a valid binary search tree.
 * -10^5 <= k <= 10^5
 *
 * @author sniper
 * @date 2021/12/1
 *
 * @LC653
 */
public class TwoSumIVWithBSTInput {

    public boolean findTarget(TreeNode root, int k) {
        if (null == root) {
            return false;
        }
        //LC94
        List<Integer> list = BinaryTreeInorderTraversal.inorderTraversalV2(root);
        //two sum: LC1
        int[] nums = list.stream().mapToInt(i -> i).toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = k - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

}
