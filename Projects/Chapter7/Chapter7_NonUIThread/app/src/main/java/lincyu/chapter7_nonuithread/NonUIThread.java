package lincyu.chapter7_nonuithread;

import android.content.Context;
import android.content.Intent;

public class NonUIThread extends Thread {
	
	Context context;
	
	NonUIThread(Context context) {
		this.context = context;
	}
	
	@Override
	public void run() {
		
		Intent intent = new Intent();
		intent.setAction(UIThreadActivity.ACTION_CHANGETEXT);
		for (int i = 10; i > 0; i--) {
			intent.putExtra(UIThreadActivity.EXTRA_END, false);
			intent.putExtra(UIThreadActivity.EXTRA_MSG, "" + i);
			context.sendBroadcast(intent);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		intent.putExtra(UIThreadActivity.EXTRA_END, true);
		intent.putExtra(UIThreadActivity.EXTRA_MSG, "Time is up");
		context.sendBroadcast(intent);
	}
}
