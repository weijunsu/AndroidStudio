package lincyu.chapter6_lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity2 extends Activity {

	Button btn;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		btn = (Button)findViewById(R.id.btn2);
		btn.setOnClickListener(returnToActivity1);
		Log.d("LIFECYCLE", "Activity2:onCreate");
	}

	public void onStart() {
		super.onStart();
		Log.d("LIFECYCLE", "Activity2:onStart");
	}
	
	public void onResume() {
		super.onResume();
		Log.d("LIFECYCLE", "Activity2:onResume");
	}

	public void onPause() {
		super.onPause();
		Log.d("LIFECYCLE", "Activity2:onPause");
	}

	public void onStop() {
		super.onStop();
		Log.d("LIFECYCLE", "Activity2:onStop");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.d("LIFECYCLE", "Activity2:onDestroy");
	}
	
	public void onRestart() {
		super.onRestart();
		Log.d("LIFECYCLE", "Activity2:onRestart");
	}

	private OnClickListener returnToActivity1 = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}
