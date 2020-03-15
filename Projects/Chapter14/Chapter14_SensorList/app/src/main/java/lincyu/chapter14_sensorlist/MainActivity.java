package lincyu.chapter14_sensorlist;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

	SensorManager smgr;
	List<Sensor> slist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
		slist = smgr.getSensorList(Sensor.TYPE_ALL);
		ArrayList<String> snlist = new ArrayList<String>();

		for (int i = 0; i < slist.size(); i++) {
			snlist.add(slist.get(i).getName());
		}

		ListAdapter adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, snlist);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v,
			int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		
		Sensor sensor = slist.get(position);
		Intent intent = new Intent(this, SensorReader.class);
		intent.putExtra("TYPE", sensor.getType());
		intent.putExtra("NAME", sensor.getName());
		startActivity(intent);
	}
}
