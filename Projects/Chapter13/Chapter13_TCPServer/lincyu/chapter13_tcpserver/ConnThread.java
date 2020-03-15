package lincyu.chapter13_tcpserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnThread extends Thread {
	
	Socket socket;
	
	ConnThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(reader);
			String receivedmsg = breader.readLine();
			System.out.println("Received: '" + receivedmsg + "'");
				
			receivedmsg = receivedmsg.toUpperCase();
				
			OutputStream os = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(os, true);				
			System.out.print("Sending string: '" +
					receivedmsg + "'\n");
			writer.println(receivedmsg);
			writer.close();
			
			socket.close();
		} catch (Exception e) {
			System.out.println("傳輸失敗");
		}
	}
}
