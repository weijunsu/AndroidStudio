package lincyu.chapter13_ftpdemo;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.app.Activity;

public class LoginThread extends Thread {
	
	String site, user, pwd;
	FTPClient client;
	Activity activity;
	
	LoginThread(Activity activity, FTPClient client,
			String site, String user, String pwd) {
		this.activity = activity;
		this.client = client;
		this.site = site;
		this.user = user;
		this.pwd = pwd;
	}
	
	@Override
	public void run() {
		try {
			client.connect(site);
			int replycode = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replycode)) {
				client.disconnect();
				Common.showToast(activity, "連線失敗");
				return;
			}
			if (client.login(user, pwd)) {
				client.enterLocalPassiveMode();
				Common.showToast(activity, "登入成功");
				return;
			}
		} catch (Exception e) {
		}
		Common.showToast(activity, "登入失敗");
	}
}
