package lincyu.chapter10_boundservice;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class BoundService extends Service {

	MyBinder binder;
	
	@Override
	public IBinder onBind(Intent intent) {
		if (binder == null)
			binder = new MyBinder();
		return binder;
	}
}
