package lincyu.chapter7_nonuithread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;



public class UIThreadActivity extends ActionBarActivity {

	final static String ACTION_CHANGETEXT = "lincyu.CHANGETEXT";
	final static String EXTRA_MSG = "EXTRA_MSG";
	final static String EXTRA_END = "EXTRA_END";

	TextView tv_msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uithread);
		
		tv_msg = (TextView)findViewById(R.id.tv_msg);
		
		IntentFilter ifilter = new IntentFilter();
		ifilter.addAction(ACTION_CHANGETEXT);
		registerReceiver(receiver, ifilter);
		
		NonUIThread t = new NonUIThread(this);
		t.start();
	}
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra(EXTRA_MSG);
			boolean end = intent.getBooleanExtra(EXTRA_END, false);
			if (end)
				unregisterReceiver(receiver);
			tv_msg.setText(msg);
		}
	};
}
