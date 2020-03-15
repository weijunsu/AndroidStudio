package lincyu.chapter13_ftpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.apache.commons.net.ftp.FTPClient;

public class FTPActivity extends ActionBarActivity {

	FTPClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ftp);

		Intent intent = getIntent();
		String site = intent.getStringExtra("FTPSITE");
		String user = intent.getStringExtra("USERNAME");
		String pwd = intent.getStringExtra("PASSWORD");

		client = new FTPClient();
		
		LoginThread thread = new LoginThread(this, client,
				site, user, pwd);
		thread.start();

		Button upload = (Button)findViewById(R.id.upload);
		upload.setOnClickListener(upload_l);
		Button down = (Button)findViewById(R.id.download);
		down.setOnClickListener(download_l);
	}
	
	OnClickListener upload_l = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (client.isConnected() == false)
				return;
			UploadThread thread = new UploadThread(
					FTPActivity.this, client);
			thread.start();
		}
	};

	OnClickListener download_l = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (client.isConnected() == false)
				return;
			DownloadThread thread = new DownloadThread(
					FTPActivity.this, client);
			thread.start();
		}
	};
}
