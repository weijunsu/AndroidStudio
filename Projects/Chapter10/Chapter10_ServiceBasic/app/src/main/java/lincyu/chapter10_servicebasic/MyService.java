package lincyu.chapter10_servicebasic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {	
	
	@Override
	public void onCreate() {
		Log.d("LINCYU", "Service: onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("LINCYU", "Service: onStartCommand");
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					for (int j = 0; j < 10000000; j++);
					Log.v("LINCYU", "Hello " + i);
				}
			}
		};
		// t.run();
		t.start();
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Log.d("LINCYU", "Service: onDestory");
	}
	
	@Override
	public IBinder onBind(Intent i) {
		Log.d("LINCYU", "Service: onBind");
		Binder binder = new Binder();
		return binder;
	}
	
	@Override
	public boolean onUnbind(Intent i) {
		Log.d("LINCYU", "Service: onUnbind");
		return false;
	}
}
