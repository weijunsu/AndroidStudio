package lincyu.chapter10_calltrigger3;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AlertWindowService extends Service {
	
	View floatDialogView;
	WindowManager wm;
	WindowManager.LayoutParams params;
	float startX, startY, x, y;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId) {
		wm = (WindowManager)getApplicationContext().
				getSystemService(Context.WINDOW_SERVICE);
		params = new WindowManager.LayoutParams();    
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; 
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;    
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;    
		
		LayoutInflater inflater = LayoutInflater.from(this);
		floatDialogView = inflater.inflate(
				R.layout.floatwindow, null);
		Button btn_close = (Button)floatDialogView.
				findViewById(R.id.btn_close);
		btn_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopSelf();
			}
		});
		
		String number = intent.getStringExtra(
				TelephonyManager.EXTRA_INCOMING_NUMBER);
		TextView tv_number = (TextView)floatDialogView.
				findViewById(R.id.tv_number);
		tv_number.setText("Incoming Number: " + number);
		floatDialogView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				x = event.getRawX();
				y = event.getRawY();
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getRawX();
					startY = event.getRawY();  
					break;
				case MotionEvent.ACTION_MOVE:  
					updatePosition();  
					break;  
				case MotionEvent.ACTION_UP:  
					updatePosition();
					startX = startY = 0;
					break;  
				}
				return true;  
			}
		});
		wm.addView(floatDialogView, params);
		return 0;
	}
	
	private void updatePosition(){
		params.x = (int)(x - startX);  
		params.y = (int)(y - startY);  
		wm.updateViewLayout(floatDialogView, params);  
	}
	
	@Override
	public void onDestroy() {
		if (floatDialogView != null) {
			wm.removeView(floatDialogView);
		}
	}
}
