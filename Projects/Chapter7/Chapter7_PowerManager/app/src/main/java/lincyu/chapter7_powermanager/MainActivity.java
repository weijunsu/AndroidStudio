package lincyu.chapter7_powermanager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	BroadcastReceiver receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		receiver = new PowerManagerReceiver(
				this.getLocalClassName());
		
		IntentFilter ifilter = new IntentFilter();
		ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(receiver, ifilter);
		
		Button btn_service = (Button)
				findViewById(R.id.btn_startservice);
		btn_service.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,
						PowerManagerService.class);
				startService(intent);
				finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}	
}
