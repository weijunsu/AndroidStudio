package lincyu.chapter12_notepadcp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class NoteProvider extends ContentProvider {

	SQLiteDatabase db;
	DBOpenHelper openhelper;

	@Override
	public boolean onCreate() {
		Context context = getContext();
		openhelper = new DBOpenHelper(context);
		db = null;
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection,
			String selection, String[] selectionArgs,
			String sortOrder) {
		
		if (db == null)
			db = openhelper.getWritableDatabase();
		Cursor c = db.query(NoteDB.NOTETABLE, projection,
				selection, selectionArgs, null, null, sortOrder);
		return c;
	}

	@Override
	public int update (Uri uri, ContentValues values,
			String selection, String[] selectionArgs) {
		return -1;
	}
	
	@Override
	public Uri insert (Uri uri, ContentValues values) {
		return null;
	}
	
	@Override
	public int delete (Uri uri, String selection,
			String[] selectionArgs) {
		return -1;
	}
	
	@Override
	public String getType(Uri uri) {
		return "";
	}
}
