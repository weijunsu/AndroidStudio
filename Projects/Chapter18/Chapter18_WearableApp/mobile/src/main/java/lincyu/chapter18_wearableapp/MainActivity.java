package lincyu.chapter18_wearableapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    public void onNewIntent(Intent intent) {
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        String data = intent.getStringExtra("EXTRA_DATA");
        String path = intent.getStringExtra("EXTRA_PATH");
        String sourcenodeid = intent.getStringExtra("EXTRA_SOURCENODEID");
        TextView tv_msg = (TextView)findViewById(R.id.tv_msg);
        tv_msg.setText("Data: " + data + "\n\n" +
                "Path: " + path + "\n\n" +
                "SourceNodeId: " + sourcenodeid);
    }
}
