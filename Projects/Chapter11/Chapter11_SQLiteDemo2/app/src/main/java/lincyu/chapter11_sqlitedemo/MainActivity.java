package lincyu.chapter11_sqlitedemo;

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
			DATABASE_TABLE + "(number, title, body, date);";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		openhelper = new DBOpenHelper(this);
		db = openhelper.getWritableDatabase();

		Log.d("LINCYU", "DB Version: " + db.getVersion());
		
		Cursor c = db.rawQuery("select * from " +
				DATABASE_TABLE, null);
		String [] names = c.getColumnNames();
		for (int i = 0; i < names.length; i++)
			Log.d("LINCYU", "ColumnNames (" +
					c.getColumnIndex(names[i]) + "): " + names[i]);
	}

	@Override
	public void onStop() {
		super.onStop();
		db.close();
	}

	class DBOpenHelper extends SQLiteOpenHelper {		
		public DBOpenHelper(Context context) {
			super(context, "demo.db", null, 2);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATETABLE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
			Log.d("LINCYU", "onUpgrade: " + oldV + " -> " + newV);
			db.execSQL("alter table " + DATABASE_TABLE +
				" add column date;");
		}
	}
}
