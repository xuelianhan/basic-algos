package org.ict.algorithm.leetcode.binarysearchtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given the root of a binary search tree (BST),
 * where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Example 1:
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1.
 * Swapping 1 and 3 makes the BST valid.
 *
 * Example 2:
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 1000].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * Follow up: A solution using O(n) space is pretty straight-forward.
 * Could you devise a constant O(1) space solution?
 * @author sniper
 * @date 27 Jul 2023
 * LC99, Medium
 */
public class RecoverBinarySearchTree {

    /**
     * class Solution {
     * public:
     *     void recoverTree(TreeNode* root) {
     *         vector<TreeNode*> node_list;
     *         vector<int> val_list;
     *         inorder(root, node_list, val_list);
     *         sort(val_list.begin(), val_list.end());
     *         for (int i = 0; i < node_list.size(); i++) {
     *             node_list[i]->val = val_list[i];
     *         }
     *     }
     *
     *     void inorder(TreeNode* root, vector<TreeNode*>& node_list, vector<int>& val_list) {
     *         if (!root) {
     *             return;
     *         }
     *         inorder(root->left, node_list, val_list);
     *         node_list.push_back(root);
     *         val_list.push_back(root->val);
     *         inorder(root->right, node_list, val_list);
     *     }
     * };
     * --------------------------
     * 
     * @param root
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        List<Integer> valList = new ArrayList<>();
        inorder(root, nodeList, valList);
        Collections.sort(valList);
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).val = valList.get(i);
        }
    }

    private void inorder(TreeNode root, List<TreeNode> nodeList, List<Integer> valList) {
        if (root == null) {
            return;
        }
        inorder(root.left, nodeList, valList);
        nodeList.add(root);
        valList.add(root.val);
        inorder(root.right, nodeList, valList);
    }
}
