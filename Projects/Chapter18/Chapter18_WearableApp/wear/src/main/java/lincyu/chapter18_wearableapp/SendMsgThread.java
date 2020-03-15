package lincyu.chapter18_wearableapp;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;

public class SendMsgThread extends Thread {

    Activity activity;
    String msg;

    private GoogleApiClient client;

    SendMsgThread(Activity activity, String msg) {
        this.activity = activity;
        this.msg = msg;
    }

    @Override
    public void run() {
        client = getClient(activity);
        ArrayList<String> nodeids = getNodes();
        if (nodeids.size() == 0) return;
        String nodeid = nodeids.get(0);
        sendMessage(nodeid);
    }

    private GoogleApiClient getClient(Context context) {
        return new GoogleApiClient.Builder(context)
                .addApi(Wearable.API)
                .build();
    }

    private void sendMessage(String nodeId) {
        client.blockingConnect();
        Wearable.MessageApi.sendMessage(
                client, nodeId, "/lincyu/mobile", msg.getBytes());
        client.disconnect();
    }

    private ArrayList<String> getNodes() {
        ArrayList<String> results = new ArrayList<String>();
        client.blockingConnect();
        NodeApi.GetConnectedNodesResult nodes =
                Wearable.NodeApi.getConnectedNodes(client).await();
        for (Node node : nodes.getNodes()) {
            results.add(node.getId());
        }
        client.disconnect();
        return results;
    }
}
