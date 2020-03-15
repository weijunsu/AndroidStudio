package lincyu.chapter10_locationservice;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class LocationService extends Service
	implements LocationListener {
	
	private LocationManager mgr;
	private boolean isIn;
	
	@Override
	public IBinder onBind(Intent i) {
		return null;
	}
	
	@Override
	public void onCreate() {
		mgr = (LocationManager)getSystemService(LOCATION_SERVICE);
		mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 1, this);
		isIn = false;
	}

	@Override
	public void onDestroy() {
		mgr.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location current) {
		if (current == null) return;
		Location dest = new Location(current);
		dest.setLatitude(25.033496);
		dest.setLongitude(121.563863);

		float dist = current.distanceTo(dest);
		if (dist < 100.0) {
			if (isIn == false) {
				Toast.makeText(this, "Welcome to Taipei 101",
						Toast.LENGTH_LONG).show();
				isIn = true;
			}
		} else {
			isIn = false; // too far
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
