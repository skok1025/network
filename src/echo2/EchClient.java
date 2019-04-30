package echo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchClient {
	
	public static final String SERVER_IP = "192.168.1.34";
	public static final int SERVER_PORT = 7001;
	
	public static void main(String[] args) {
		
		Scanner scan = null;
		Socket socket = null;
		
		try {
			scan = new Scanner(System.in);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
