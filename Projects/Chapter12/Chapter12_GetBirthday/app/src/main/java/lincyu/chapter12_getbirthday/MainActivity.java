package lincyu.chapter12_getbirthday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	final static String BACKACTION = "lincyu.GETBIRTHDAY";
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView)findViewById(R.id.tv);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(BACKACTION);
		registerReceiver(back, filter);
		
		Intent intent = new Intent();
		intent.setAction("lincyu.HOROSCOPE");
		intent.putExtra("BACKACTION", BACKACTION);
		sendBroadcast(intent);
	}
	
	BroadcastReceiver back = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			unregisterReceiver(back);
			String month = intent.getStringExtra("PREF_MONTH");
			int day = intent.getIntExtra("PREF_DAY", 1);
			tv.setText(month + "月" + day + "日");
		}
	};
}
