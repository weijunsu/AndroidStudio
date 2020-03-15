package lincyu.chapter13_culture;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ConnThread extends Thread {
	
	Activity activity;
	ListView lv;
	int type;
	
	ArrayList<Facility> items;
	
	ConnThread(Activity activity, ListView lv, int type) {
		this.activity = activity;
		this.lv = lv;
		this.type = type;
	}
	
	@Override
	public void run() {
		
		String url =
			"http://cloud.culture.tw/frontsite/trans/" +
			"emapOpenDataAction.do?" +
			"method=exportEmapJsonByMainType" + "&" +
			"&mainType=" + type;
		
		HttpGet request = new HttpGet(url);
		String result = "";
		try {
			DefaultHttpClient client = new
					DefaultHttpClient();
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode(); 
			if (code == 200) {
				result = EntityUtils.toString(
						response.getEntity());
				items = parsejson(result);				
				connsucc();
			} else {
				connerror();
			}
		} catch (Exception e) {
			connerror();
		}
	}
	
	ArrayList<Facility> parsejson(String jsonstr) {
		ArrayList<Facility> items =
				new ArrayList<Facility>();
		try {
			JSONArray array = new JSONArray(jsonstr);
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject jo = array.getJSONObject(i);
				String name = "";
				try {
					name = jo.getString("name");
				} catch (Exception e) {}
				String intro = "";
				try {
					intro = jo.getString("intro");
				} catch (Exception e) {}
				String opentime = "";
				try {
					opentime = jo.getString("openTime");
				} catch (Exception e) {}
				String price = "";
				try {
					price = jo.getString("ticketPrice");
				} catch (Exception e) {}
				String website = "";
				try {
					website = jo.getString("website");
				} catch (Exception e) {}
				double lng = 0;
				try {
					lng = jo.getDouble("longitude");
				} catch (Exception e) {}
				double lat = 0;
				try {
					lat = jo.getDouble("latitude");
				} catch (Exception e) {}
				Facility f = new Facility(name, intro,
						opentime, price, website,
						lng, lat);
				items.add(f);
			}
		} catch (Exception e) {
		}
		return items;
	}
	
	void connsucc() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ArrayAdapter<Facility> adapter =
						new ArrayAdapter<Facility>(
						activity,
						android.R.layout.simple_list_item_1,
						items);
				lv.setAdapter(adapter);
				lv.setOnItemClickListener(itemclick);
			}
		});
	}
	
	OnItemClickListener itemclick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> av, View v,
				int position, long id) {
			Facility f = items.get(position);
			Intent intent = new Intent();
			intent.setClass(activity,
					FacilityInfoActivity.class);
			intent.putExtra("FACILITY", f);
			activity.startActivity(intent);
		}
	};

	void connerror() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, "連線失敗",
						Toast.LENGTH_SHORT).show();
			}
		});
	}	
}