package lincyu.chapter7_calltrigger2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallReceiver extends BroadcastReceiver{	
	@Override
	public void onReceive(Context context, Intent intent) {

		Intent newintent = new Intent(intent);
		newintent.setAction("");
		newintent.setClass(context, SecondReceiver.class);
		context.sendBroadcast(newintent);
	}
}
