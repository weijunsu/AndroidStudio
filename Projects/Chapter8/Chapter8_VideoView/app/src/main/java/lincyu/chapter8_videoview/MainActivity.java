package lincyu.chapter8_videoview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private VideoView vv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Uri uri = Uri.parse("file:///sdcard/nctulibrary.mp4");

		vv = (VideoView)findViewById(R.id.vv);
		vv.setVideoURI(uri);
		vv.setMediaController(new MediaController(this));
		vv.start();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		vv.stopPlayback();
	}
}
