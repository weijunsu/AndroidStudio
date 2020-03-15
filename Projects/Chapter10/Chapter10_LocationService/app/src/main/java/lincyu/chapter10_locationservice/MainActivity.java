package lincyu.chapter10_locationservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn_startS = (Button)findViewById(R.id.startS);
		btn_startS.setOnClickListener(startS_l);
		Button btn_stopS = (Button)findViewById(R.id.stopS);
		btn_stopS.setOnClickListener(stopS_l);
	}

	OnClickListener startS_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,
					LocationService.class);
			startService(intent);
		}
	};

	OnClickListener stopS_l = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,
					LocationService.class);
			stopService(intent);
		}
	};
}
