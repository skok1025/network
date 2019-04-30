package echo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static final int PORT = 7001;
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			
			log("start.... port:"+PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				Thread thread = new EchoServerReceiveThread(socket);
				
				thread.start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			if(serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		
	}
	
	public static void log(String log) {
		System.out.println("[server #"+Thread.currentThread()+"] "+log);
	}

}
