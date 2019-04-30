package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.34";
	private static final int SERVER_PORT = 7005;

	public static void main(String[] args) {
		Scanner scan = null;
		Socket socket = null;
		try {
			// 1.Scanner 생성 (표준입력 연결)
			scan = new Scanner(System.in);
			// 1.소켓생성
			socket = new Socket();

			// 2-1. 소켓 버퍼 사이즈 확인
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			
			System.out.println(receiveBufferSize+":"+sendBufferSize);
			
			
			// 2-2. 소켓 버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024*10);
			socket.setSendBufferSize(1024*10);
			
			System.out.println(receiveBufferSize+":"+sendBufferSize);
			
			// 3. 서버연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			log("connected");

			
			//4. IOStream 생성(받아오기)
			BufferedReader br = new BufferedReader( 
					new InputStreamReader( socket.getInputStream(),"utf-8") ); 				
			PrintWriter pr = new PrintWriter( 
					new OutputStreamWriter( socket.getOutputStream(), "utf-8"), true ); // autoFlush true

			
			while(true) {
				// 5. 키보드 입력 받기
				System.out.print(">>");
				String line = scan.nextLine();
				
				if("quit".contentEquals(line)) {
					break;
				}
				
				// 6. 데이터 쓰기
				pr.println(line);
				
				// 7. 데이터 읽기
				String data = br.readLine();
				if(data == null) {
					log("closed by server");
					break;
				}
				// 8. 콘솔출력
				System.out.println("<<"+data);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(scan != null) {
					scan.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	public static void log(String log) {
		System.out.println("[client] " + log);
	}
}
