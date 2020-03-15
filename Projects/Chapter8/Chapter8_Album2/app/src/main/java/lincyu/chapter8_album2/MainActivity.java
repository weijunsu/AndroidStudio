package lincyu.chapter8_album2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<AlbumItem> albumlist = new ArrayList<AlbumItem>();

		albumlist.add(new AlbumItem("蜜月旅行",R.drawable.hm001));
		albumlist.add(new AlbumItem("班表小幫手",R.drawable.scicon));
		
		AlbumArrayAdapter adapter =
			new AlbumArrayAdapter(this, albumlist);
		
		lv = (ListView)findViewById(R.id.lv);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(itemclick);
	}
	
	OnItemClickListener itemclick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> av, View v,
				int position, long id) {
			
			int [] imageIds = null;
			int columns = 3;
			switch(position) {
			case 0:
				imageIds = new int[6];
				imageIds[0] = R.drawable.hm001;
				imageIds[1] = R.drawable.hm002;
				imageIds[2] = R.drawable.hm003;
				imageIds[3] = R.drawable.hm004;
				imageIds[4] = R.drawable.hm005;
				imageIds[5] = R.drawable.hm006;
				columns = 2;
				break;
			case 1:
				imageIds = new int[5];
				imageIds[0] = R.drawable.sc001;
				imageIds[1] = R.drawable.sc002;
				imageIds[2] = R.drawable.sc003;
				imageIds[3] = R.drawable.sc004;
				imageIds[4] = R.drawable.sc005;
				break;
			}
			Intent intent = new Intent();
			intent.setClass(MainActivity.this,
					GridActivity.class);
			intent.putExtra("KEY_IDS", imageIds);
			intent.putExtra("KEY_COLUMNS", columns);
			startActivity(intent);
		}
	};
}
