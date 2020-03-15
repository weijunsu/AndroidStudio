package lincyu.chapter12_contactemail;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Data;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();

		Cursor c = cr.query(Email.CONTENT_URI,
			null, null, null, null);
		
		ArrayList<EmailItem> emails = new ArrayList<EmailItem>();
		
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			String name = c.getString(c.getColumnIndex(
					Data.DISPLAY_NAME));
			String email = c.getString(c.getColumnIndex(
					Email.DATA1));
			emails.add(new EmailItem(name, email));
			c.moveToNext();
		}
		
		ContactArrayAdapter adapter = new ContactArrayAdapter
				(this, emails);

		ListView lv = (ListView)findViewById(R.id.lv);
		lv.setAdapter(adapter);
	}
}
