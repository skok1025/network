package io;

import java.io.BufferedReader;
import java.io.FileReader;

public class _MyTest {

	public static void main(String[] args) {
		
		String line = null;
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("phone.txt"));
			while((line=reader.readLine())!= null	) {

				System.out.println(line); 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
