package lincyu.chapter13_ftpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	EditText site, user, pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		site = (EditText)findViewById(R.id.et_site);
		user = (EditText)findViewById(R.id.et_username);
		pwd = (EditText)findViewById(R.id.et_password);
		
		Button btn_login = (Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(login);		
	}

	OnClickListener login = new OnClickListener() {
		public void onClick(View v) {
			String ftpsite = site.getText().toString();
			String username = user.getText().toString();
			String password = pwd.getText().toString();

			Intent intent = new Intent();
			intent.setClass(MainActivity.this, FTPActivity.class);
			intent.putExtra("FTPSITE", ftpsite);
			intent.putExtra("USERNAME", username);
			intent.putExtra("PASSWORD", password);
			startActivity(intent);
		}
	};
}