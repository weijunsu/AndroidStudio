package lincyu.chapter7_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BR_Activity extends BroadcastReceiver{	
	@Override
	public void onReceive(Context context, Intent intent) {

		String msg = intent.getStringExtra("KEY_MSG");

		Intent newintent = new Intent();
		newintent.setClass(context, ActivityDialog.class);
		newintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		newintent.putExtra("KEY_MSG", msg);
		context.startActivity(newintent);
	}
}
