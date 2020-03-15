package lincyu.chapter7_powermanager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class PowerManagerService extends Service {	
	
	BroadcastReceiver receiver;
	
	@Override
	public void onCreate() {
		receiver = new PowerManagerReceiver(
				this.getClass().getSimpleName());
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		IntentFilter ifilter = new IntentFilter();
		ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(receiver, ifilter);
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(receiver);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
