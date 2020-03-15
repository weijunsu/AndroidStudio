package lincyu.chapter7_broadcastreceiver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class ActivityDialog extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		String msg = intent.getStringExtra("KEY_MSG");
		
		AlertDialog.Builder adb =
				new AlertDialog.Builder(this);
		adb.setMessage("Received message: " + msg);
		adb.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		});
		adb.show();
	}
}
