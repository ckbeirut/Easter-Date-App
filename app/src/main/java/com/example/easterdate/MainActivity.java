package com.example.easterdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Edit = findViewById(R.id.editTextDate);
        final TextView text = findViewById(R.id.textViewWestern);
        final TextView textEastern = findViewById(R.id.textViewEastern);
        final EditText eastern = findViewById(R.id.eastern);
        final EditText western = findViewById(R.id.western);

        eastern.setVisibility(View.INVISIBLE);
        western.setVisibility(View.INVISIBLE);

        Edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() == 0 || Integer.parseInt(s.toString()) < 1800) {
                    text.setText("");
                    textEastern.setText("");
                    eastern.setVisibility(View.INVISIBLE);
                    western.setVisibility(View.INVISIBLE);
                    return;
                }

                EditText Edit = findViewById(R.id.editTextDate);
                int AN = Integer.parseInt(Edit.getText().toString());

                int G = AN % 19;
                int C = AN / 100;
                int H = (C - C / 4 - (8 * C + 13) / 25 + 19 * G + 15) % 30;
                int I = H - (H / 28) * (1 - (H / 28) * (29 / (H + 1)) * ((21 - G) / 11));
                int J = (AN + AN / 4 + I + 2 - C + C / 4) % 7;

                int L = I - J;
                int MP = 3 + (L + 40) / 44;
                int JP = L + 28 - 31 * (MP / 4);

                int mon, day;

                int b = AN % 7;
                int ce = AN % 4;
                int d = (19 * G + 16) % 30;
                int e = (2 * ce + 4 * b + 6 * d) % 7;
                int f = (19 * G + 16) % 30;
                int key = f + e + 3;
                if (key > 30)
                    mon = 5;
                else
                    mon = 4;
                if (key > 30)
                    day = key - 30;
                else day = key;

                String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

                eastern.setVisibility(View.VISIBLE);
                western.setVisibility(View.VISIBLE);
                text.setText("Sunday " + JP + " " + month[MP - 1]);
                textEastern.setText("Sunday " + day + " " + month[mon - 1]);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }
}