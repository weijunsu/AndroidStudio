package lincyu.chapter11_sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class MainActivity extends ActionBarActivity {

	SQLiteDatabase db;
	DBOpenHelper openhelper;
	
	String DATABASE_TABLE = "demotable";
	String DATABASE_CREATETABLE = "create table " +
			DATABASE_TABLE + "(number, title, body);";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		openhelper = new DBOpenHelper(this);
		db = openhelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("number", 1);
		cv.put("title", "abc");
		cv.put("body", "Hello abc!");
		db.insert(DATABASE_TABLE, null, cv);

		db.execSQL("insert into " + DATABASE_TABLE +
				" values(2," + "'xyz'" + "," + "'Hello xyz!'" + ");");

		Cursor c = db.rawQuery("select * from " +
				DATABASE_TABLE, null);

		String [] names = c.getColumnNames();

		for (int i = 0; i < names.length; i++)
			Log.d("LINCYU", "ColumnNames (" +
					c.getColumnIndex(names[i]) + "): " + names[i]);

		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			Log.d("LINCYU", "Title" + i + ": " +
					c.getString(c.getColumnIndex(names[1])));
			c.moveToNext();
		}

		cv = new ContentValues();
		cv.put("title", "def");
		db.update(DATABASE_TABLE, cv, "title=" + "'xyz'" , null);

		c = db.rawQuery("select * from " + DATABASE_TABLE, null);
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			Log.d("LINCYU", "Title(Update1)" + i + ": " +
					c.getString(c.getColumnIndex(names[1])));
			c.moveToNext();
		}
		c = db.rawQuery("select * from " + DATABASE_TABLE, null);

		db.execSQL("update " + DATABASE_TABLE +
				" set body=" + "'Hello def!'" +
				" where title=" + "'def'" + ";");
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			Log.d("LINCYU", "Body(Update2)" + i + ": " +
					c.getString(c.getColumnIndex(names[2])));
			c.moveToNext();
		}
		c.close();

		db.delete(DATABASE_TABLE, "title=" + "'abc'", null);
	}

	@Override
	public void onStop() {
		super.onStop();
		db.execSQL("drop table if exists " + DATABASE_TABLE );
		db.execSQL(DATABASE_CREATETABLE);
		db.close();
	}

	class DBOpenHelper extends SQLiteOpenHelper {		
		public DBOpenHelper(Context context) {
			super(context, "demo.db", null, 1);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATETABLE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		}
	}
}
