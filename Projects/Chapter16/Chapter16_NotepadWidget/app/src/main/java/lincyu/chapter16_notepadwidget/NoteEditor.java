package lincyu.chapter16_notepadwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NoteEditor extends ActionBarActivity {
	
	EditText et_title, et_body;
	ArrayList<String> titlelist;
	SQLiteDatabase db;
	int notepos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noteeditor);
		et_title = (EditText)findViewById(R.id.et_title);
		et_body = (EditText)findViewById(R.id.et_body);
        
		Intent intent = getIntent();
		notepos = intent.getIntExtra("NOTEPOS", -1);		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		DBOpenHelper openhelper = new DBOpenHelper(this);
		db = openhelper.getWritableDatabase();
		
		titlelist = NoteDB.getTitleList(db);

		if (notepos != -1) {
			String title = titlelist.get(notepos);
			et_title.setText(title);
			et_body.setText(NoteDB.getBody(db, title));
		} else {
			et_title.setText("");
			et_body.setText("");
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		String title = et_title.getText().toString();
		if (title.length() == 0) {
			Toast.makeText(this, "標題不能為空白，便條無儲存",
					Toast.LENGTH_LONG).show();
		} else {
			NoteDB.addNote(db, et_title.getText().toString(),
				et_body.getText().toString());
			
			Calendar c = Calendar.getInstance();
			Intent intent = new Intent();
			intent.setAction("NPWIDGET_UPDATE");
			PendingIntent pendingIntent = PendingIntent.
					getBroadcast(this, 0, intent, 0);
			AlarmManager alarmManager = (AlarmManager)
					getSystemService(Context.ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP,
					c.getTimeInMillis(), pendingIntent);
		}
	}

	boolean isTitleExist(String title) {
		for (int i = 0; i < titlelist.size(); i++)
			if (title.equalsIgnoreCase(titlelist.get(i)))
				return true;
		return false;
	}
}
