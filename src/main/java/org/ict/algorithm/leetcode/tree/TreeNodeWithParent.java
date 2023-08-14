package org.ict.algorithm.leetcode.tree;

/**
 * @author sniper
 * @date 15 Aug 2023
 */
public class TreeNodeWithParent {

    public Integer val;
    public TreeNodeWithParent parent;
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;

    public TreeNodeWithParent() {}

    public TreeNodeWithParent(Integer val) {
        this.val = val;
    }

    public TreeNodeWithParent(Integer val, TreeNodeWithParent left, TreeNodeWithParent right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
