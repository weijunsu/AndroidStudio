package lincyu.chapter7_broadcastreceiver;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class ActivityNotification extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(ActivityNotification.this);
		tv.setText("ActivityNotification!");
		setContentView(tv);
	}
}
