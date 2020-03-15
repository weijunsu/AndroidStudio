package lincyu.chapter8_album1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<String> albumlist = new ArrayList<String>();

		albumlist.add("蜜月旅行");
		albumlist.add("班表小幫手");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				// R.layout.listitem, R.id.tv_item,
				albumlist);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v,
			int position, long id) {

		super.onListItemClick(l, v, position, id);
	
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
		intent.setClass(MainActivity.this, GridActivity.class);
		intent.putExtra("KEY_IDS", imageIds);
		intent.putExtra("KEY_COLUMNS", columns);
		startActivity(intent);
	}
}
