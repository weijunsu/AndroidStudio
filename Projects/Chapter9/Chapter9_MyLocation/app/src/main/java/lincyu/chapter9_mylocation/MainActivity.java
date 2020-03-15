package lincyu.chapter9_mylocation;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private MyLocationListener mll;
	private LocationManager mgr;
	private TextView tv;
	private String best;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView)findViewById(R.id.tv_loc);
		
		mgr = (LocationManager)getSystemService(LOCATION_SERVICE);
		mll = new MyLocationListener();

		Location location = mgr.getLastKnownLocation(
				LocationManager.GPS_PROVIDER);
		if (location == null) {
			location = mgr.getLastKnownLocation(
					LocationManager.NETWORK_PROVIDER);
		}
		if (location != null) {
			tv.setText(showLocation(location));
		} else {
			tv.setText("Cannot get location!");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		best = mgr.getBestProvider(criteria, true);
		if (best != null) {
			mgr.requestLocationUpdates(best, 1000, 1, mll);
		} else {
			mgr.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 1000, 1, mll);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mgr.removeUpdates(mll);
	}

	class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				tv.setText(showLocation(location));
			} else {
				tv.setText("Cannot get location!");
			}
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onStatusChanged(String provider,
				int status, Bundle extras) {
		}
	}

	public String showLocation(Location location) {
		StringBuffer msg = new StringBuffer();
		msg.append("定位提供者(Provider): \n");
		msg.append(location.getProvider());
		msg.append("\n緯度(Latitude): \n");
		msg.append(Double.toString(location.getLatitude()));
		msg.append("\n經度(Longitude): \n");
		msg.append(Double.toString(location.getLongitude()));
		msg.append("\n高度(Altitude): \n");
		msg.append(Double.toString(location.getAltitude()));
		return msg.toString();
	}
}
