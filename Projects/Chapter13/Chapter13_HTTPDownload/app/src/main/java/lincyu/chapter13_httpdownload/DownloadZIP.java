package lincyu.chapter13_httpdownload;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DownloadZIP extends Thread {
	
	Activity activity;
	ImageView iv1, iv2;
	
	DownloadZIP(Activity activity, ImageView iv1, ImageView iv2) {
		this.activity = activity;
		this.iv1 = iv1;
		this.iv2 = iv2;
	}

	@Override
	public void run() {		
		try {
			File sdroot = Environment.
				getExternalStorageDirectory();
			File zip = new File(sdroot, "download.zip");
			FileOutputStream fos = new FileOutputStream(zip);
			
			String zipsrc = "http://hscc.cs.nctu.edu.tw/" +
					"~lincyu/photos.zip";

			HttpGet request = new HttpGet(zipsrc);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode(); 
			if (code != 200) {
				fos.close();
				showmsg("下載失敗");
				return;
			}
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) > 0 ) {
				fos.write(buffer, 0, len);
			}
			is.close();
			fos.close();
			unpackZip(sdroot, zip);
			zip.delete();
			showmsg("下載完成");
		} catch (Exception e) {
			showmsg("下載失敗");
		}
	}
	
	void unpackZip(File sdroot, File zipfile) throws Exception {
		InputStream is;
		ZipInputStream zis;

		is = new FileInputStream(zipfile);
		zis = new ZipInputStream(new BufferedInputStream(is));          
		ZipEntry ze;

		int findex = 1;
		while ((ze = zis.getNextEntry()) != null) {
			if(ze.isDirectory())
				continue;
				
			String name = ze.getName();
			int len = name.length();
			String subname = name.substring(len-4, len);
			if (subname.equals(".jpg") == false)
				continue;
				
			File outfile = new File(sdroot, ze.getName());
			FileOutputStream fout = new FileOutputStream(outfile); 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while ((len = zis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				byte[] bytes = baos.toByteArray();
				fout.write(bytes);
				baos.reset();
			}
			fout.close();
			zis.closeEntry();
			FileInputStream iis = new FileInputStream(outfile);
			Bitmap bitmap = BitmapFactory.decodeStream(iis);
			iis.close();
			showImage(bitmap, findex++);
		}
		zis.close();
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
	
	void showImage(final Bitmap bitmap, final int findex) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (findex == 1) {
					iv1.setImageBitmap(bitmap);
				} else {
					iv2.setImageBitmap(bitmap);
				}
			}
		});
	}
}
