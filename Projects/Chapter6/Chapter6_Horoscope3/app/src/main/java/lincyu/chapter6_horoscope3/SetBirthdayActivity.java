package lincyu.chapter6_horoscope3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetBirthdayActivity extends ActionBarActivity {

	private EditText etMonth;
	private EditText etDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setbirthday);
		Button back = (Button)findViewById(R.id.submit);
		back.setOnClickListener(showhoroscope);
		etMonth = (EditText)findViewById(R.id.etMonth);
		etMonth.setText("1");
		etDay = (EditText)findViewById(R.id.etDay);
		etDay.setText("1");
	}
	
	private OnClickListener showhoroscope = new OnClickListener() {
		public void onClick(View v) {
			int intMonth =
				Integer.parseInt(etMonth.getText().toString());
			int intDay =
				Integer.parseInt(etDay.getText().toString());
			if ((intMonth < 1 || intMonth > 12) ||
				(intDay < 1 || intDay > 31)) {
				Toast.makeText(SetBirthdayActivity.this,
						R.string.input_error,
						Toast.LENGTH_SHORT).show();
				return;
			}
			
			Intent intent = new Intent();
			intent.putExtra("KEY_MONTH", intMonth);
			intent.putExtra("KEY_DAY", intDay);
			setResult(RESULT_OK, intent);
			finish();
		}
	};
}