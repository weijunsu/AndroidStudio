package lincyu.chapter14_orientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
		implements SensorEventListener {

	SensorManager smgr;
	Sensor accel;
	Sensor compass;
	boolean baccel, bcompass = false;
	float[] accelValues = new float[3];
	float[] compassValues = new float[3];
	float[] rotationMatrix = new float[9];
	float[] values = new float[3];
	float azimuth;
	ArrowView arrow;
	TextView tv;
	LayoutParams params;
	Display display;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics metrics = new DisplayMetrics();
		display = getWindowManager().getDefaultDisplay();
		display.getMetrics(metrics);
		
		arrow = new ArrowView(this, metrics.widthPixels,
			metrics.heightPixels);
		
		tv = new TextView(this);
		tv.setTextSize((float)24.0);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		setContentView(arrow);
		addContentView(tv, params);
		
		baccel = false;
		bcompass = false;
		
		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
		accel = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		compass = smgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	}

	@Override
	protected void onResume() {
		smgr.registerListener(this, accel,
				SensorManager.SENSOR_DELAY_NORMAL);
		smgr.registerListener(this, compass,
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	protected void onPause() {
		smgr.unregisterListener(this, accel);
		smgr.unregisterListener(this, compass);
		super.onPause();
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch(event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			for (int i = 0; i < 3; i++) {
				accelValues[i] = event.values[i];
			}
			baccel = true;
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
        	for(int i=0; i<3; i++) {
				compassValues[i] = event.values[i];
			}
			bcompass = true;
			break;
		}
		if(!baccel || !bcompass)
			return;
		
		if(SensorManager.getRotationMatrix(rotationMatrix, null,
				accelValues, compassValues)) {
			
			int rotation = display.getRotation();
			if (rotation == Surface.ROTATION_90 ||
					rotation == Surface.ROTATION_270)
				SensorManager.remapCoordinateSystem(
						rotationMatrix, SensorManager.AXIS_X,
						SensorManager.AXIS_Z, rotationMatrix);
			
			SensorManager.getOrientation(rotationMatrix, values);
			update();
			baccel = false;
			bcompass = false;
		}
	}
	
	void update() {
		azimuth = (float) Math.toDegrees(values[0]);
		if(azimuth < 0) azimuth += 360.0f;
		String msg = "Azimuth: " + azimuth;

		float degree = 0-azimuth;
		if (degree <0) degree = degree + 360.0f;
		arrow.setDegree(degree);
		setContentView(arrow);
		
		tv.setText(msg);
		addContentView(tv, params);
	}
}
