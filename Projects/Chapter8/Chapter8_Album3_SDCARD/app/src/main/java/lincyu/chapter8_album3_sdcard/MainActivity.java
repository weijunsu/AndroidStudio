package lincyu.chapter8_album3_sdcard;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
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
				albumlist);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v,
			int position, long id) {

		super.onListItemClick(l, v, position, id);
		
		int columns = 3;
		File sdroot = Environment.getExternalStorageDirectory();
		String rpath = sdroot.getPath();

		String [] imgfiles = null;
		switch(position) {
		case 0:
			columns = 2;
			imgfiles = new String[6];
			for (int i = 0; i < imgfiles.length; i++) {
				imgfiles[i] = rpath + "/hm00" + (i+1) + ".jpg";
			}
			break;
		case 1:
			imgfiles = new String[5];
			for (int i = 0; i < imgfiles.length; i++) {
				imgfiles[i] = rpath + "/sc00" + (i+1) + ".png";
			}
			break;
		}
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, GridActivity.class);
		intent.putExtra("KEY_FILES", imgfiles);
		intent.putExtra("KEY_COLUMNS", columns);
		startActivity(intent);
	}
}
