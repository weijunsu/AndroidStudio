package lincyu.chapter6_fragment2;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	HoroscopeFragment horoscope;
	SetBirthdayFragment setbirthday;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		horoscope = new HoroscopeFragment();
		setbirthday = new SetBirthdayFragment();
		
		FragmentTransaction ft;
		ft = getFragmentManager().beginTransaction();
		ft.add(R.id.mainui, setbirthday, "SETBIRTHDAY");
		ft.addToBackStack(null);
		ft.commit();
	}

	public void onBirthdaySet(int month, int day) {
		FragmentTransaction ft;
		
		Bundle args = new Bundle();
		args.putInt("KEY_MONTH", month);
		args.putInt("KEY_DAY", day);
		horoscope.setArguments(args);
		
		ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.mainui, horoscope, "HOROSCOPE");
		ft.addToBackStack(null);
		ft.commit();
	}
}
