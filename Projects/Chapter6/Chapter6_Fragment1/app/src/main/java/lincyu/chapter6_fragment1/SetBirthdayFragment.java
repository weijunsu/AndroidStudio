package lincyu.chapter6_fragment1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetBirthdayFragment extends Fragment {
	
	Button submit;
	EditText etMonth, etDay;
	Context context;
	FragmentManager fm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		fm = getFragmentManager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(
				R.layout.fragment_setbirthday, null);
		
		submit = (Button)v.findViewById(R.id.submit);
		etMonth = (EditText)v.findViewById(R.id.etMonth);
		etDay = (EditText)v.findViewById(R.id.etDay);
		submit.setOnClickListener(gethoroscope);
		return v;
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
				Toast.makeText(context, R.string.input_error,
					Toast.LENGTH_SHORT).show();
				return;
			}
			HoroscopeFragment hf = (HoroscopeFragment)
					fm.findFragmentById(R.id.frag_horoscope);
			hf.setUI(intMonth, intDay);			
		}
	};	
}
