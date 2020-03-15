package lincyu.chapter6_horoscope3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HoroscopeActivity extends ActionBarActivity {

    private TextView result;
    private ImageView symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        result = (TextView)findViewById(R.id.tv_horoscope);
        symbol = (ImageView)findViewById(R.id.iv_horoscope);
        Button button = (Button)findViewById(R.id.btn_setbirthday);
        button.setOnClickListener(setBirthday);
    }

    private static final int ACTIVITY_SET_BIRTHDAY = 1;

    private OnClickListener setBirthday = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(HoroscopeActivity.this,
                    SetBirthdayActivity.class);
            startActivityForResult(intent, ACTIVITY_SET_BIRTHDAY);
        }
    };

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent intent) {

        int intMonth, intDay;

        if (intent == null)
            return;

        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode) {
            case ACTIVITY_SET_BIRTHDAY:
                intMonth = intent.getIntExtra("KEY_MONTH", 1);
                intDay = intent.getIntExtra("KEY_DAY", 1);
                if (intDay < bound[intMonth-1]) {
                    result.setText(resId[intMonth-1]);
                    symbol.setImageResource(sybId[intMonth-1]);
                } else {
                    if (intMonth == 12) {
                        result.setText(resId[0]);
                        symbol.setImageResource(sybId[0]);
                    } else {
                        result.setText(resId[intMonth]);
                        symbol.setImageResource(sybId[intMonth]);
                    }
                }
                break;
        }
    }

    final int [] bound = {20, 19, 21, 20, 21, 21,
            23, 23, 23, 23, 22, 22};
    final int [] resId = {
            R.string.Capricorn, R.string.Aquarius, R.string.Pisces,
            R.string.Aries, R.string.Taurus, R.string.Gemini,
            R.string.Cancer, R.string.Leo, R.string.Virgo,
            R.string.Libra, R.string.Scorpio, R.string.Sagittarius};
    final int [] sybId = {
            R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces,
            R.drawable.aries, R.drawable.taurus, R.drawable.gemini,
            R.drawable.cancer, R.drawable.leo, R.drawable.virgo,
            R.drawable.libra, R.drawable.scorpio, R.drawable.sagittarius};
}
