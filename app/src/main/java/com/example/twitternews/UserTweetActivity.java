package com.example.twitternews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.TwitterData;
import com.example.twitternews.Data.WebData;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserTweetActivity extends AppCompatActivity implements TweetSearchAdapter.OnTweetResultClickListener{
    public static final String TWEET_QUERIE_ACTIVITY = "TweetSearch";
    private static final String TAG = UserTweetActivity.class.getSimpleName();

    private TwitterData mData;
    private TextView mUserNameTV;
    private RecyclerView mUserTweetsRV;
    private TweetSearchAdapter tweetAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(TWEET_QUERIE_ACTIVITY)){
            mData = (TwitterData) intent.getSerializableExtra(TWEET_QUERIE_ACTIVITY);
            mUserNameTV = findViewById(R.id.tv_user_profile_name);
            mUserNameTV.setText(mData.twitter_username);

            mUserTweetsRV = findViewById(R.id.rv_tweet_results);
            mUserTweetsRV.setLayoutManager(new LinearLayoutManager(this));
            mUserTweetsRV.setHasFixedSize(true);
            
            tweetAdapter = new TweetSearchAdapter(this);
            mUserTweetsRV.setAdapter(tweetAdapter);
            // TODO: 3/17/2020 update this to actually search tweets JSOUP API
            // check main activity lines 36-44 for how to get the urls :)

            postTweetsToRV(mData.twitter_tweets);
        }
    }
    
    private void postTweetsToRV(List<String> searchQuery){
        tweetAdapter.updateTweetResults(searchQuery);
    }

    @Override
    public void onTweetResultClicked(String tweet){
        Log.d(TAG, "This is where something will happen with new data");
        Intent intent = new Intent(this, WebTweetActivity.class);
        intent.putExtra(WebTweetActivity.TWEET_WEB_ACTIVITY, tweet);
        startActivity(intent);
        // open next intent, create web items and display them.

    }
}
