package lincyu.chapter6_horoscope1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    EditText etMonth, etDay;
    TextView result;
    ImageView symbol;

    final int PAGE_SETBIRTHDAY = 1;
    final int PAGE_HOROSCOPE = 2;
    int currentpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageSetBirthday();
    }

    private void pageSetBirthday() {
        currentpage = PAGE_SETBIRTHDAY;
        setContentView(R.layout.layout_setbirthday);
        Button submit = (Button)findViewById(R.id.submit);
        etMonth = (EditText)findViewById(R.id.etMonth);
        etDay = (EditText)findViewById(R.id.etDay);
        submit.setOnClickListener(gethoroscope);
    }

    private OnClickListener gethoroscope = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int intMonth =
                    Integer.parseInt(etMonth.getText().toString());
            int intDay =
                    Integer.parseInt(etDay.getText().toString());
            if ((intMonth < 1 || intMonth > 12) ||
                    (intDay < 1 || intDay > 31)) {
                Toast.makeText(MainActivity.this,
                        R.string.input_error,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            pageHoroscope(intMonth, intDay);
        }
    };

    private void pageHoroscope(int month, int day) {
        currentpage = PAGE_HOROSCOPE;
        setContentView(R.layout.layout_horoscope);
        Button backbtn = (Button)findViewById(R.id.backbtn);
        backbtn.setOnClickListener(back);
        result = (TextView)findViewById(R.id.result);
        symbol = (ImageView)findViewById(R.id.imgSymbol);
        setUI(month, day);
    }

    private OnClickListener back = new OnClickListener() {
        @Override
        public void onClick(View v) {
            pageSetBirthday();
        }
    };

    public void setUI(int intMonth, int intDay) {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                currentpage == PAGE_HOROSCOPE) {
            pageSetBirthday();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
