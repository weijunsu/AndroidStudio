package lincyu.chapter7_alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

	PendingIntent pendingintent;
	AlarmManager am;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.btn_start);
		btn.setOnClickListener(startlistener);
		btn = (Button)findViewById(R.id.btn_stop);
		btn.setOnClickListener(stoplistener);
		
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, AlarmReceiver.class);
		
		pendingintent = PendingIntent.getBroadcast(
				MainActivity.this, 1, intent, 0);

		am = (AlarmManager)getSystemService(ALARM_SERVICE);	
	}

	private OnClickListener startlistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			startReport();
		}
	};

	private OnClickListener stoplistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			stopReport();
		}
	};

	private void startReport() {

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		long interval = 1000*60*60;
		am.setRepeating(AlarmManager.RTC_WAKEUP,
				c.getTimeInMillis(), interval, pendingintent);
		Toast.makeText(MainActivity.this, "整點報時開始",
				Toast.LENGTH_SHORT).show();
	}

	private void stopReport() {
		
		am.cancel(pendingintent);
		Toast.makeText(MainActivity.this, "停止整點報時",
				Toast.LENGTH_SHORT).show();
	}
}
