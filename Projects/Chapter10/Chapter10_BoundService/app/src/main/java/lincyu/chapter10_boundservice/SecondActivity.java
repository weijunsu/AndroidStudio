package lincyu.chapter10_boundservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity{
	
	ServiceConn conn;
	MyBinder binder;
	TextView tv_number;
	int number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		conn = new ServiceConn();
		Intent intent = new Intent();
		intent.setClass(this, BoundService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
		
		Button btn = (Button)findViewById(R.id.setnumber);
		btn.setOnClickListener(setnumber);
		tv_number = (TextView)findViewById(R.id.tv_number);
	}
	
	OnClickListener setnumber = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (binder == null) return;
			binder.setNumber(number+2);
			unbindService(conn);
			binder = null;
			finish();
		}
	};
		
	class ServiceConn implements ServiceConnection {
		
		public void onServiceConnected(ComponentName classname,
				IBinder service) {
			binder = (MyBinder)service;
			number = binder.getNumber();
			tv_number.setText("" + number);
		}
		public void onServiceDisconnected(ComponentName classname) {
			binder = null;
		}
	}
}
