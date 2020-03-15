package lincyu.chapter13_math;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	EditText et_num1, et_num2;
	TextView tv_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_num1 = (EditText)findViewById(R.id.et_num1);
		et_num2 = (EditText)findViewById(R.id.et_num2);
		
		tv_result = (TextView)findViewById(R.id.tv_result);
		
		Button btn = (Button)findViewById(R.id.btn_send);
		btn.setOnClickListener(send);
	}
	
	OnClickListener send = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String num1 = et_num1.getText().toString();
			String num2 = et_num2.getText().toString();
			ConnThread thread = new ConnThread(
				MainActivity.this, tv_result, num1, num2);
			thread.start();
		}
	};
}
