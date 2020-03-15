package lincyu.chapter7_explicitbroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SenderActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sender);
		Button btn = (Button)findViewById(R.id.btn_sendbroadcast);
		btn.setOnClickListener(sendb);
	}

	private OnClickListener sendb = new OnClickListener () {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(SenderActivity.this, SelfReceiver.class);
			intent.putExtra("KEY_MSG", "ExplicitBroadcast");
			sendBroadcast(intent);
		}
	};
}
