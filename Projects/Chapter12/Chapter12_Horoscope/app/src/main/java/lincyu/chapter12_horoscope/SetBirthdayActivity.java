package lincyu.chapter12_horoscope;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SetBirthdayActivity extends ActionBarActivity {

	EditText etMonth, etDay;
	
	public static final String KEY_MONTH = "KEY_MONTH";
	public static final String KEY_DAY = "KEY_DAY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setbirthday);
		Button submit = (Button)findViewById(R.id.submit);
		etMonth = (EditText)findViewById(R.id.etMonth);
		etDay = (EditText)findViewById(R.id.etDay);
		submit.setOnClickListener(gethoroscope);
		
		SharedPreferences pref = getSharedPreferences("PREF_BIRTH",
				MODE_PRIVATE);
		String pref_month = pref.getString("PREF_MONTH", "1");
		etMonth.setText(pref_month);
		int intDay = pref.getInt("PREF_DAY", 1);
		etDay.setText("" + intDay);
	}

	@Override
	public void onStop() {
		super.onStop();
		SharedPreferences pref = getSharedPreferences("PREF_BIRTH",
				MODE_PRIVATE);
		SharedPreferences.Editor preEdt = pref.edit();
		preEdt.putString("PREF_MONTH", etMonth.getText().toString());
		int intDay = Integer.parseInt(etDay.getText().toString());
		preEdt.putInt("PREF_DAY", intDay);
		preEdt.commit();
	}
	
	private OnClickListener gethoroscope = new OnClickListener() {
		@Override
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
			intent.setClass(SetBirthdayActivity.this,
					HoroscopeActivity.class);
			intent.putExtra(KEY_MONTH, intMonth);
			intent.putExtra(KEY_DAY, intDay);
			startActivity(intent);
		}
	};
}
