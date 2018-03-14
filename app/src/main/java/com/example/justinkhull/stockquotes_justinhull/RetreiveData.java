package com.example.justinkhull.stockquotes_justinhull;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by justinkhull on 3/13/18.
 */

public class RetreiveData extends AsyncTask<String, Void, ArrayList<String>> {

    /*public RetreiveData(String symbol) {
        Stock myStock = new Stock(symbol);
    }*/

    private ArrayList<String> views;

    private String symbol;

    private TextView symbol_view;
    private TextView name_view;
    private TextView lastTradePrice_view;
    private TextView lastTradeTime_view;
    private TextView change_view;
    private TextView range_view;

    public RetreiveData(String symbol, TextView symbol_view, TextView name_view, TextView lastTradePrice_view, TextView lastTradeTime_view, TextView change_view, TextView range_view) {
        this.symbol = symbol;
        this.symbol_view = symbol_view;
        this.name_view = name_view;
        this.lastTradePrice_view = lastTradePrice_view;
        this.lastTradeTime_view = lastTradeTime_view;
        this.change_view = change_view;
        this.range_view = range_view;
        Log.i("String", symbol);
    };

    @Override
    protected ArrayList<String> doInBackground (String... myString) {
        String endString = "";
        Log.i("String", "MADE IT TO DOINBACKGROUND");
        Log.i("String", symbol);
        Stock myStock = new Stock(symbol);
        try {
            views = myStock.load();

            Log.i("String", views.get(1));
            /*symbol_view.setText(views.get(1));
            name_view.setText(views.get(0));
            lastTradePrice_view.setText(views.get(2));
            lastTradeTime_view.setText(views.get(3));
            change_view.setText(views.get(4));
            range_view.setText(views.get(5));*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        return views;
    }

    @Override
    protected void onPostExecute(ArrayList<String> endString) {
        symbol_view.setText(views.get(1));
        name_view.setText(views.get(0));
        lastTradePrice_view.setText(views.get(2));
        lastTradeTime_view.setText(views.get(3));
        change_view.setText(views.get(4));
        range_view.setText(views.get(5));
    }
}
