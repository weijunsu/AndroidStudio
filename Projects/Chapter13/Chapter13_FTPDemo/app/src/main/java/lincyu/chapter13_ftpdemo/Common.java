package lincyu.chapter13_ftpdemo;

import android.app.Activity;
import android.widget.Toast;

public class Common {

	static void showToast(final Activity activity, final String msg) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}	
}
