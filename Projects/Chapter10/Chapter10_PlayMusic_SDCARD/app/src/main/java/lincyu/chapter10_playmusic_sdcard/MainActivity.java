package lincyu.chapter10_playmusic_sdcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn_start = (Button)findViewById(R.id.start);
		btn_start.setOnClickListener(start_l);
		Button btn_stop = (Button)findViewById(R.id.stop);
		btn_stop.setOnClickListener(stop_l);
		Button btn_pause = (Button)findViewById(R.id.pause);
		btn_pause.setOnClickListener(pause_l);        
	}
	
	private OnClickListener start_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MusicService.class);
			intent.putExtra("KEY_ISPAUSE", false);
			startService(intent);
		}
	};

	private OnClickListener stop_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MusicService.class);
			stopService(intent);
		}
	};

	private OnClickListener pause_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MusicService.class);
			intent.putExtra("KEY_ISPAUSE", true);
			startService(intent);
		}
	};
}
