package lincyu.chapter7_powermanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class PowerManagerReceiver extends BroadcastReceiver {

	String generator;
	
	PowerManagerReceiver(String generator) {
		this.generator = generator;
	}
	
	public PowerManagerReceiver() {
		super();
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();

		if (action.equals(Intent.ACTION_BATTERY_LOW)) {
			
			Log.d("LINCYU", "PowerManager: Saving Mode");
			Toast.makeText(context, "進入省電模式",
					Toast.LENGTH_LONG).show();
			
		} else if (action.equals(Intent.ACTION_BATTERY_OKAY)) {
			
			Log.d("LINCYU", "PowerManager: Normal Mode");
			Toast.makeText(context, "進入一般模式",
					Toast.LENGTH_LONG).show();
			
		} else if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
			
			int level = intent.getIntExtra(
					BatteryManager.EXTRA_LEVEL, -1);
			Toast.makeText(context, "Level: " + level +
					" , Generator: " + generator,
					Toast.LENGTH_LONG).show();
			
		} else {
			Toast.makeText(context, "收到奇怪的廣播",
					Toast.LENGTH_LONG).show();
		}
	}
}
