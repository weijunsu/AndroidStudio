package lincyu.chapter13_ftpdemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.app.Activity;
import android.content.Context;

public class DownloadThread extends Thread {
	
	FTPClient client;
	Activity activity;
	
	DownloadThread(Activity activity, FTPClient client) {
		this.activity = activity;
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			client.setFileType(FTP.ASCII_FILE_TYPE);
			InputStream is = client.retrieveFileStream(
					"./public_html/Android/download.txt"); 

			FileOutputStream fos = activity.openFileOutput(
					"download.txt", Context.MODE_PRIVATE);
			
			int c = -1;
			do {
				c = is.read();
				if (c != -1) fos.write(c);
			} while (c != -1);
			is.close();
			fos.close();
			String msg = readFile();
			Common.showToast(activity, msg);
			return;
		} catch (Exception e) {
		}
		Common.showToast(activity, "下載失敗");
	}
	
	String readFile() {
		StringBuilder str = new StringBuilder("");
		FileInputStream fis = null;
		int c;
		try {
			fis = activity.openFileInput("download.txt");
			do {
				c = fis.read();
				if (c != -1) str.append((char)c);
			} while (c != -1);
			fis.close();
		} catch (Exception e) {
		}
		return str.toString();
	}
}
