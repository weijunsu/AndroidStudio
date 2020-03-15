package lincyu.chapter12_sharefile;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;

public class FileProvider extends ContentProvider {

	Context context;
	
	@Override
	public boolean onCreate() {
		context = getContext();
		return true;
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode) {
		try {
			File fileroot = context.getFilesDir();
			String fpath = fileroot.getPath() + "/share.txt";
			File file = new File(fpath);
			if (!file.exists()) {
				return null;
			}
			int imode = ParcelFileDescriptor.MODE_READ_ONLY;
			return ParcelFileDescriptor.open(file, imode);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection,
			String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return "";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}
}
