package lincyu.chapter6_horoscope2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HoroscopeActivity extends ActionBarActivity {

    TextView result;
    ImageView symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        Button backbtn = (Button)findViewById(R.id.backbtn);
        backbtn.setOnClickListener(back);
        result = (TextView)findViewById(R.id.result);
        symbol = (ImageView)findViewById(R.id.imgSymbol);

        Intent intent = getIntent();
        int month = intent.getIntExtra(
                SetBirthdayActivity.KEY_MONTH, 1);
        int day = intent.getIntExtra(
                SetBirthdayActivity.KEY_DAY, 1);
        setHoroscope(month, day);
    }

    private OnClickListener back = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public void setHoroscope(int intMonth, int intDay) {
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

