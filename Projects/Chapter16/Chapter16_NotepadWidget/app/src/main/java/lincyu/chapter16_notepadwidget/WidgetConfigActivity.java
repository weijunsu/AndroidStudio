package lincyu.chapter16_notepadwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WidgetConfigActivity extends ActionBarActivity {
	
	ListView lv_notes;
	SQLiteDatabase db;
	ArrayList<String> titlelist;
	
	AppWidgetManager appWidgetManager;
	int widgetid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			widgetid = extras.getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		appWidgetManager = AppWidgetManager.getInstance(this);
		
		lv_notes = (ListView)findViewById(R.id.lv_notes);
		lv_notes.setOnItemClickListener(iclick);
	}
	
	OnItemClickListener iclick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> av, View v,
				int position, long id) {
			String title = titlelist.get(position);
			String body = NoteDB.getBody(db, title);
			
			NotepadWidget.update(appWidgetManager,
					WidgetConfigActivity.this.getPackageName(),
					title, body, widgetid);
			
			WidgetDB.addWidget(db, title, widgetid, true);
			
			Intent intent = new Intent();
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
					widgetid);
			setResult(RESULT_OK, intent);
			finish();
		}
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		db.close();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		DBOpenHelper openhelper = new DBOpenHelper(this);
		db = openhelper.getWritableDatabase();
		
		titlelist = NoteDB.getTitleList(db);
		ArrayAdapter<String> adapter =new ArrayAdapter<String>
			(this, android.R.layout.simple_list_item_1, titlelist);
		lv_notes.setAdapter(adapter);
	}
}
