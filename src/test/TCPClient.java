package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import sun.net.www.content.audio.basic;

public class TCPClient {

	private static final String SERVER_IP = "192.168.1.34";
	private static final int SERVER_PORT = 7000;

	// final : 값을 마지막으로 지정
	// 메소드 : 오버라이드 하지말라
	// 클래스 : 상속하지 말라
	public static void main(String[] args) {
		Socket socket = null;
		try {
			// 1.소켓생성
			socket = new Socket();

			// 2. 서버연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[cliet] connected");
			
			// 3. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 4. 쓰기
			String data = "Hello World\n";
			os.write(data.getBytes("utf-8"));
			
		
			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer); // blocking
			
			if(readByteCount == -1) {
				System.out.println("[client] closed by server");
			}
			
			data = new String(buffer,0,readByteCount,"utf-8");
			System.out.println("[client] received : "+data);
			
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && !socket.isClosed())
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
