package lincyu.chapter18_wearnotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    static final String EXTRA_VOICE_REPLY =
            "extra_voice_reply";
    Button btn_contentintent, btn_actionbtn;
    Button btn_voiceinput, btn_addpage;
    TextView tv_reply;
    PendingIntent mapPendingIntent, webPendingIntent;

    static int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_reply = (TextView)findViewById(R.id.tv_reply);

        Intent mapintent = new Intent(Intent.ACTION_VIEW);
        Uri mapuri = Uri.parse(
            "geo:25.033496, 121.563863");
        mapintent.setData(mapuri);
        mapPendingIntent =
        PendingIntent.getActivity(MainActivity.this, 0, mapintent, 0);

        Intent websiteintent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(
                "http://hscc.cs.nctu.edu.tw/~lincyu/");
        websiteintent.setData(uri);
        webPendingIntent =
                PendingIntent.getActivity(MainActivity.this, 0, websiteintent, 0);

        btn_contentintent = (Button)findViewById(R.id.btn_contentintent);
        btn_contentintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Content Intent")
                                .setContentText("Open Google Map")
                                .setContentIntent(mapPendingIntent)
                                .setPriority(Notification.PRIORITY_MAX);
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId,
                        notificationBuilder.build());
            }
        });

        btn_actionbtn = (Button)findViewById(R.id.btn_actionbtn);
        btn_actionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Action Button")
                                .setContentText("Open My Website")
                                .setPriority(Notification.PRIORITY_MAX)
                                .addAction(R.mipmap.ic_launcher,
                                        "My Website", webPendingIntent);
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId,
                        notificationBuilder.build());
            }
        });

        btn_voiceinput = (Button)findViewById(R.id.btn_voiceinput);
        btn_voiceinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String replyLabel = "可執行的選項";
                String [] replychoices = {"Start", "Pause", "Stop"};

                RemoteInput remoteInput =
                        new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                        .setLabel(replyLabel)
                        .setChoices(replychoices)
                        .build();

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pendingintent =
                        PendingIntent.getActivity(MainActivity.this, 0, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.Action action =
                        new NotificationCompat.Action.Builder(
                                R.mipmap.ic_launcher,
                                "選項", pendingintent)
                                .addRemoteInput(remoteInput)
                                .build();

                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Voice Input")
                                .setContentText("Demo")
                                .setPriority(Notification.PRIORITY_MAX)
                                .extend(new WearableExtender().addAction(action));

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);

                notificationManager.notify(notificationId,
                        notificationBuilder.build());
                tv_reply.setText("Waiting");
            }
        });

        btn_addpage = (Button)findViewById(R.id.btn_addpage);
        btn_addpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification secondpage =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Second Page")
                                .setContentText("第二頁")
                                .build();

                Notification firstpage =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("First Page")
                                .setContentText("第一頁")
                                .extend(new WearableExtender()
                                        .addPage(secondpage))
                                .build();

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);

                notificationManager.notify(notificationId, firstpage);
            }
        });
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CharSequence msg = "";
        Bundle remoteInput =
                RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            msg = remoteInput.getCharSequence(
                    MainActivity.EXTRA_VOICE_REPLY);
        }
        tv_reply.setText(msg);
    }
}
