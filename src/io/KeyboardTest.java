package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {

	public static void main(String[] args) {

		InputStreamReader isr;

		// 보조 스트림2
		// char|char|char|\n -> "char1char2char3"
		BufferedReader br = null;
		try {
			// 기반 스트림(표준입력, 키보드, System.in)
			isr = new InputStreamReader(System.in, "utf-8");
			// 보조 스트림1
			// byte|byte|byte -> char
			br = new BufferedReader(isr);
			// read
			String line = null;

			while ((line = br.readLine()) != null) {
				if ("exit".equals(line)) {
					break;
				}
				System.out.println(">>" + line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
