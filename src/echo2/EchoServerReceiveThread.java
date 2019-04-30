package echo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	
	
	private Socket socket;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
		
	}

	
	@Override
	public void run() {

		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		String addr = inetRemoteSocketAddress.getAddress().getHostAddress();
		int port = inetRemoteSocketAddress.getPort();
		
		EchoServer.log("connected by [client "+addr+":"+port+"]");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"),true);
			
			while(true) {
				String line = reader.readLine();
				
				if(line == null) {
					EchoServer.log("closed by client");
					break;
				}
				
				EchoServer.log("received: "+line);
				
				writer.println(line);
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
			EchoServer.log("sudden closed by client");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
