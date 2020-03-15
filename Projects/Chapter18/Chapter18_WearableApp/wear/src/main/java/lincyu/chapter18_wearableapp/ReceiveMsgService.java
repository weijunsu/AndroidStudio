package lincyu.chapter18_wearableapp;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class ReceiveMsgService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        String data = new String(messageEvent.getData());
        String path = messageEvent.getPath();
        String nid = messageEvent.getSourceNodeId();

        if (!path.equals("/lincyu/wear"))
            return;

        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("EXTRA_DATA", data);
        intent.putExtra("EXTRA_NID", nid);
        startActivity(intent);
    }
}
