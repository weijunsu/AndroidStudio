package lincyu.chapter13_tcpclient;

import android.app.Activity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ConnThread extends Thread {

	String msg;
	Activity activity;
	TextView tv;
	
	ConnThread(Activity activity, TextView tv, String msg) {
		this.activity = activity;
		this.tv = tv;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		Socket socket;
		try {
			InetAddress serverAddr = InetAddress.
					getByName("192.168.1.106");
			socket = new Socket(serverAddr, 7777);
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pwriter = new PrintWriter(os, true);
			pwriter.println(msg);
			
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader bufferedreader =
					new BufferedReader(reader);
			String result = bufferedreader.readLine();
			setTextView(activity, result);
			socket.close();
		} catch (Exception e) {
			setTextView(activity, "無法連線至伺服器");
		}
	}

	void setTextView(Activity activity, final String msg) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				tv.setText(msg);
			}
		});
	}	
}
