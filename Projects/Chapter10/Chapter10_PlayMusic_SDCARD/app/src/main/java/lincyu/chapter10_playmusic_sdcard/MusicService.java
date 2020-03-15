package lincyu.chapter10_playmusic_sdcard;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MusicService extends Service {
	
	private MediaPlayer myplayer;
	
	@Override
	public IBinder onBind(Intent i) {
		return null;
	}

	@Override
	public void onCreate() {
		myplayer = new MediaPlayer();
		try {
			File file = searchsong();
			if (file == null) {
				Toast.makeText(this, "找不到可播放的音樂檔",
						Toast.LENGTH_SHORT).show();
				return;
			}
			myplayer.setDataSource(file.getPath());
			myplayer.setOnCompletionListener(comL);
			myplayer.prepare();
		} catch (Exception e) {
		}
	}

	OnCompletionListener comL = new OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer nouse) {
			try {
				myplayer.stop();
				myplayer.prepare();
			} catch (Exception e){
			}
			stopSelf();
		}
	};

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		boolean isPause = intent.getBooleanExtra("KEY_ISPAUSE", true);
		
		if (isPause == true) {
			if(myplayer.isPlaying() == false) return 0;
			try {
				myplayer.pause();
			} catch (Exception e) {
			}
		} else {
			try {
				myplayer.start();
			} catch (Exception e) {
			}
		}
		Log.d("LINCYU", "onStartCommand ends");
		return 0;
	}
	
	@Override
	public void onDestroy() {
		try {
			myplayer.stop();
			myplayer.release();
		} catch (Exception e) {
		}
	}

	File searchsong() {
		File sdcard = Environment.getExternalStorageDirectory();
		File files [] = sdcard.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isFile() == false) continue;
			String name = f.getName();
			int start = name.lastIndexOf('.');
			String subname = name.substring(start+1);
			if (subname.equalsIgnoreCase("mp3")) return f;
		}
		return null;
	}
}
