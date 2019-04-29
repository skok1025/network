package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class NSLookup2 {
	
	public static void main(String[] args) {
		
		BufferedReader reader = null;
		String hostname = null;
		
		
		try {
			
			reader = new BufferedReader(new InputStreamReader(System.in));
			hostname = reader.readLine();
			
			InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
			
			for(InetAddress addr : inetAddresses) {
				System.out.println(hostname+":"+addr.getHostAddress());
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
