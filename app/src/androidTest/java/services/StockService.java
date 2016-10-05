package services;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import entities.StockQuoteEntity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class StockService {

    public StockService() {
    }

    public StockQuoteEntity fetchStocks(@NonNull final String stockSymbol) throws IOException, JSONException {
        String yahooURLFirst = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";
        String yahooURLSecond = "%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        final String url = yahooURLFirst + stockSymbol + yahooURLSecond;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json").build();
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonResponse).getJSONObject("query").getJSONObject("results").getJSONObject("quote");
        StockQuoteEntity stockEntity = new Gson().fromJson(jsonObject.toString(), StockQuoteEntity.class);
        response.close();
        return stockEntity;
    }


}
