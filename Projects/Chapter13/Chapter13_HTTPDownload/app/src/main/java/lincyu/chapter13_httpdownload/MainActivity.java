package lincyu.chapter13_httpdownload;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends ActionBarActivity {
	
	ImageView iv1, iv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv1 = (ImageView)findViewById(R.id.iv1);
		iv2 = (ImageView)findViewById(R.id.iv2);
		
		Button btn_dpdf = (Button)findViewById(R.id.btn_dpdf);
		btn_dpdf.setOnClickListener(dpdf);
		
		Button btn_opdf = (Button)findViewById(R.id.btn_opdf);
		btn_opdf.setOnClickListener(opdf);
		
		Button btn_dzip = (Button)findViewById(R.id.btn_dzip);
		btn_dzip.setOnClickListener(dzip);
	}
	
	OnClickListener dpdf = new OnClickListener() {
		@Override
		public void onClick(View v) {
			DownloadPDF thread = new DownloadPDF(MainActivity.this);
			thread.start();
		}
	};
	
	OnClickListener opdf = new OnClickListener() {
		@Override
		public void onClick(View v) {
			File sdroot = Environment.
				getExternalStorageDirectory();
			File pdf = new File(sdroot, "download.pdf");
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(pdf), "application/pdf");
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			try {
				startActivity(intent);
			} catch (Exception e) {
				Toast.makeText(MainActivity.this, "無法開啟PDF",
					Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	OnClickListener dzip = new OnClickListener() {
		@Override
		public void onClick(View v) {
			DownloadZIP thread = new DownloadZIP(
					MainActivity.this, iv1, iv2);
			thread.start();
		}
	};
}
