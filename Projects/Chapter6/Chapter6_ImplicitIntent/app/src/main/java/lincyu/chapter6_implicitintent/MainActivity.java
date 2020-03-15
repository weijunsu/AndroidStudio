package lincyu.chapter6_implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn1);
        btn.setOnClickListener(btn1);
        btn = (Button)findViewById(R.id.btn2);
        btn.setOnClickListener(btn2);
        btn = (Button)findViewById(R.id.btn3);
        btn.setOnClickListener(btn3);
        btn = (Button)findViewById(R.id.btn4);
        btn.setOnClickListener(btn4);
        btn = (Button)findViewById(R.id.btn5);
        btn.setOnClickListener(btn5);
    }

    private OnClickListener btn1 = new OnClickListener() {
        public void onClick(View v) {
            Uri uri = Uri.parse(
                    "http://www.google.com.tw");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        }
    };

    private OnClickListener btn2 = new OnClickListener() {
        public void onClick(View v) {
            Uri uri = Uri.parse(
                    "mailto:lincyu@cs.nctu.edu.tw");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(uri);
            startActivity(intent);
        }
    };

    private OnClickListener btn3 = new OnClickListener() {
        public void onClick(View v) {
            Uri uri = Uri.parse(
                    "geo:25.047443, 121.511478");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        }
    };

    private OnClickListener btn4 = new OnClickListener() {
        public void onClick(View v) {
            Uri uri = Uri.parse(
                    "tel:035712121");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        }
    };

    private OnClickListener btn5 = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
    };
}
