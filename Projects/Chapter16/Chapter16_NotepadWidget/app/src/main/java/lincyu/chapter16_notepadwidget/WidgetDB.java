package lincyu.chapter16_notepadwidget;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class WidgetDB {
	
	final static String WIDGETTABLE = "widgettable";

	static void addWidget(SQLiteDatabase db, String title,
			int widgetid, boolean isNew) {

		ContentValues cv = new ContentValues();
		cv.put("title", title);
		cv.put("widgetid", widgetid);

		if (isNew == true) {
			db.insert(WIDGETTABLE, null, cv);
		} else {
			db.update(WIDGETTABLE, cv,
					"widgetid=" + widgetid, null);
		}
	}
	
	static void delWidget(SQLiteDatabase db, int widgetid) {
		db.delete(WIDGETTABLE,
				"widgetid=" + widgetid, null);
	}
	
	static String getTitle(SQLiteDatabase db, int widgetid) {
		Cursor c = db.rawQuery("select title from " +
				WIDGETTABLE + " where widgetid=" + widgetid + ";", null);
		c.moveToFirst();	
		String title = null;
		if (c.getCount() > 0) {
			int titleIndex = c.getColumnIndex("title");
			title = c.getString(titleIndex);
		}
		c.close();
		return title;
	}
}
