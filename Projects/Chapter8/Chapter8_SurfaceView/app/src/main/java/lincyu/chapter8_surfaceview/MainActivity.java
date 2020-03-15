package lincyu.chapter8_surfaceview;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private SurfaceHolder sh;
	private MediaPlayer player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SurfaceView sv = (SurfaceView)findViewById(R.id.sv);
		sh = sv.getHolder();
		sh.addCallback(new MySHCallback());

		Button btn_start = (Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(l_start);
		Button btn_stop = (Button)findViewById(R.id.btn_stop);
		btn_stop.setOnClickListener(l_stop);   
	}

	@Override
	public void onPause() {
		super.onPause();
		if (player != null)
			player.release();
	}

	private OnClickListener l_start = new OnClickListener() {
		public void onClick(View v) {
			if (player != null) player.release();
			try {
				player = new MediaPlayer();
				player.setDataSource("/sdcard/nctulibrary.mp4");
				player.setOnCompletionListener(comL);
				player.setDisplay(sh);
				player.prepare();
				player.start();
			} catch (Exception e) {
			}
		}
	};
	
	private OnClickListener l_stop = new OnClickListener() {
		public void onClick(View v) {
			try {
				player.stop();
				player.release();
			} catch (Exception e) {
			}
		}
	};

	private OnCompletionListener comL = new OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer nouse) {
			try {
				player.stop();
				player.release();
			} catch (Exception e){
			}
		}
	};

	class MySHCallback implements SurfaceHolder.Callback {
		@Override
		public void surfaceChanged(SurfaceHolder sh,
				int format, int w, int h) {
		}
		@Override
		public void surfaceCreated(SurfaceHolder sh) {
		}
		@Override
		public void surfaceDestroyed(SurfaceHolder sh) {
		}
	}
}
