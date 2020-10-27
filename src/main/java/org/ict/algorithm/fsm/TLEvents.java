package org.ict.algorithm.fsm;

public enum TLEvents {
	T("task正常外呼事件"),
	L("Listener消息监听事件"),
	TR("task终结外呼事件"),
	TSR("task语音识别事件"),
	TF("task结果通知事件");
	
	private TLEvents(String name) {
	    this.name = name;
	}
	private String name;
	
    public String getName() {
        return name;
    }
	
}
