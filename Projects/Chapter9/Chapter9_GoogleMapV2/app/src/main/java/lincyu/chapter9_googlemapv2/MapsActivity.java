package lincyu.chapter9_googlemapv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        Button btn = (Button)findViewById(R.id.btn_legal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLegalNotices();
            }
        });
    }

    private void showLegalNotices() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Legal Notices");
        String notice = GooglePlayServicesUtil.
                getOpenSourceSoftwareLicenseInfo(this);
        adb.setMessage(notice);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
            }
        });
        adb.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        MarkerOptions mo = new MarkerOptions();
        LatLng ll = new LatLng(24.789481,120.998998);
        mo.position(ll);
        mo.title("交通大學光復校區");
        mo.icon(BitmapDescriptorFactory.
                fromResource(R.drawable.nctu));
        mMap.addMarker(mo);

        // Show Current Location
        UiSettings uisettings = mMap.getUiSettings();
        uisettings.setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);

        // Polyline
        LatLng TAIPEI101 = new LatLng(25.033496, 121.563863);
        LatLng NCTU = new LatLng(24.789481,120.998998);

        PolylineOptions plo = new PolylineOptions();
        plo.add(NCTU, TAIPEI101);
        plo.width(5);
        plo.color(Color.BLUE);
        mMap.addPolyline(plo);

        // Move the center position
        CameraPosition.Builder cpb = new CameraPosition.Builder();
        cpb.target(NCTU);
        cpb.zoom(10f);
        cpb.bearing(0);
        CameraPosition cpnctu = cpb.build();
        CameraUpdate initloc = CameraUpdateFactory.
                newCameraPosition(cpnctu);
        // mMap.moveCamera(initloc);
        mMap.animateCamera(initloc);
    }

}
