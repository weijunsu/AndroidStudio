package lincyu.chapter13_math;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class ConnThread extends Thread {

	String num1, num2;
	Activity activity;
	TextView tv;

	ConnThread(Activity activity, TextView tv,
			String num1, String num2) {
		this.activity = activity;
		this.tv = tv;
		this.num1 = num1;
		this.num2 = num2;
	}

	@Override
	public void run() {
		String url =
			"http://android-gae-demo.appspot.com/" +
			"androidgaedemo?" +
			"num1=" + num1 + "&" +
			"num2=" + num2;

		HttpGet request = new HttpGet(url);
		String result = "";
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode(); 
			if (code == 200) {
				result = EntityUtils.toString(
						response.getEntity());
				JSONObject jo = new JSONObject(result);
				int mul = jo.getInt("multiplication");
				int add = jo.getInt("addition");
				connsucc(mul, add);
			} else {
				connerror();
			}
		} catch (Exception e) {
			connerror();
		}
	}
	
	void connsucc(final int mul, final int add) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				String result = "Multiplication: " + mul +
					"\n" + "Addition: " + add;
				tv.setText(result);
			}
		});
	}

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
