package echo.test;

import java.net.Socket;

public class User {
	
	private Socket socket;
	private Long threadId;
	
	
	
	public User(Socket socket, Long threadId) {
		super();
		this.socket = socket;
		this.threadId = threadId;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public Long getThreadId() {
		return threadId;
	}
	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}
	@Override
	public String toString() {
		return "User [socket=" + socket + ", threadId=" + threadId + "]";
	}
	
	
	

}
