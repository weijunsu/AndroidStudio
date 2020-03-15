package lincyu.chapter15_ardemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity
		implements SensorEventListener, LocationListener {

	SurfaceHolder sh;
	TextView spotinfo;
	LocationManager lmgr;
	SensorManager smgr;
	Sensor accel;
	Sensor compass;
	Location user;
	Camera camera;
	boolean baccel = false, bcompass = false;
	float[] accelValues = new float[3];
	float[] compassValues = new float[3];
	float[] rotationMatrix = new float[9];
	float[] values = new float[3];
	float azimuth, target;
	boolean bazimuth = false, blocation = false;
	ArrayList<Spot> spotlist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.activity_main);
		
		spotinfo = new TextView(this);
		spotinfo.setTextSize((float)24.0);
		LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addContentView(spotinfo, params);

		SurfaceView sv = (SurfaceView)findViewById(R.id.sv);
		sh = sv.getHolder();
		sh.addCallback(new MySHCallback());

		lmgr = (LocationManager)getSystemService(LOCATION_SERVICE);
		
		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
		accel = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		compass = smgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		spotlist = new ArrayList<Spot>();
		spotlist.add(new Spot(25.033496, 121.563863, "Taipei 101"));
		spotlist.add(new Spot(24.786579, 120.998268, "浩然圖書館"));
	}

	class MySHCallback implements SurfaceHolder.Callback {
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			camera = Camera.open();

			if (camera == null) {
				finish();
				return;
			}
			
			try  {
				camera.setPreviewDisplay(holder);
			} catch (Exception e) {
				finish();
			}
		}
		
		@Override
		public void surfaceDestroyed(SurfaceHolder surfaceholder) {
			if (camera == null) return;
			camera.stopPreview();
			camera.release();
			camera = null;
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder surfaceholder,
				int format, int w, int h) {
			camera.startPreview();			
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		smgr.registerListener(this, accel,
				SensorManager.SENSOR_DELAY_NORMAL);
		smgr.registerListener(this, compass,
				SensorManager.SENSOR_DELAY_NORMAL);
		lmgr.requestLocationUpdates(
			LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		smgr.unregisterListener(this, accel);
		smgr.unregisterListener(this, compass);
		lmgr.removeUpdates(this);
		spotinfo.setText("");
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
		if(!baccel || !bcompass) return;
		
		if(SensorManager.getRotationMatrix(rotationMatrix, null,
				accelValues, compassValues)) {
			
			SensorManager.remapCoordinateSystem(
					rotationMatrix, SensorManager.AXIS_X,
					SensorManager.AXIS_Z, rotationMatrix);
			
			SensorManager.getOrientation(rotationMatrix, values);
			bazimuth = true;
			azimuth = (float) Math.toDegrees(values[0]);
			if(azimuth < 0) azimuth += 360.0f;
			update();
			baccel = false;
			bcompass = false;
		}
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (location == null) return;
		user = new Location(location);		
		blocation = true;
		update();
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider,
			int status, Bundle extras) {
	}

	class Spot {
		Spot(double latitude, double longitude, String name) {
			this.latitude = latitude;
			this.longitude = longitude;
			this.name = name;
		}
		double latitude;
		double longitude;
		String name;
	}

	void update() {
		String info = "";
		if (!blocation) {
			spotinfo.setText("無法獲得GPS位置資訊");
			return;
		}
		if (!bazimuth) {
			spotinfo.setText("無法讀取感測器");
			return;
		}

		for (int i = 0; i < spotlist.size(); i++) {
			Spot spot = spotlist.get(i);
			Location spotloc = new Location(user);
			spotloc.setLatitude(spot.latitude);
			spotloc.setLongitude(spot.longitude);
			
			target = user.bearingTo(spotloc);
			
			float dist = user.distanceTo(spotloc);
			if (dist > 20000.0) continue;
			
			float degree = target - azimuth;
			if (degree < 0.0) {
				degree = degree + 360.0f;
			}
			if (degree > 180.0) {
				degree = 360.0f - degree;
			}
			if (degree <= 20.0) {
				info = info + spot.name + "\n" + dist + "公尺\n";
			}
		}
		spotinfo.setText(info);
	}
}