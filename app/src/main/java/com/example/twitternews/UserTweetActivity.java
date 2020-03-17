package com.example.twitternews;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.DummyData;

import java.util.ArrayList;

public class UserTweetActivity extends AppCompatActivity implements TweetSearchAdapter.OnTweetResultClickListener{
    public static final String TWEET_QUERIE_ACTIVITY = "TweetSearch";
    private static final String TAG = UserTweetActivity.class.getSimpleName();

    // TODO: 3/17/2020 Need to change this to real data type: twitterdata json 
    private DummyData mData;
    TextView mUserNameTV;
    RecyclerView mUserTweetsRV;
    TweetSearchAdapter tweetAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(TWEET_QUERIE_ACTIVITY)){
            mData = (DummyData)intent.getSerializableExtra(TWEET_QUERIE_ACTIVITY);
            mUserNameTV = findViewById(R.id.tv_user_profile_name);
            mUserNameTV.setText(mData.twitter_user);

            mUserTweetsRV = findViewById(R.id.rv_tweet_results);
            mUserTweetsRV.setLayoutManager(new LinearLayoutManager(this));
            mUserTweetsRV.setHasFixedSize(true);
            
            tweetAdapter = new TweetSearchAdapter(this);
            mUserTweetsRV.setAdapter(tweetAdapter);
            // TODO: 3/17/2020 update this to actually search tweets JSOUP API 
            doTweetSearches("Hello world");
        }
    }
    
    private void doTweetSearches(String searchQuery){
        // TODO: 3/17/2020 May change this to
        ArrayList<DummyData> tweetSearch = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            DummyData tempData = new DummyData();
            Log.d(TAG, "Adding tweet search query");
            tempData.twitter_tweet = tempData.twitter_tweets[i];
            tweetSearch.add(tempData);
            tweetAdapter.updateTweetResults(tweetSearch);
        }
    }

    @Override
    public void onTweetResultClicked(DummyData data){
        Log.d(TAG, "This is where something will happen with new data");
    }
}
