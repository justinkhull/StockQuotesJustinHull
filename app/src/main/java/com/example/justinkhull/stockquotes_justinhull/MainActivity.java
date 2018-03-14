package com.example.justinkhull.stockquotes_justinhull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText symbol_input;
    private TextView symbol_view;
    private TextView name_view;
    private TextView lastTradePrice_view;
    private TextView lastTradeTime_view;
    private TextView change_view;
    private TextView range_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        symbol_input = findViewById(R.id.symbol_input);
        symbol_input.setInputType(InputType.TYPE_CLASS_TEXT);

        //String symbol = "GOOG";
        symbol_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                Log.d("keyCode", Integer.toString(keyCode));
                if ((keyEvent.getAction()==KeyEvent.ACTION_DOWN) && (keyCode==keyEvent.KEYCODE_ENTER)) {
                    String symbol = symbol_input.getText().toString();

                    symbol_view = findViewById(R.id.symbol);
                    name_view = findViewById(R.id.name);
                    lastTradePrice_view = findViewById(R.id.lastTradePrice);
                    lastTradeTime_view = findViewById(R.id.lastTradeTime);
                    change_view = findViewById(R.id.change);
                    range_view = findViewById(R.id.range);

                    RetreiveData retreiveData = new RetreiveData(symbol, symbol_view, name_view, lastTradePrice_view, lastTradeTime_view, change_view, range_view);
                    retreiveData.execute(symbol);

                    return true;
                }
                return false;
            }
        });
        //String symbol = symbol_input.getText().toString();

        /*symbol_view = findViewById(R.id.symbol);
        name_view = findViewById(R.id.name);
        lastTradePrice_view = findViewById(R.id.lastTradePrice);
        lastTradeTime_view = findViewById(R.id.lastTradeTime);
        change_view = findViewById(R.id.change);
        range_view = findViewById(R.id.range);

        RetreiveData retreiveData = new RetreiveData(symbol, symbol_view, name_view, lastTradePrice_view, lastTradeTime_view, change_view, range_view);
        retreiveData.execute(symbol);*/
    }
}
