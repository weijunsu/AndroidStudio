package lincyu.chapter9_intentmap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.latlngzoom);
		btn.setOnClickListener(b1);
		btn = (Button)findViewById(R.id.address);
		btn.setOnClickListener(b2);
		btn = (Button)findViewById(R.id.name);
		btn.setOnClickListener(b3);
		btn = (Button)findViewById(R.id.customized);
		btn.setOnClickListener(b4);
		btn = (Button)findViewById(R.id.navigation);
		btn.setOnClickListener(b5);
		
	}
	
	OnClickListener b1 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("geo:25.033496, 121.563863?z=18");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};
	
	OnClickListener b2 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("geo:0,0?q=新竹市大學路1001號");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};
	
	OnClickListener b3 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("geo:0,0?q=高鐵新竹站");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};
	
	OnClickListener b4 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("geo:0,0?q=25.033496,121.563863(我不在這上班)");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};
	
	OnClickListener b5 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("http://maps.google.com/maps?" +
					"saddr=25.033496,121.563863&" +
					"daddr=24.789481,120.998998");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};	
}
