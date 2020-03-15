package lincyu.chapter17_androidbeam;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BeamActivity extends ActionBarActivity {

    EditText et_msg;
    Button btn_beam, btn_reader;

    NfcAdapter nfcAdapter;
    NdefMessage ndefMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam);

        et_msg = (EditText)findViewById(R.id.et_msg);
        btn_beam = (Button)findViewById(R.id.btn_beam);
        btn_beam.setOnClickListener(beam_l);

        btn_reader = (Button)findViewById(R.id.btn_reader);
        btn_reader.setOnClickListener(reader_l);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            Toast.makeText(this, "您的手機沒有支援 NFC",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nfcAdapter.isEnabled() == false) {
            Toast.makeText(this, "請開啟 NFC 功能",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(
                    Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    View.OnClickListener beam_l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = et_msg.getEditableText().toString();
            NdefRecord record = new NdefRecord(
                    NdefRecord.TNF_WELL_KNOWN,
                    NdefRecord.RTD_TEXT,
                    new byte[0], msg.getBytes());
            ndefMessage = new NdefMessage(record);
            nfcAdapter.setNdefPushMessage(ndefMessage,
                    BeamActivity.this);
        }
    };

    View.OnClickListener reader_l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BeamActivity.this,
                    ReaderActivity.class);
            startActivity(intent);
        }
    };
}
