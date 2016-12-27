package cn.edu.pku.chat;

public class ChatObject {

	private String userName;
	
	private String message;
	
	private String sessionId;
	
	public ChatObject(String userName, String message) {
		super();
		this.userName = userName;
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
