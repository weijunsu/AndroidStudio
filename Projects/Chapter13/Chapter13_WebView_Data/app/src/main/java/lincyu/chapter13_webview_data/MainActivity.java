package lincyu.chapter13_webview_data;

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
				"<center><h3><font color=\"#0000FF\">" + "使用" +
				"</font><font color=\"#FF0000\">" + "說明" +
				"</font></h3></center>" +
				"<ol><li>" + "如何開啟？" + 
				"<ul><li>" + "按下桌面圖示即可開啟" +
				"</li></ul></li>" + "<li>" + "如何關閉？" + 
				"<ul><li>" + "按下返回鍵即可關閉" +
				"</li></ul></li>" + "</html>";
		
		WebView wv = (WebView)findViewById(R.id.wv);
		wv.loadDataWithBaseURL(null, data,
				"text/html", "utf-8", null);
	}
}
