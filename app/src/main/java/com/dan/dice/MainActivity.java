package com.dan.dice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText etNumber;
    Button btnRoll;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = (EditText) findViewById(R.id.etNumber);
        btnRoll = (Button) findViewById(R.id.btnRoll);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo.setVisibility(View.GONE);


        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberRolls;
//                int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
                int randomNumber;

                numberRolls = Integer.parseInt(etNumber.getText().toString().trim());
                new ProcessDiceInBackgroud().execute(numberRolls);

//                Random random = new Random();
//
//                String results;
//
//                for (int i = 0; i < numberRolls; i++) {
//                    randomNumber = random.nextInt(6) + 1;
//
//                    switch (randomNumber) {
//                        case 1:
//                            ones++;
//                            break;
//                        case 2:
//                            twos++;
//                            break;
//                        case 3:
//                            threes++;
//                            break;
//                        case 4:
//                            fours++;
//                            break;
//                        case 5:
//                            fives++;
//                            break;
//                        default:
//                            sixes++;
//
//                    }
//                }
//
//
//                results = "Result are:\n1:" + ones + "\n2:" + twos + "\n3:" + threes + "\n4:" + fours + "\n5:" + fives
//                        + "\n6:" + sixes;
//                tvInfo.setText(results);
//                Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ProcessDiceInBackgroud extends AsyncTask<Integer, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(Integer.parseInt(etNumber.getText().toString().trim()));
            dialog.show();
        }

        @Override
        protected String doInBackground(Integer... integers) {

//            int numberRolls;
            int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
            int randomNumber;

//            numberRolls = Integer.parseInt(etNumber.getText().toString().trim());

            Random random = new Random();

            String results;

            double currentProgress;
            double previousProgress=0;

            for (int i = 0; i < integers[0]; i++) {
                randomNumber = random.nextInt(6) + 1;

                currentProgress = (double) i / integers[0];

                if (currentProgress - previousProgress >= 0.02) {
                    publishProgress(i);
                    previousProgress = currentProgress;
                }

                switch (randomNumber) {
                    case 1:
                        ones++;
                        break;
                    case 2:
                        twos++;
                        break;
                    case 3:
                        threes++;
                        break;
                    case 4:
                        fours++;
                        break;
                    case 5:
                        fives++;
                        break;
                    default:
                        sixes++;

                }
            }


            results = "Result are:\n1:" + ones + "\n2:" + twos + "\n3:" + threes + "\n4:" + fours + "\n5:" + fives
                    + "\n6:" + sixes;
//            tvInfo.setText(results);
//            Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
//
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            dialog.setProgress(values[0]);
//            dialog.incrementProgressBy(1);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            dialog.dismiss();
tvInfo.setVisibility(View.VISIBLE);
            tvInfo.setText(s);
            Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
        }
    }
}
