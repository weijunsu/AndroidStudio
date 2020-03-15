package lincyu.chapter17_androidbeam;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.util.Arrays;


public class ReaderActivity extends ActionBarActivity {

    private TextView tv_msg;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        tv_msg = (TextView)findViewById(R.id.tv_msg);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        Intent intent = new Intent(this, getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);
    }

    @Override
    public void onNewIntent(Intent intent) {

        String msg = "Waiting";
        String action = intent.getAction();
        if (!action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            msg = "Action: " + action + "\n\n";
            tv_msg.setText(msg);
            return;
        }

        Parcelable[] data = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (data != null) {
            try {
                msg = getPayload(data);
            } catch (Exception e) {
                tv_msg.setText(e.toString());
            }
        }
        tv_msg.setText(msg);
    }

    private String getPayload(Parcelable[] data) {
        String msg = "Waiting";
        for (int i = 0; i < data.length; i++) {
            NdefRecord[] recs = ((NdefMessage)data[i]).
                    getRecords();
            for (int j = 0; j < recs.length; j++) {
                NdefRecord rec = recs[j];
                short tnf = rec.getTnf();
                byte [] type = rec.getType();
                if (tnf == NdefRecord.TNF_WELL_KNOWN &&
                    Arrays.equals(type, NdefRecord.RTD_TEXT)) {
                    byte[] payload = rec.getPayload();
                    msg = new String(payload);
                }
            }
        }
        return msg;
    }

    @Override
    public void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this,
                pendingIntent, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }
}
