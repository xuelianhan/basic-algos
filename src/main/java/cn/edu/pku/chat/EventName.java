package cn.edu.pku.chat;

public enum EventName {

	push_event(1, "push_event"),
	conn_event(2, "conn_event"),
	broatcast_event(3, "broat_castevent"),
	point_to_point_event(4, "point_to_point_event");
	private EventName(int code, String desc) {
		this.code = code;
		this.desc =desc;
	}
	
	private int code;
	
	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
