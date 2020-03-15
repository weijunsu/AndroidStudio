package lincyu.chapter17_rwtag;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class WriteActivity extends ActionBarActivity {

    EditText et_msg;
    Button btn_write, btn_read;
    TextView tv_result;

    NfcAdapter nfcAdapter;
    NdefMessage ndefMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        et_msg = (EditText)findViewById(R.id.et_msg);
        tv_result = (TextView)findViewById(R.id.tv_result);
        btn_write = (Button)findViewById(R.id.btn_write);
        btn_write.setOnClickListener(write_l);

        btn_read = (Button)findViewById(R.id.btn_read);
        btn_read.setOnClickListener(read_l);

    }



    @Override
    public void onResume() {
        super.onResume();
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "您的手機沒有支援 NFC",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (nfcAdapter.isEnabled() == false) {
            Toast.makeText(this, "請開啟 NFC 功能",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(
                    Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    View.OnClickListener write_l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String msg = et_msg.getEditableText().toString();
            if (msg.length() == 0) return;
            NdefRecord record = new NdefRecord(
                        NdefRecord.TNF_WELL_KNOWN,
                        NdefRecord.RTD_TEXT,
                        new byte[0], msg.getBytes());
            ndefMessage = new NdefMessage(record);
            Intent intent = new Intent(
                WriteActivity.this, WriteActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                        WriteActivity.this, 0, intent, 0);
            nfcAdapter.enableForegroundDispatch(WriteActivity.this,
                pendingIntent, null, null);
        }
    };

    @Override
    public void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        boolean succflag = writeTag(ndefMessage, tag);
        if (succflag) {
            tv_result.setText("成功寫入資料");
        } else {
            tv_result.setText("寫入資料失敗");
        }
    }

    private boolean writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;
        String mess = "";
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    ndef.close();
                    return false;
                }
                if (ndef.getMaxSize() < size) {
                    // Message is too long
                    ndef.close();
                    return false;
                }
                ndef.writeNdefMessage(message);
                return true;
            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if (format != null) {
                    format.connect();
                    format.format(message);
                    format.close();
                    return true;
                }
                format.close();
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    View.OnClickListener read_l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent(WriteActivity.this,
            ReadActivity.class);
        startActivity(intent);
        }
    };
}
