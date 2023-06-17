package org.ict.algorithm.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 *
 *
 *
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 * @author sniper
 * @date 17 Jun 2023
 * LC106, Medium
 */
public class ConstructBinaryTreeByInorderAndPostorder {


    /**
     * Understanding the following solution
     * Time Cost 2ms
     * ------------------------------------------------
     * @author lurklurk
     * The basic idea is to take the last element in postorder array as the root,
     * find the position of the root in the inorder array;
     * then locate the range for left subtree and right subtree and do recursion.
     * Use a HashMap to record the index of root in the inorder array.
     * -------------------------------------------------
     * # Definition for a binary tree node.
     * # class TreeNode:
     * #     def __init__(self, val=0, left=None, right=None):
     * #         self.val = val
     * #         self.left = left
     * #         self.right = right
     * class Solution:
     *     def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
     *         map = {num: i for i, num in enumerate(inorder)}
     *         n = len(inorder)
     *
     *         def build(inStart: int, inEnd: int, postStart: int, postEnd: int) -> Optional[TreeNode]:
     *             if inStart > inEnd:
     *                 return None
     *             rootVal = postorder[postEnd]
     *             rootInOrderIndex = map[rootVal]
     *             leftSize = rootInOrderIndex - inStart
     *
     *             root = TreeNode(rootVal)
     *             root.left = build(inStart, rootInOrderIndex - 1,  postStart, postStart + leftSize - 1)
     *             root.right = build(rootInOrderIndex + 1, inEnd,  postStart + leftSize, postEnd - 1)
     *             return root
     *
     *         return build(0, n - 1, 0, n - 1)
     * --------------------------------------------------
     * class Solution {
     * public:
     *     TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
     *         unordered_map<int, int> map;
     *         for (int i = 0; i < inorder.size(); ++i) {
     *             map[inorder[i]] = i;
     *         }
     *         const int n =  inorder.size();
     *         return build(inorder, 0, n - 1, postorder, 0, n - 1, map);
     *     }
     *
     * private:
     *   TreeNode* build(const vector<int>& inorder, int inStart, int inEnd,
     *                   const vector<int>& postorder, int postStart, int postEnd,
     *                   const unordered_map<int, int>& map) {
     *
     *         if (inStart > inEnd) {
     *             return nullptr;
     *         }
     *         const int rootVal = postorder[postEnd];
     *         const int rootInOrderIndex = map.at(rootVal);
     *         const int leftSize = rootInOrderIndex - inStart;
     *
     *         TreeNode* root = new TreeNode(rootVal);
     *         root->left = build(inorder, inStart, rootInOrderIndex - 1,
     *             postorder, postStart, postStart + leftSize - 1, map);
     *         root->right = build(inorder, rootInOrderIndex + 1, inEnd,
     *             postorder, postStart + leftSize, postEnd - 1, map);
     *         return root;
     *   }
     * };
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /**
         * inorder and postorder consist of unique values.
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        /**
         * postorder.length == inorder.length
         */
        int n = postorder.length;
        return build(inorder, 0, n - 1, postorder, 0, n - 1, map);
    }

    /**
     * e.g.
     * inorder:   [11,4,5,13,8,9]
     * postorder: [11,4,13,9,8,5]
     * rootVal:5, rootInOrderIdx:2, leftSize = rootInOrderIdx - inStart = 2 - 0 = 2
     *          5
     *         / \
     *   (11,4)   (13,8,9)
     *
     * left:
     * inStart:0, inEnd = rootInOrderIdx - 1 = 1, [11, 4] of inorder
     * postStart:0, postEnd = postStart + leftSize - 1 = 0 + 2 - 1 = 1, [11, 4] of postorder
     *
     * right:
     * inStart = rootInOrderIdx + 1 = 3, inEnd:5, [13, 8, 9] of inorder
     * postStart = postStart + leftSize = 0 + 2 = 2, postEnd = postEnd - 1 = 4, [13, 9, 8] of postorder
     *
     *
     * @param inorder
     * @param inStart
     * @param inEnd
     * @param postorder
     * @param postStart
     * @param postEnd
     * @param map
     * @return
     */
    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> map) {
        if (inStart > inEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int rootInOrderIdx = map.get(rootVal);
        int leftSize = rootInOrderIdx - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, rootInOrderIdx - 1, postorder, postStart, postStart + leftSize - 1, map);
        root.right = build(inorder, rootInOrderIdx + 1, inEnd, postorder, postStart + leftSize, postEnd - 1, map);
        return root;
    }

}
