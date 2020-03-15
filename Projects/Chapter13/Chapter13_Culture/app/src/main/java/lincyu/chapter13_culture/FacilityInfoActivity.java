package lincyu.chapter13_culture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FacilityInfoActivity extends ActionBarActivity {

	Facility f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facilityinfo);
		
		Intent intent = getIntent();
		f = (Facility)intent.
				getParcelableExtra("FACILITY");
		
		TextView tv = (TextView)findViewById(R.id.tv_name);
		tv.setText(f.name);
		tv = (TextView)findViewById(R.id.tv_intro);
		tv.setText(f.intro);
		tv = (TextView)findViewById(R.id.tv_opentime);
		tv.setText(f.opentime);
		tv = (TextView)findViewById(R.id.tv_price);
		tv.setText(f.price);
		
		Button btn = (Button)findViewById(R.id.btn_website);
		btn.setOnClickListener(website);
		btn = (Button)findViewById(R.id.btn_map);
		btn.setOnClickListener(map);
	}
	
	OnClickListener website = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse(f.website);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};
	
	OnClickListener map = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("geo:" +
					f.lat + ", " + f.lng);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
		}
	};	
}
