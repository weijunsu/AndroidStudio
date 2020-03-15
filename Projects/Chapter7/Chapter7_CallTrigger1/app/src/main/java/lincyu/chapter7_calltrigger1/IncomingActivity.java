package lincyu.chapter7_calltrigger1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.Toast;

public class IncomingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fake);
		
		Intent intent = getIntent();
		String number = intent.getStringExtra(
				TelephonyManager.EXTRA_INCOMING_NUMBER);
		
		Toast.makeText(this, "Incoming number: " +
			number, Toast.LENGTH_SHORT).show();
		
		AlertDialog.Builder adb =
				new AlertDialog.Builder(this);
		adb.setMessage("Incoming number: " + number);
		adb.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		});
		adb.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_fake, menu);
		return true;
	}

}
