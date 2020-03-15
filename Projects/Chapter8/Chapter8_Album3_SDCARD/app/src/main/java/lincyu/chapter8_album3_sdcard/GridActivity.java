package lincyu.chapter8_album3_sdcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;



public class GridActivity extends ActionBarActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		
		Intent intent = getIntent();
		String [] imgfiles = intent.getStringArrayExtra("KEY_FILES");
		int cols = intent.getIntExtra("KEY_COLUMNS", 3);
				
		GridView gv = (GridView)findViewById(R.id.gv);
		gv.setNumColumns(cols);
		gv.setAdapter(new ImageAdapter(this, imgfiles));
	}
	
	public class ImageAdapter extends ArrayAdapter<String> {
	
		private Context mCtx;
		
		public ImageAdapter(Context c, String [] imgfiles) {
			super(c, 0, imgfiles);
			mCtx = c;
		}

		@Override
		public View getView(int position, View convertView,
				ViewGroup parent) {
			
			String filepath = getItem(position);
			Drawable drawable = Drawable.createFromPath(filepath);
			
			ImageView iv = new ImageView(mCtx);
			if (drawable == null) {
				iv.setImageResource(R.drawable.notfound);
			} else {
				iv.setImageDrawable(drawable);
			}
			iv.setAdjustViewBounds(true);
			return iv;
		}
		
	}
}