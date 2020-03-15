package lincyu.chapter7_broadcastreceiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BR_Notification extends BroadcastReceiver{	
	
	static int id = 70000;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String msg = intent.getStringExtra("KEY_MSG");
		
		Intent newintent = new Intent();
		newintent.setClass(context, ActivityNotification.class);
		PendingIntent pendingIntent = PendingIntent.
				getActivity(context, 0, newintent, 0);
		
		Notification notify = null;
		if (Build.VERSION.SDK_INT >= 16) {
			notify = newNotification(context, pendingIntent,
					"(New) Broadcast is received.", msg);
		} else {
			notify = oldNotification(context, pendingIntent,
					"(Old) Broadcast is received.", msg);
		}
				
		NotificationManager notificationManager =
				(NotificationManager)context.
				getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(id++, notify);
	}
		
	@SuppressWarnings("deprecation")
	private Notification oldNotification(
			Context context, PendingIntent pi,
			String title, String msg) {
			
		Notification notify = new Notification(
			R.mipmap.ic_launcher, title,
			System.currentTimeMillis());
		notify.setLatestEventInfo(context, title, msg, pi);
		return notify;
	}

	@SuppressLint("NewApi")
	private Notification newNotification(
			Context context, PendingIntent pi,
			String title, String msg) {
		
		Notification.Builder builder =
				new Notification.Builder(context);
		builder.setContentTitle(title);
		builder.setContentText(msg);
		builder.setSmallIcon(R.mipmap.ic_launcher);
		builder.setContentIntent(pi);
		builder.setTicker(msg);
		builder.setWhen(System.currentTimeMillis());
		Notification notify = builder.build();
		return notify;		
	}
}
