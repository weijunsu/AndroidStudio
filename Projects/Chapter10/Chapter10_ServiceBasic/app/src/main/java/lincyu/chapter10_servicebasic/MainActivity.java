package lincyu.chapter10_servicebasic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	ServiceConnection serviceConn;
	boolean isBound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		serviceConn = new MyServiceConn();
		isBound = false;

		Button btn_startS = (Button)findViewById(R.id.startS);
		btn_startS.setOnClickListener(startS_l);
		Button btn_stopS = (Button)findViewById(R.id.stopS);
		btn_stopS.setOnClickListener(stopS_l);
		Button btn_bindS = (Button)findViewById(R.id.bindS);
		btn_bindS.setOnClickListener(bindS_l);
		Button btn_unbindS = (Button)findViewById(R.id.unbindS);
		btn_unbindS.setOnClickListener(unbindS_l);
	}
	
	private OnClickListener startS_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MyService.class);
			startService(intent);
		}
	};

	private OnClickListener stopS_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MyService.class);
			stopService(intent);
		}
	};
	
	private OnClickListener bindS_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MyService.class);
			bindService(intent, serviceConn,
					Context.BIND_AUTO_CREATE);
		}
	};
	
	private OnClickListener unbindS_l = new OnClickListener() {
		public void onClick(View v) {
			if (isBound == true) {
				unbindService(serviceConn);
				isBound = false;
			}
		}
	};

	class MyServiceConn implements ServiceConnection {
		public void onServiceConnected(ComponentName classname,
				IBinder service) {
			isBound = true;
			Log.d("LINCYU", "Activity: onServiceConnected");
		}

		public void onServiceDisconnected(ComponentName classname) {
		}
	}
}
