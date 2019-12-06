package org.ict.algorithm.thread;

public class Node {
    private Integer val1; 
    private Integer val2; 
    private Integer val3; 
    
    public Node() {}
    
    public Node(Integer val1, Integer val2, Integer val3) {
    	this.val1 = val1;
    	this.val2 = val2;
    	this.val3 = val3;
    }

	public Integer getVal1() {
		return val1;
	}

	public void setVal1(Integer val1) {
		this.val1 = val1;
	}

	public Integer getVal2() {
		return val2;
	}

	public void setVal2(Integer val2) {
		this.val2 = val2;
	}

	public Integer getVal3() {
		return val3;
	}

	public void setVal3(Integer val3) {
		this.val3 = val3;
	}
    
}
