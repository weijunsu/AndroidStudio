package lincyu.chapter7_calltrigger1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver{	
	@Override
	public void onReceive(Context context, Intent intent) {

		String number = intent.getStringExtra(
				TelephonyManager.EXTRA_INCOMING_NUMBER);
		String state = intent.getStringExtra(
				TelephonyManager.EXTRA_STATE);

		Toast.makeText(context, "State: " + state,
				Toast.LENGTH_SHORT).show();
		
		if (!state.equals(TelephonyManager.EXTRA_STATE_RINGING))
			return;
		
		Intent newintent = new Intent();
		newintent.setClass(context, IncomingActivity.class);
		newintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		newintent.putExtra(TelephonyManager.EXTRA_INCOMING_NUMBER,
				number);
		context.startActivity(newintent);
	}
}
