package lincyu.chapter6_lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity1 extends Activity {

	Button btn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity1);
		btn = (Button)findViewById(R.id.btn1);
		btn.setOnClickListener(toActivity2);
		Log.d("LIFECYCLE", "Activity1:onCreate");
	}
	
	public void onStart() {
		super.onStart();
		Log.d("LIFECYCLE", "Activity1:onStart");
	}

	public void onResume() {
		super.onResume();
		Log.d("LIFECYCLE", "Activity1:onResume");
	}

	public void onPause() {
		super.onPause();
		Log.d("LIFECYCLE", "Activity1:onPause");
	}
	
	public void onStop() {
		super.onStop();
		Log.d("LIFECYCLE", "Activity1:onStop");
	}
	
	public void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "Activity1:onDestroy");
	}

	public void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "Activity1:onRestart");
	}
	
	private OnClickListener toActivity2 = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Activity1.this, Activity2.class);
			startActivity(intent);
		}
	};
}
