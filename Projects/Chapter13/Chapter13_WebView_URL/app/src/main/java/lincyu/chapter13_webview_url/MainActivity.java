package lincyu.chapter13_webview_url;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class MainActivity extends ActionBarActivity {

	EditText et_keyword;
	Button btn_google;
	WebView wv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_keyword = (EditText)findViewById(R.id.et_keyword);
		
		wv = (WebView)findViewById(R.id.wv);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(client);
		
		btn_google = (Button)findViewById(R.id.btn_search);
		btn_google.setOnClickListener(google);
	}
	
	OnClickListener google = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String keyword = et_keyword.getText().toString();
			try {
				keyword = URLEncoder.encode(keyword, "UTF-8");
			} catch (Exception e) {
			}
			Log.d("LINCYU", "Keyword: " + keyword);
			String url = "http://www.google.com/search?q=" +
				keyword;
			wv.loadUrl(url);
		}
	};
	
	WebViewClient client = new WebViewClient() {
		@Override
		public boolean shouldOverrideUrlLoading(
				WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};	
}