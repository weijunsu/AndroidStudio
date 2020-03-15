package lincyu.chapter12_notepadcr;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		
		Cursor c = cr.query(Uri.parse(
				"content://lincyu.noteprovider"),
				null, null, null, null);

		String msg = "";
		c.moveToFirst();
		for (int i = 1; i <= c.getCount(); i++) {
			String title = c.getString(c.getColumnIndex("title"));
			String body = c.getString(c.getColumnIndex("body"));
			msg = msg + "標題(" + i + ")：" + title + "\n";
			msg = msg + "內容(" + i + ")：" + body + "\n\n";
			c.moveToNext();
		}

		TextView tv = (TextView)findViewById(R.id.tv);
		tv.setText(msg);
	}
}
