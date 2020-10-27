package org.ict.algorithm.fsm;

public enum ReplyEvent {
	Y("yes"),
	N("no");
	
	private ReplyEvent(String name) {
	    this.name = name;
	}
	
	private String name;
	
	public String getName() {
	    return this.name;
	}
	
}
