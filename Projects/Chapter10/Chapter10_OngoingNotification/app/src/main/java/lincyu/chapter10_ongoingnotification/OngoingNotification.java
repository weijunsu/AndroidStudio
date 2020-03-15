package lincyu.chapter10_ongoingnotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OngoingNotification extends Service {

	private static final int SERVICE_ID = 7777;

	@Override
	public void onCreate() {
	}

	@Override
	public int onStartCommand (Intent intent, int flags, int startId) {
		
		Intent activityintent = new Intent();
		activityintent.setClass(this, MainActivity.class);
		activityintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		PendingIntent pi = PendingIntent.getActivity(this,
				0, activityintent, 0);
		
		Notification notify = null;
		notify = oldNotification(pi, "Title", "Message");
		if (notify != null) {
			startForeground(SERVICE_ID, notify);
		}
	    return(START_NOT_STICKY);	
	}
	
	@SuppressWarnings("deprecation")
	private Notification oldNotification(PendingIntent pi,
			String title, String msg) {
		
		Notification notify = new Notification(
				R.mipmap.ic_launcher,
				title, System.currentTimeMillis());	
		notify.setLatestEventInfo(this, title, msg, pi);
		return notify;
	}
	
	@Override
	public void onDestroy() {
		this.stopForeground(true);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
