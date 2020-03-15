package lincyu.chapter15_basiccamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends Activity {

	SurfaceView sv;
	SurfaceHolder sh;
	Camera camera;
	int facing;

	TextView tv;
	Button takepicture, switchcamera, viewpicture;
	LinearLayout ll;

	MySensorListener sensorlistener;
	SensorManager smgr;
	Sensor sensor;
	
	Display display;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		display = getWindowManager().getDefaultDisplay();

		Intent intent = getIntent();
		facing = intent.getIntExtra("FACING",
				CameraInfo.CAMERA_FACING_BACK);
		
		setContentView(R.layout.activity_main);

		sv = (SurfaceView)findViewById(R.id.sv);
		sh = sv.getHolder();
		sh.addCallback(new MySHCallback());

		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
		sensorlistener = new MySensorListener();

		sensor = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		LayoutInflater inflater = LayoutInflater.from(this);
		ll = (LinearLayout)inflater.inflate(
				R.layout.addviews, null);
		tv = (TextView)ll.findViewById(R.id.tv_camerastatus);
		takepicture = (Button)ll.findViewById(R.id.btn_tp);
		takepicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (camera != null) {
					camera.takePicture(null, null, jpeg);
				}
			}
		});
		switchcamera = (Button)ll.findViewById(R.id.btn_sc);
		switchcamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (facing == CameraInfo.CAMERA_FACING_BACK)
					facing = CameraInfo.CAMERA_FACING_FRONT;
				else
					facing = CameraInfo.CAMERA_FACING_BACK;
				camera.stopPreview();
				camera.release();
				camera = null;
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,
						MainActivity.class);
				intent.putExtra("FACING", facing);
				startActivity(intent);
				finish();
			}
		});
		viewpicture = (Button)ll.findViewById(R.id.btn_vp);
		viewpicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,
						ViewPicture.class);
				startActivity(intent);
			}
		});

		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		addContentView(ll, lp);
	}
	
	PictureCallback jpeg = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			Bitmap bm = BitmapFactory.decodeByteArray(data,
					0, data.length);
			FileOutputStream fos = null;
			try {
				File sdroot =
						Environment.getExternalStorageDirectory();
				File file = new File(sdroot, "picture.jpg");
				fos = new FileOutputStream(file);
				BufferedOutputStream bos =
						new BufferedOutputStream(fos);
				bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
				bos.flush();
				bos.close();
				Toast.makeText(MainActivity.this, "儲存成功",
						Toast.LENGTH_SHORT).show();
				camera.startPreview();
			}catch (Exception e) {
				Toast.makeText(MainActivity.this, "儲存失敗",
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null)
			smgr.registerListener(sensorlistener, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (sensor != null)
			smgr.unregisterListener(sensorlistener, sensor);
	}

	class MySHCallback implements SurfaceHolder.Callback {

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			CameraInfo info = new CameraInfo();
			int ncamera = Camera.getNumberOfCameras();
			for (int i = 0; i < ncamera; i++) {
				Camera.getCameraInfo(i, info);
				if (info.facing == facing) {
					camera = Camera.open(i);
					break;
				}
			}
			if (camera == null) camera = Camera.open();
			
			if (camera == null) {
				Toast.makeText(MainActivity.this,
						"無法開啟相機", Toast.LENGTH_SHORT).show();
				finish();
				return;
			}
			
			int degrees = 0;
			int rotation = display.getRotation();
			switch (rotation) {
			case Surface.ROTATION_0: degrees = 0; break;
			case Surface.ROTATION_90: degrees = 90; break;
			case Surface.ROTATION_180: degrees = 180; break;
			case Surface.ROTATION_270: degrees = 270; break;
			}

			int result;
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				result = (info.orientation + degrees) % 360;
				result = (360 - result) % 360;  // compensate the mirror
			} else {  // back-facing
				result = (info.orientation - degrees + 360) % 360;
			}
			camera.setDisplayOrientation(result);

			Camera.Parameters params = camera.getParameters();
			params.setPictureFormat(ImageFormat.JPEG);

            camera.setParameters(params);

			try  {
				camera.setPreviewDisplay(sh);
			} catch (Exception e) {
				finish();
			}
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder surfaceholder) {
			if (camera != null) {
				camera.stopPreview();
				camera.release();
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder surfaceholder,
				int format , int w, int h) {
			if (camera != null) camera.startPreview();
		}
	}

	class MySensorListener implements SensorEventListener {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.values[0] > 9.3 || event.values[1] > 9.3) {
				tv.setText("相機是正的");
			} else {
				tv.setText("相機拿歪了喔");
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	}
}
