package lincyu.chapter13_webview_data2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String data = "<html><head>" +
				"<meta content=\"text/html; charset=utf-8\">" +
				"</head><body bgcolor=\"#EBFFF5\">" + 
				"<center><h3>我的照片</h3></center>" +
				"<img src=\"http://hscc.cs.nctu.edu.tw/" +
				"~lincyu/AboutMe/chihyu2012.jpg\"/>"+ "</html>";
		
		WebView wv = (WebView)findViewById(R.id.wv);
		wv.loadDataWithBaseURL("fake-url", data,
				"text/html", "utf-8", null);
	}

}
