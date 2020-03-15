package lincyu.chapter7_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BR_Toast extends BroadcastReceiver {	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String msg = intent.getStringExtra("KEY_MSG");
		
		Toast.makeText(context, "Received message: " + msg,
				Toast.LENGTH_SHORT).show();
	}
}
