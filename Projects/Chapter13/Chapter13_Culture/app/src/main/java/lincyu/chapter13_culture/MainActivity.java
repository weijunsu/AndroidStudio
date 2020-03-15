package lincyu.chapter13_culture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	ArrayList<Category> items;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		items = new ArrayList<Category>();
		
		items.add(new Category("展演空間", 10));
		items.add(new Category("藝文中心", 11));
		items.add(new Category("創意園區", 13));
		items.add(new Category("藝術村", 14));

		ArrayAdapter<Category> adapter =
				new ArrayAdapter<Category>(
				this, android.R.layout.simple_list_item_1,
				items);
		
		ListView lv = (ListView)findViewById(R.id.lv);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(itemclick);
	}
	
	OnItemClickListener itemclick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> av, View v,
				int position, long id) {
			Category category = items.get(position);
			Intent intent = new Intent();
			intent.setClass(MainActivity.this,
					FacilityActivity.class);
			intent.putExtra("NAME", category.name);
			intent.putExtra("TYPE", category.type);
			startActivity(intent);
		}
	};
	
}
