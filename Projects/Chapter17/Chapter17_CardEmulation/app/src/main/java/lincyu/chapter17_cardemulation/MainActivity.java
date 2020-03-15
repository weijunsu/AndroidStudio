package lincyu.chapter17_cardemulation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private EditText et_msg;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("PREF_NFC",
                Context.MODE_PRIVATE);
        String msg = pref.getString("PREF_MSG", "");

        et_msg = (EditText) findViewById(R.id.et_msg);
        et_msg.setText(msg);
    }

    @Override
    public void onPause() {
        super.onPause();
        String msg = et_msg.getEditableText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PREF_MSG", msg);
        editor.commit();
    }
}
