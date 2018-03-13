package com.example.justinkhull.stockquotes_justinhull;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by justinkhull on 3/13/18.
 */

public class RetreiveData extends AsyncTask<String, Void, String> {

    /*public RetreiveData(String symbol) {
        Stock myStock = new Stock(symbol);
    }*/

    private String symbol;
    public RetreiveData(String symbol) {
        this.symbol = symbol;
        Log.i("String", symbol);
    };

    @Override
    protected String doInBackground (String... myString) {
        String endString = "";
        Log.i("String", "MADE IT TO DOINBACKGROUND");
        Log.i("String", symbol);
        Stock myStock = new Stock(symbol);
        try {
            myStock.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return endString;
    }
}
