package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class NSLookup {
	public static void main(String[] args) {
		
		BufferedReader reader = null;
		//Scanner scan = new Scanner(System.in);
		String hostname;
		InetAddress[] inetAddresslist;

		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.print(">>");
				//hostname = scan.nextLine();
				hostname = reader.readLine();
				if (hostname.equals("exit")) {
					break;
				}
				inetAddresslist = InetAddress.getAllByName(hostname);
				for (InetAddress addr : inetAddresslist) {
					System.out.println(hostname + ":" + addr.getHostAddress());
				}
			}
		} catch (Exception e) {
			System.out.println("알 수 없는 호스트입니다.");
			e.printStackTrace();
			
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
