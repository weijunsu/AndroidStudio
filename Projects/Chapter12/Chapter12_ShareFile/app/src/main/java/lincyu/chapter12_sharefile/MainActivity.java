package lincyu.chapter12_sharefile;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends ActionBarActivity {

	EditText et;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StringBuilder str = new StringBuilder("");
		try {
			FileInputStream fin = openFileInput("share.txt");
			do {
				int c = fin.read();
				if (c == -1) break;
				str.append((char)c);
			} while(true);
			fin.close();
		} catch (Exception e) {
		}
		
		et = (EditText)findViewById(R.id.et);
		et.setText(str.toString());
	}

	@Override
	public void onStop() {
		String str = et.getText().toString();
		try {
			FileOutputStream fos = openFileOutput("share.txt",
				MODE_PRIVATE);
			for (int i = 0; i < str.length(); i++)
				fos.write((int)str.charAt(i));
			fos.close();
		} catch(Exception e) {	
		}
		super.onStop();
	}
}
