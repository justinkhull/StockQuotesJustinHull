package com.example.justinkhull.stockquotes_justinhull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String symbol = "GOOG";

        RetreiveData retreiveData = new RetreiveData(symbol);
        retreiveData.execute(symbol);
    }
}
