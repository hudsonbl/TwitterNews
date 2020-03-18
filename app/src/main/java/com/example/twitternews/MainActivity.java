package com.example.twitternews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.twitternews.Data.TwitterData;
import com.example.twitternews.Utils.TwitterUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.twitternews.Data.WebData;

public class MainActivity extends AppCompatActivity implements UserSearchAdapter.OnSearchResultClickListener {
  private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mTwitterUserSearchRV;
    private EditText mSearchBoxET;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mErrorMessageTV;
    private UserSearchAdapter mUserSearchAdapter;
    private TwitterUtil mTwitterUtil;
    private ArrayList<TwitterData> mTwitterData;
    private TweetPackageViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String numTweets = sharedPreferences.getString("num_tweets", "5");
        Log.d(TAG, "NUMBER OF TWEETS: " + numTweets);

        mTwitterUtil = new TwitterUtil();
        mTwitterData = new ArrayList<TwitterData>();

        mSearchBoxET = findViewById(R.id.et_search_box);
        mTwitterUserSearchRV = findViewById(R.id.rv_search_results);

        mTwitterUserSearchRV.setLayoutManager(new LinearLayoutManager(this));
        mTwitterUserSearchRV.setHasFixedSize(true);

        mUserSearchAdapter = new UserSearchAdapter(this);
        mTwitterUserSearchRV.setAdapter(mUserSearchAdapter);

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_bar);
        mErrorMessageTV = findViewById(R.id.tv_error_message);

        mViewModel = new ViewModelProvider(this).get(TweetPackageViewModel.class);
        mViewModel.getTwitterData().observe(this, new Observer<List<TwitterData>>(){
            @Override
            public void onChanged(List<TwitterData> twitterData){
                mUserSearchAdapter.updateSearchResults((ArrayList<TwitterData>) twitterData);
            }
        });

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if(!TextUtils.isEmpty(searchQuery)){
                    Log.d(TAG, "Searching User: " + searchQuery);
                    doUserSearch(searchQuery);
                }
            }
        });
    }

    private void doUserSearch(String searchQuery){
        Log.d(TAG, "Inside of user search: " + searchQuery);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int numTweets = preferences.getInt("num_tweets", 5);

        mViewModel.loadUserSearch(searchQuery, numTweets);
    }

    @Override
    public void onSearchResultClicked(TwitterData repo) {
        Log.d(TAG, "Search query?" + repo.twitter_username);
        Intent intent = new Intent(this, UserTweetActivity.class);
        intent.putExtra(UserTweetActivity.TWEET_QUERIE_ACTIVITY, repo);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.action_settings:
                Log.d(TAG, "SELECTED!");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
