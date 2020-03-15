package lincyu.chapter15_buildincamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv = (ImageView)findViewById(R.id.iv);
		Button btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(open);
	}
	
	OnClickListener open = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent cameraIntent = new Intent();
			cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, 1);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode,
			int resultCode, Intent intent) {
		
		super.onActivityResult(requestCode, resultCode, intent);
		
		if (intent == null) return;
		if (requestCode != 1) return;
		
		Bitmap bm = (Bitmap)intent.getExtras().get("data");
		iv.setImageBitmap(bm);
	}
}
