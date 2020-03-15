package lincyu.chapter4_abgame2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView result, tv_showanswer;
    Button submit, showanswer;
    EditText input;
    int [] answer = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.tv_result);
        input = (EditText)findViewById(R.id.et_input);
        submit = (Button)findViewById(R.id.btn_submit);
        submit.setOnClickListener(calcResult);
        showanswer = (Button)findViewById(R.id.btn_showanswer);
        showanswer.setOnClickListener(showAnswerListener);
        tv_showanswer = (TextView)findViewById(R.id.tv_showanswer);
        GenerateAnswer();
    }

    private OnClickListener calcResult = new OnClickListener() {

        @Override
        public void onClick(View v) {

            String input_str =input.getText().toString();
            input.setText("");

            if (input_str.length() != 4) {
                Toast.makeText(MainActivity.this,
                        R.string.input_error,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            result.setText(Compare(input_str));
        }
    };

    private OnClickListener showAnswerListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            String answer_str = "答案為：";
            for (int i = 0; i < 4; i++)
                answer_str += answer[i];
            tv_showanswer.setText(answer_str);

        }
    };

    private void GenerateAnswer() {
        for (int i = 0; i < 4; i++) {
            boolean breakflag = true;
            do {
                breakflag = true;
                answer[i] = (int)(Math.random()*10);
                for (int j = 0; j < i; j++) {
                    if (answer[i] == answer[j]) {
                        breakflag = false;
                        break;
                    }
                }
            } while (!breakflag);
        }
    }

    private String Compare(String input_str) {
        String result = new String();
        int guess = Integer.parseInt(input_str);
        int [] guessarray = new int[4];
        guessarray[0] = guess/1000;
        guessarray[1] = (guess%1000)/100;
        guessarray[2] = (guess%100)/10;
        guessarray[3] = (guess%10);

        int counta = 0, countb = 0;
        for (int i = 0; i < 4; i++) {
            if (guessarray[i] == answer[i])
                counta++;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;
                if (guessarray[i] == answer[j])
                    countb++;
            }
        }
        result = (String)getText(R.string.result);
        result = result + counta + "A" + countb + "B";
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
