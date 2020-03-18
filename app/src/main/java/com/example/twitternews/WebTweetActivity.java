package com.example.twitternews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.WebData;

import java.util.List;

public class WebTweetActivity extends AppCompatActivity implements WebSearchAdapter.OnWebClickedListener {
    public static final String TWEET_WEB_ACTIVITY = "TweetToWeb";
    private static final String TAG = WebTweetActivity.class.getSimpleName();

    private String mTweetQuery;
    private TextView mQueryNameTV;
    private RecyclerView mWebEntriesRV;
    private WebSearchAdapter mWSearchAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_tweet_detail);

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
        // mWSearchAdapter.updateWebData();
    }

    @Override
    public void onWebClickedListener(WebData webData){
        String url = webData.URL;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
