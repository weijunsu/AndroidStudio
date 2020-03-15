package lincyu.chapter18_wearableapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tv_msg;
    private Button btn_start, btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub)
                findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(
                new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                tv_msg = (TextView)stub.findViewById(R.id.tv_msg);

                Intent intent = getIntent();
                processIntent(intent);

                btn_start = (Button)stub.findViewById(R.id.btn_start);
                btn_start.setOnClickListener(listener);
                btn_stop = (Button)stub.findViewById(R.id.btn_stop);
                btn_stop.setOnClickListener(listener);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String msg = ((Button)view).getText().toString();
            SendMsgThread thread = new SendMsgThread(
                    MainActivity.this, msg);
            thread.start();
        }
    };

    @Override
    public void onNewIntent(Intent intent) {
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        String data = intent.getStringExtra("EXTRA_DATA");
        if (data == null) return;
        String nid = intent.getStringExtra("EXTRA_NID");
        TextView tv_msg = (TextView)findViewById(R.id.tv_msg);
        tv_msg.setText("Data: " + data + "\n" +
                "Node: " + nid);
    }
}
