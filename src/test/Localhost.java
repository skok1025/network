package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {
	public static void main(String[] args) {
		try {
				
			//IP Address 를 얻는다..
			InetAddress inetAddress = 
					InetAddress.getLocalHost();
			
			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			
			System.out.println(hostname+" : "+hostAddress);
			
			byte[] addresses = inetAddress.getAddress();
			for(byte addr:addresses) {
				System.out.print(addr & 0x000000ff); // 부호비트 삭제
				System.out.print(".");
			}
			
			/*
			 * InetAddress[] inetAddresslist=InetAddress.getAllByName(hostname);
			 * for(InetAddress addr:inetAddresslist) {
			 * System.out.println(addr.getHostAddress()); }
			 */
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
