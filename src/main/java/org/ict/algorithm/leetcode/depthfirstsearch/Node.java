package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Node {

	public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int val,List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
    
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
