package lincyu.chapter7_localbroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SenderActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sender);
		Button btn = (Button)findViewById(R.id.btn_sendbroadcast);
		btn.setOnClickListener(sendb);
	}

    LocalBroadcastManager lbm;
    SelfReceiver receiver;
    final static String ACTION = "LOCAL_BROADCAST";

	private OnClickListener sendb = new OnClickListener () {
		public void onClick(View v) {
			Intent intent = new Intent();
            intent.setAction(ACTION);
			intent.putExtra("KEY_MSG", "Local Broadcast");
            lbm.sendBroadcast(intent);
		}
	};

    @Override
    public void onResume() {
        super.onResume();
        receiver = new SelfReceiver();
        lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        lbm.registerReceiver(receiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        lbm.unregisterReceiver(receiver);
    }
}
