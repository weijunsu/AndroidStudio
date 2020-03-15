package lincyu.chapter6_fragment1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HoroscopeFragment extends Fragment {
	
	TextView result;
	ImageView symbol;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_horoscope,
				null);
		
		result = (TextView)v.findViewById(R.id.result);
		symbol = (ImageView)v.findViewById(R.id.imgSymbol);
		
		return v;
	}
	
	public void setUI(int intMonth, int intDay) {
		if (intDay < bound[intMonth-1]) {
			result.setText(resId[intMonth-1]);
			symbol.setImageResource(sybId[intMonth-1]);
		} else {
			if (intMonth == 12) {
				result.setText(resId[0]);
				symbol.setImageResource(sybId[0]);
			} else {
				result.setText(resId[intMonth]);
				symbol.setImageResource(sybId[intMonth]);
			}
		}
	}
	
	final int [] bound = {20, 19, 21, 20, 21, 21,
			23, 23, 23, 23, 22, 22};
	final int [] resId = {
		R.string.Capricorn, R.string.Aquarius, R.string.Pisces,
		R.string.Aries, R.string.Taurus, R.string.Gemini,
		R.string.Cancer, R.string.Leo, R.string.Virgo,
		R.string.Libra, R.string.Scorpio, R.string.Sagittarius};
	final int [] sybId = {
		R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces,
		R.drawable.aries, R.drawable.taurus, R.drawable.gemini,
		R.drawable.cancer, R.drawable.leo, R.drawable.virgo,
		R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius};
}
