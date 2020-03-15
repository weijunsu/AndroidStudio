package lincyu.chapter16_notepadwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RemoteViews;

public class NotepadWidget extends AppWidgetProvider {

	static void update(AppWidgetManager appWidgetManager,
			String pkg, String title, String body, int widgetid) {

		RemoteViews views = new RemoteViews(pkg,
				R.layout.widget_layout);
		views.setTextViewText(R.id.tv_title, title);
		views.setTextViewText(R.id.tv_body, body);
		appWidgetManager.updateAppWidget(widgetid, views);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		
		String action = intent.getAction();
		if(!"NPWIDGET_UPDATE".equals(action)) return;

		AppWidgetManager appWidgetManager =
				AppWidgetManager.getInstance(context);
			
		ComponentName thisWidget = new
				ComponentName(context.getPackageName(),
				NotepadWidget.class.getName());
		
		int [] appWidgetIds = appWidgetManager.
				getAppWidgetIds(thisWidget);
		onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onUpdate(Context context,
			AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		DBOpenHelper openhelper = new DBOpenHelper(context);
		SQLiteDatabase db = openhelper.getWritableDatabase();
		for (int i = 0; i < appWidgetIds.length; i++) {
			int widgetid = appWidgetIds[i];
			String title = WidgetDB.getTitle(db, widgetid);
			if (title == null) continue;
			String body = NoteDB.getBody(db, title);
			update(appWidgetManager, context.getPackageName(),
					title, body, widgetid);
		}
		db.close();
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		DBOpenHelper openhelper = new DBOpenHelper(context);
		SQLiteDatabase db = openhelper.getWritableDatabase();
		for (int i = 0; i < appWidgetIds.length; i++)
			WidgetDB.delWidget(db, appWidgetIds[i]);
		db.close();
	}
}
