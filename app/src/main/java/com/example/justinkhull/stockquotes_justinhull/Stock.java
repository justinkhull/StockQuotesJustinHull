package com.example.justinkhull.stockquotes_justinhull;

/**
 * Created by justinkhull on 3/13/18.
 */

import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Stock implements Serializable
{
    private static final boolean DEBUG = true;

    private static final String TAG_PREFIX = "stockquotes";

    private String symbol;
    private String lastTradeTime;
    private String lastTradePrice;
    private String change;
    private String range;
    private String name;


    public Stock(String symbol)
    {
        this.symbol = symbol.toUpperCase();

        if (DEBUG)
            Log.i(TAG_PREFIX + "Stock()", "symbol = " + symbol);

    }


    public ArrayList<String> load() throws MalformedURLException, IOException {
        ArrayList<String> views = new ArrayList<String>();

        URL url = new URL("https://api.iextrading.com/1.0/stock/" + symbol + "/book");

        if (DEBUG)
            Log.i(TAG_PREFIX + "Stock.load()", "url = " + url);

        URLConnection connection = url.openConnection();

        if (DEBUG)
            Log.i(TAG_PREFIX + "Stock.load()", "url connection opened");

        InputStreamReader isr = new InputStreamReader((connection.getInputStream()));

        if (DEBUG)
            Log.i(TAG_PREFIX + "Stock.load()", "input stream reader created");

        BufferedReader in = new
                BufferedReader(isr);

        if (DEBUG)
            Log.i(TAG_PREFIX + "Stock.load()", "buffered reader created");

        String line = in.readLine();

        if (DEBUG)
            Log.i(TAG_PREFIX + "Stock.load()", "line = " + line);


        // consume any data remaining in the input stream
        while (in.readLine() != null)
            ;

        in.close();

        if (line != null && line.length() > 0)
        {
            // parse the JSON
            JSONObject stock = JsonUtils.parseStockQuoteJson(line);

            try {
                symbol = stock.getString("symbol");
                lastTradeTime = stock.getString("latestTime");
                lastTradePrice = stock.getString("latestPrice");
                change = stock.getString("change");
                range = stock.getString("week52Low") + " - " + stock.getString("week52High");
                name = stock.getString("companyName");
            } catch (Exception ex) {
                Log.e(TAG_PREFIX, "Error retrieving data from JSON");
            }

            if (DEBUG)
                Log.i(TAG_PREFIX + "Stock.load()", "name = " + name);
                views.add(name);

            //***ADDED CODE BELOW THIS LINE TO END OF METHOD

            if (DEBUG)
                Log.i(TAG_PREFIX + "Stock.load()", "symbol = " + symbol);
                views.add(symbol);

            if (DEBUG)
                Log.i(TAG_PREFIX + "Stock.load()", "lastTradePrice = " + getLastTradePrice());
                views.add(getLastTradePrice());

            if (DEBUG)
                Log.i(TAG_PREFIX + "Stock.load()", "lastTradeTime = " + getLastTradeTime());
                views.add(getLastTradeTime());

            if (DEBUG)
                Log.i(TAG_PREFIX + "Stock.load()", "change = " + getChange());
                views.add(getChange());

            if (DEBUG)
                Log.i(TAG_PREFIX + "Stock.load()", "range = " + getRange());
                views.add(getRange());
        }
        return views;
    }


    /**
     * Returns the stock's last trade time.
     */
    public String getLastTradeTime()
    {
        return  lastTradeTime;
    }


    /**
     * Returns the stock's last trade price.
     */
    public String getLastTradePrice()
    {
        return lastTradePrice;
    }


    /**
     * Returns the stock's .
     */
    public String getChange()
    {
        return change;
    }


    /**
     * Returns the stock's 52-week range.
     */
    public String getRange()
    {
        return range;
    }


    /**
     * Returns the stock's name; e.g., Google, Inc.
     */
    public String getName()
    {
        return name;
    }


    /**
     * Returns the stock's symbol; e.g., GOOG.
     */
    public String getSymbol()
    {
        return symbol;
    }
}
