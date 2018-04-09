package com.vysocki.yuri.currency_converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertMoneyButtonClicked(View view) {
        Double euroValue = 0.812512696;
        TextView inputMoneyTextView = findViewById(R.id.inputMoneyTextView);
        Double result = Double.parseDouble(inputMoneyTextView.getText().toString());
        result *= euroValue;
        Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
