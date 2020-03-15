package lincyu.chapter8_japaneselearning;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private MediaPlayer player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn_start = (Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(l_start);
		Button btn_pause = (Button)findViewById(R.id.btn_pause);
		btn_pause.setOnClickListener(l_pause);
		Button btn_stop = (Button)findViewById(R.id.btn_stop);
		btn_stop.setOnClickListener(l_stop);
        ImageView iv_question = (ImageView)
                findViewById(R.id.iv_question);
        iv_question.setOnTouchListener(tl);
	}

	@Override
	public void onResume() {
		super.onResume();
		player = MediaPlayer.create(this, R.raw.aoi);
		player.setOnCompletionListener(comL);
	}

	public void onPause() {
		super.onPause();
		player.release();
	}
	
	private OnClickListener l_start = new OnClickListener() {
		public void onClick(View v) {
			try {
				player.start();
			} catch (Exception e) {
			}
		}
	};

	private OnClickListener l_pause = new OnClickListener() {
		public void onClick(View v) {
			if (player.isPlaying()==false) return;
			try {
				player.pause();
			} catch (Exception e) {
			}
		}
	};
	
	private OnClickListener l_stop = new OnClickListener() {
		public void onClick(View v) {
			try {
				player.stop();
				player.prepare();
			} catch (Exception e) {
			}
		}
	};

	private OnCompletionListener comL = new OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer nouse) {
			try {
				player.stop();
				player.prepare();
			} catch (Exception e){
			}
		}
	};

    View.OnTouchListener tl = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v,MotionEvent event){
            float touchDownX;
            float touchDownY;
            int h = v.getHeight();
            int w = v.getWidth();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchDownX = event.getX();
                    touchDownY = event.getY();
                    if (touchDownX<=(float)w&&
                            touchDownX>=(float)(w/2.0)&&
                            touchDownY<=(float)(h/2.0)&&
                            touchDownY>=(float)0)
                        Toast.makeText(MainActivity.this, "Correct",
                                Toast.LENGTH_LONG).show();
                    break;
            }
            return true;
        }
    };
}
