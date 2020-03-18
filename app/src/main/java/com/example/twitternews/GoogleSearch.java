package com.example.twitternews;



import android.os.StrictMode;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.twitternews.Data.WebData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.content.ContentValues.TAG;

public class GoogleSearch {

    private static final String GOOGLE_SEARCH_URL = "https://duckduckgo.com/html/";

    public static ArrayList<WebData> doSearch(String tweet, Integer numResults) throws IOException {
        ArrayList<WebData> webList = new ArrayList<WebData>();
        Integer iterator = 0;
        //Taking search term input from console
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String searchURL = GOOGLE_SEARCH_URL + "?q="+tweet;
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla").get();

        Elements results = doc.getElementById("links").children();
        for (Element result : results) {
            WebData item = new WebData();
            if(result.getElementsByClass("result__a").first() != null) {
                iterator = iterator + 1;
                String title = result.getElementsByClass("result__a").first().text();
                String url = result.getElementsByClass("result__snippet").first().attr("href");
                Log.d("URL:", url);
                Log.d("TITLE:", title);
                item.Title = title;
                item.URL = url.replace("/l/?kh=-1&uddg=","");
                webList.add(item);
                if (iterator.equals(numResults)){
                    break;
                }
            }
        }
        return webList;
    }

}

