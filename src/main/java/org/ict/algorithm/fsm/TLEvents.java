package org.ict.algorithm.fsm;

public enum TLEvents {
	T("task callout event"),
	L("listener for message event"),
	TR("task end callout event"),
	TSR("task speech recognize event"),
	TF("task result notify event");
	
	private TLEvents(String name) {
	    this.name = name;
	}
	private String name;
	
    public String getName() {
        return name;
    }
	
}
