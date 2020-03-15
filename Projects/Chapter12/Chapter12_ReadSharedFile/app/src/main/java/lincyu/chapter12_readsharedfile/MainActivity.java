package lincyu.chapter12_readsharedfile;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileInputStream;

public class MainActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StringBuilder str = new StringBuilder("");
		try {
			FileInputStream fin = getFIS(this);
			do {
				int c = fin.read();
				if (c == -1) break;
				str.append((char)c);
			} while (true);
		} catch (Exception e) {
		}
		
		TextView tv = (TextView)findViewById(R.id.tv);   
		tv.setText(str);
	}

	FileInputStream getFIS(Context context) {
		
		ContentResolver resolver = getContentResolver();
		try {
			Uri uri =
				Uri.parse("content://lincyu.sharefile.fileprovider");
			ParcelFileDescriptor pfd = resolver.
				openFileDescriptor(uri, "r");
			FileDescriptor fd = pfd.getFileDescriptor();
			FileInputStream fis = new FileInputStream(fd);
			return fis;
		} catch (Throwable t) {
			return null;
		}
	}
}
