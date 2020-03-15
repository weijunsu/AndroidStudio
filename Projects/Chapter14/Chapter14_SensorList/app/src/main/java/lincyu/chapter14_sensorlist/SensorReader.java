package lincyu.chapter14_sensorlist;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorReader extends Activity {

	SensorManager smgr;
	TextView tv_name, tv_reader;
	Sensor sensor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reader);

		tv_name = (TextView)findViewById(R.id.tv_name);
		tv_reader = (TextView)findViewById(R.id.tv_reader);

		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);

		Intent intent = getIntent();
		String name = intent.getStringExtra("NAME");
		int type = intent.getIntExtra("TYPE",
				Sensor.TYPE_ACCELEROMETER);

		List<Sensor> slist = smgr.getSensorList(type);
		for (int i = 0; i < slist.size(); i++) {
			if (slist.get(i).getName().equals(name)) {
				sensor = slist.get(i);
				break;
			}
		}
		String sensorinfo = "Name: " + sensor.getName() + "\n";
		sensorinfo = sensorinfo + "Type: " + sensor.getType() + "\n";
		tv_name.setText(sensorinfo);
	}

	@Override
	protected void onResume() {
		smgr.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		smgr.unregisterListener(listener, sensor);
		super.onPause();
	}

	SensorEventListener listener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged (SensorEvent event) {	
			String values = "";
			for (int i = 0; i < event.values.length; i++)
				values = values + "values[" + i + "]: " +
						event.values[i] + "\n";
			tv_reader.setText(values);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
}
