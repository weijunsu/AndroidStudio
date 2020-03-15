package lincyu.chapter13_culture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

public class FacilityActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		String category = intent.getStringExtra("NAME");
		setTitle(category);
		int type = intent.getIntExtra("TYPE", 10);
		
		ListView lv = (ListView)findViewById(R.id.lv);
		
		ConnThread thread = new ConnThread(this, lv, type);
		thread.start();
	}
}
