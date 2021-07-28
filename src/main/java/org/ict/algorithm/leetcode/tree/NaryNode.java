package org.ict.algorithm.leetcode.tree;

import java.util.List;

public class NaryNode {

    public int val;
    public List<NaryNode> children;

    public NaryNode() {}

    public NaryNode(int val) {
        this.val = val;
    }

    public NaryNode(int val, List<NaryNode> children) {
        this.val = val;
        children = children;
    }

}
