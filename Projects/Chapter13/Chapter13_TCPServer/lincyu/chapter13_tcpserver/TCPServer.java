package lincyu.chapter13_tcpserver;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static final int SERVERPORT = 7777;

	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(SERVERPORT);
			while (true) {
				Socket client = serverSocket.accept();
				ConnThread thread = new ConnThread(client);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println("無法啟動Server");
		}
	}
}
