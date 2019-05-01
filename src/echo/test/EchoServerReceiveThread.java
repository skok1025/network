package echo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class EchoServerReceiveThread extends Thread {
	
	private Socket socket;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remotePort = inetRemoteSocketAddress.getPort();
		EchoServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]" );
		ArrayList<PrintWriter> prlist = new ArrayList<PrintWriter>();
		
		try {
			//4. IOStream 생성(받아오기)
			BufferedReader br = new BufferedReader( 
					new InputStreamReader( socket.getInputStream(),
										   "utf-8") );
			
			for(User user:EchoServer.threadSocketList) {
				
				prlist.add(new PrintWriter( 
						new OutputStreamWriter( user.getSocket().getOutputStream(), "utf-8"), true ) // autoFlush true
				);
			}
			
			
			long threadId = Thread.currentThread().getId();
			String nick = br.readLine();
			
			EchoServer.userMap.put(threadId, nick);
			
			while(true) {
				//5. 데이터 읽기
				String data = br.readLine();
				if(data == null) {
					EchoServer.log("closed by client");
					break;
				}
				
				EchoServer.log("received:" + data);
				
				for(PrintWriter pr:prlist) {
					//6. 데이터 쓰기
					pr.println(EchoServer.userMap.get(Thread.currentThread().getId())+":"+data);
					
				}
				
			}
		}catch(SocketException e) {
			System.out.println("[server] sudden closed by client");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(socket != null && socket.isClosed() == false ) {
					socket.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
