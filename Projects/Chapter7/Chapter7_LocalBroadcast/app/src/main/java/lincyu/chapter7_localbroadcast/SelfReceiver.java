package lincyu.chapter7_localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SelfReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String msg = intent.getStringExtra("KEY_MSG");
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
