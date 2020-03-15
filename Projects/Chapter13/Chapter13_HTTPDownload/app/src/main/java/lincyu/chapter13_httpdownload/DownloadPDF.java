package lincyu.chapter13_httpdownload;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPDF extends Thread {
	
	Activity activity;
	
	DownloadPDF(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void run() {
		try {
			File sdroot = Environment.getExternalStorageDirectory();
			File pdf = new File(sdroot, "download.pdf");
			FileOutputStream fos = new FileOutputStream(pdf);
		
			String pdfsrc = "http://hscc.cs.nctu.edu.tw/" +
				"~lincyu/MobileUserExperience.pdf";

			URL url = new URL(pdfsrc);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) > 0 ) {
				fos.write(buffer, 0, len);
			}
			is.close();
			fos.close();
			showmsg("下載完成");
		} catch (Exception e) {
			showmsg("下載失敗");
		}
	}
	
	void showmsg(final String msg) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, msg,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
