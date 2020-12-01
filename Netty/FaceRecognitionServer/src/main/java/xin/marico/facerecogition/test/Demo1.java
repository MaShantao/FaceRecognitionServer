package xin.marico.facerecogition.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("python G:\\PythonProject\\demo\\test.py  1231415646.jpg");
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
