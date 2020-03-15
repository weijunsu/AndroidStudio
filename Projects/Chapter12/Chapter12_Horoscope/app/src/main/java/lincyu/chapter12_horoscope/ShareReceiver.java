package lincyu.chapter12_horoscope;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class ShareReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String backaction = intent.getStringExtra("BACKACTION");

		SharedPreferences pref = context.getSharedPreferences(
				"PREF_BIRTH", Context.MODE_PRIVATE);
		
		Intent back = new Intent();
		back.setAction(backaction);
		back.putExtra("PREF_MONTH",
				pref.getString("PREF_MONTH", "1"));
		back.putExtra("PREF_DAY",
				pref.getInt("PREF_DAY", 1));
		context.sendBroadcast(back);		
	}
}
