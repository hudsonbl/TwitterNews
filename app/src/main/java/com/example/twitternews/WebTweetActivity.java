package com.example.twitternews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.WebData;

import java.io.IOException;
import java.util.List;

public class WebTweetActivity extends AppCompatActivity implements WebSearchAdapter.OnWebClickedListener {
    public static final String TWEET_WEB_ACTIVITY = "TweetToWeb";
    private static final String TAG = WebTweetActivity.class.getSimpleName();

    private String mTweetQuery;
    private TextView mQueryNameTV;
    private RecyclerView mWebEntriesRV;
    private WebSearchAdapter mWSearchAdapter;
    private List<WebData> mWebList;
    private Integer mNumResults;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_tweet_detail);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String numResults = sharedPreferences.getString("num_tweets", "5");
        mNumResults = Integer.parseInt(numResults);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(TWEET_WEB_ACTIVITY)){
            mTweetQuery = (String) intent.getSerializableExtra(TWEET_WEB_ACTIVITY);
            mQueryNameTV = findViewById(R.id.tv_tweet_web_query);
            // TODO: 3/18/2020 Set title of new activity to tweet selected
            mQueryNameTV.setText(mTweetQuery);

            mWebEntriesRV = findViewById(R.id.rv_tweet_web_results);
            mWebEntriesRV.setLayoutManager(new LinearLayoutManager(this));
            mWebEntriesRV.setHasFixedSize(true);

            mWSearchAdapter = new WebSearchAdapter(this);
            mWebEntriesRV.setAdapter(mWSearchAdapter);

            // TODO: 3/18/2020 This is where we web search
            doWebSearches(mTweetQuery);
        }
    }

    private void doWebSearches(String tweetQuery){
        Log.d(TAG, "Tweet Query Search: " + tweetQuery);
        // TODO: 3/18/2020 Do web searches, store them into a list then update adapter below
        try {
            mWebList = GoogleSearch.doSearch(tweetQuery, mNumResults);
        }catch (IOException e){
            e.printStackTrace();
        }
         mWSearchAdapter.updateWebData(mWebList);
    }

    @Override
    public void onWebClickedListener(WebData webData){
        String url = webData.URL;
        Log.d("WEBDATA URL!: ", url);
        Uri uri = Uri.parse(webData.URL);
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.putExtra(Intent.EXTRA_TEXT, uri);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }else{
            Log.d("WHAT", "THE EFF");
        }
//        startActivity(i);
    }
}
