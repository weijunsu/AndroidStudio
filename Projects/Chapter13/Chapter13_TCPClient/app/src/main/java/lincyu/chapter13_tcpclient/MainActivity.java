package lincyu.chapter13_tcpclient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	EditText et_msg;
	Button btn_send;
	TextView tv_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_result = (TextView)findViewById(R.id.tv_result);
		et_msg = (EditText)findViewById(R.id.et_msg);
		btn_send = (Button)findViewById(R.id.btn_send);
		btn_send.setOnClickListener(send);		
	}

	OnClickListener send = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String msg = et_msg.getEditableText().toString();
			ConnThread thread =new ConnThread(
				MainActivity.this, tv_result, msg);
			thread.start();
		}
	};
}
