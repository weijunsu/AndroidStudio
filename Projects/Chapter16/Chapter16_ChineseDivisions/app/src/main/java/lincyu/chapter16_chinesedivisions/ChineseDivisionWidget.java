package lincyu.chapter16_chinesedivisions;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;

public class ChineseDivisionWidget extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		
		String action = intent.getAction();
		if(!"CDWIDGET_UPDATE".equals(action)) return;

		AppWidgetManager appWidgetManager =
				AppWidgetManager.getInstance(context);
			
		ComponentName thisWidget = new
				ComponentName(context.getPackageName(),
				ChineseDivisionWidget.class.getName());
		int [] appWidgetIds = appWidgetManager.
				getAppWidgetIds(thisWidget);
		onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onUpdate(Context context,
			AppWidgetManager appWidgetManager, int[] appWidgetIds) {
				
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int chineseIndex = -1;
		if (hour%2 == 0)
			chineseIndex = hour/2-1;
		else
			chineseIndex = (hour-1)/2;
		
		int addhour = 1;
		if (hour%2 != 0) addhour = 2;
		c.add(Calendar.HOUR_OF_DAY, addhour);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Intent intent = new Intent();
		intent.setAction("CDWIDGET_UPDATE");
		PendingIntent pendingIntent = PendingIntent.
				getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager)
				context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				c.getTimeInMillis(), pendingIntent);
		
		RemoteViews remoteviews = new
				RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		remoteviews.setTextViewText(R.id.tv_widget_text,
				"現在是" + division[chineseIndex] + "時");
		final int n = appWidgetIds.length;
		int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
		for(int i = 0; i < n; i++) {
			appWidgetId = appWidgetIds[i];
			appWidgetManager.updateAppWidget(appWidgetId,
					remoteviews);
		}
	}

	String [] division = {"丑", "寅", "卯", "辰", "巳",
			"午", "未", "申", "酉", "戌", "亥", "子"};
}
