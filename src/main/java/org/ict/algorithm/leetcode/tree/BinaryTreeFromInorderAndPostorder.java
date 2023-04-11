package org.ict.algorithm.leetcode.tree;

/**
 * Given two integer arrays inorder and postorder where
 * inorder is the inorder traversal of a binary tree and
 * postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 * Constraints:
 *
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 *
 * @author sniper
 * @date 2021/12/2
 * LC106
 */
public class BinaryTreeFromInorderAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //todo
        return null;
    }

    /**
 * You are given the root of a full binary tree with the following properties:
 *
 * Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
 * Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.
 * The evaluation of a node is as follows:
 *
 * If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
 * Otherwise, evaluate the node's two children and apply the boolean operation of its value with the children's evaluations.
 * Return the boolean result of evaluating the root node.
 *
 * A full binary tree is a binary tree where each node has either 0 or 2 children.
 *
 * A leaf node is a node that has zero children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3,null,null,0,1]
 * Output: true
 * Explanation: The above diagram illustrates the evaluation process.
 * The AND node evaluates to False AND True = False.
 * The OR node evaluates to True OR False = True.
 * The root node evaluates to True, so we return true.
 * Example 2:
 *
 * Input: root = [0]
 * Output: false
 * Explanation: The root node is a leaf node and it evaluates to false, so we return false.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 3
 * Every node has either 0 or 2 children.
 * Leaf nodes have a value of 0 or 1.
 * Non-leaf nodes have a value of 2 or 3.
 * @author sniper
 * @date 14 Jan, 2023
 * LC2331, Easy
 */
public static class EvaluateBooleanBinaryTree {


    public boolean evaluateTreeV2(TreeNode root) {
        if (root.val == 0) {
            return false;
        }
        if (root.val == 1) {
            return true;
        }
        boolean l = evaluateTreeV2(root.left);
        boolean r = evaluateTreeV2(root.right);

        return (root.val == 2 ? l || r : l && r);
    }


    /**
     * @author Vlad votrubac
     * @see <a href="https://leetcode.com/problems/evaluate-boolean-binary-tree/solutions/2260557/switch"></a>
     * @param root
     * @return
     */
    public boolean evaluateTreeV1(TreeNode root) {
        switch (root.val) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return evaluateTreeV1(root.left) || evaluateTreeV1(root.right);
            default:
                return evaluateTreeV1(root.left) && evaluateTreeV1(root.right);
        }
    }

    /**
     * Time Cost 0ms
     * @param root
     * @return
     */
    public boolean evaluateTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return (root.val == 0 ? false : true);
        }
        boolean leftVal = false;
        boolean rightVal = false;
        if (root.left != null) {
            leftVal = evaluateTree(root.left);
        }
        if (root.right != null) {
            rightVal = evaluateTree(root.right);
        }
        if (root.val == 2) {
            return leftVal || rightVal;
        } else {
            return leftVal && rightVal;
        }
    }
}

    /**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 *
 * The cloned tree is a copy of the original tree.
 *
 * Return a reference to the same node in the cloned tree.
 *
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: tree = [7,4,3,null,null,6,19], target = 3
 * Output: 3
 * Explanation: In all examples the original and cloned trees are shown.
 * The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
 * Example 2:
 *
 *
 * Input: tree = [7], target =  7
 * Output: 7
 * Example 3:
 *
 *
 * Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * The values of the nodes of the tree are unique.
 * target node is a node from the original tree and is not null.
 *
 *
 * Follow up: Could you solve the problem if repeated values on the tree are allowed?
 * @author sniper
 * @date 14 Jan, 2023
 * LC1379, Easy
 */
public static class FindCorrespondingNodeOfBinaryTreeInCloneOfThatTree {


    /**
     * Follow up: Could you solve the problem if repeated values on the tree are allowed?
     * Time Cost 1ms
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopyV1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target) {
            return cloned;
        }
        TreeNode leftResult = getTargetCopyV1(original.left, cloned.left, target);
        if (leftResult != null) {
            return leftResult;
        }
        return getTargetCopyV1(original.right, cloned.right, target);
    }


    /**
     * Time Cost 2ms
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return helper(cloned, target);
    }

    private TreeNode helper( TreeNode node, TreeNode target) {
        if (node == null) {
            return null;
        }
        if (node.val == target.val) {
            return node;
        }
        TreeNode leftVal = helper(node.left, target);
        TreeNode rightVal = helper(node.right, target);
        if (leftVal != null) {
            return leftVal;
        }
        if (rightVal != null) {
            return rightVal;
        }
        return null;
    }
}

    /**
 * You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.
 *
 * Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [10,4,6]
 * Output: true
 * Explanation: The values of the root, its left child, and its right child are 10, 4, and 6, respectively.
 * 10 is equal to 4 + 6, so we return true.
 * Example 2:
 *
 *
 * Input: root = [5,3,1]
 * Output: false
 * Explanation: The values of the root, its left child, and its right child are 5, 3, and 1, respectively.
 * 5 is not equal to 3 + 1, so we return false.
 *
 *
 * Constraints:
 *
 * The tree consists only of the root, its left child, and its right child.
 * -100 <= Node.val <= 100
 * @author sniper
 * @date 14 Jan, 2023
 * LC2236,Easy
 */
public static class RootEqualsSumOfChildren {


    public boolean checkTree(TreeNode root) {
        return (root.val == root.left.val + root.right.val);
    }
}
}
