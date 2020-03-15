package lincyu.chapter14_accelerometer;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ActionBarActivity {

	float max;
	TextView tv;
	SensorManager smgr;
	List<Sensor> slist;
	boolean isStarted;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		tv = (TextView)findViewById(R.id.tv_result);
		Button start = (Button)findViewById(R.id.start);
		start.setOnClickListener(start_l);
		Button stop = (Button)findViewById(R.id.stop);
		stop.setOnClickListener(stop_l);

		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);

		slist = smgr.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (slist.size() == 0) {
			Toast.makeText(this, "No accelerometer sensor",
					Toast.LENGTH_SHORT).show();
			finish();
		}
		isStarted = false;
	}

	SensorEventListener listener = new SensorEventListener() {
		
		public void onSensorChanged (SensorEvent event) {
			if (isStarted == false) return;
	
			float force = (float)0.0;
				
			force += (float)Math.pow(event.values[0]/
					SensorManager.GRAVITY_EARTH, 2.0);
			force += (float)Math.pow(event.values[1]/
					SensorManager.GRAVITY_EARTH, 2.0);
			force += (float)Math.pow(event.values[2]/
					SensorManager.GRAVITY_EARTH, 2.0);
			force = (float)Math.sqrt(force);

			tv.setText("Force: " + force);
			if (force > max) {
				max = force;
			}
		}

		public void onAccuracyChanged (Sensor sensor, int accuracy) {
		}		
	};

	OnClickListener start_l = new OnClickListener() {
		public void onClick(View v) {
			if (isStarted == true) return;
			isStarted = true;
			max = (float)0.0;
			smgr.registerListener(listener, slist.get(0),
					SensorManager.SENSOR_DELAY_UI);
			tv.setText("");
		}
	};

	OnClickListener stop_l = new OnClickListener() {
		public void onClick(View v) {
			if (isStarted == false) return;
			isStarted = false;
			smgr.unregisterListener(listener, slist.get(0));
			String msg = "Max Value: " + max + "\n";
			if (max < 3.0) {
				tv.setText(msg + "Fail! Try again!");
			} else {
				tv.setText(msg + "Succefully!");
			}
		}
	};
}
