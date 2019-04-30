package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			// 1-1 Time-wait 시간에 소켓에 포트번호 할당을 가능하게 하기 위함
			serverSocket.setReuseAddress(true);

			// 2. 바인딩
			// : Socket 에 SocketAddress(IPAddress + Port) 를 바인딩한다.
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhost = inetAddress.getHostAddress();
		
			//serverSocket.bind(new InetSocketAddress(localhost, 5000)); // 게이트웨이 주소만 가능
			//serverSocket.bind(new InetSocketAddress(inetAddress, 5000)); // 게이트웨이 주소만 가능
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 7000)); // 게이트웨이, IP주소만 가능
			System.out.println(inetAddress.getHostName() +" 채팅창");
			
			// 3. accept
			// : 클라이언트의 연결요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking
			
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			System.out.println("[server] connected by client["+remoteHostAddress+":"+remotePort+"]");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			while(true) {
				//5. 데이터 읽기
				byte[] buffer = new byte[256]; 
				int readByteCount = is.read(buffer);
				if(readByteCount == -1) {
					//클라이언트가 정상종료한 경우
					// close() 메소드 호출
					System.out.println("[server] closed by client");
					break;
				}
				
				String data = new String(buffer, 0 ,readByteCount, "utf-8");
				System.out.println("[server] received:"+data);
				
				
				
				// 6. 데이터 쓰기
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				os.write(data.getBytes("utf-8"));
				
			}
			
			
		} catch (SocketException e) {
			System.out.println("[server] sudden closed");
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed())
					serverSocket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
