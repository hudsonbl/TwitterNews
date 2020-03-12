package com.example.twitternews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.twitternews.Data.DummyData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserSearchAdapter.OnSearchResultClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mTwitterUserSearchRV;
    private EditText mSearchBoxET;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mErrorMessageTV;
    private UserSearchAdapter mUserSearchAdapter;
    private Spinner mSpinner;
    private DummyData dummyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyData = new DummyData();

        mSearchBoxET = findViewById(R.id.et_search_box);
        mTwitterUserSearchRV = findViewById(R.id.rv_search_results);

        mTwitterUserSearchRV.setLayoutManager(new LinearLayoutManager(this));
        mTwitterUserSearchRV.setHasFixedSize(true);

        mUserSearchAdapter = new UserSearchAdapter(this);
        mTwitterUserSearchRV.setAdapter(mUserSearchAdapter);

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_bar);
        mErrorMessageTV = findViewById(R.id.tv_error_message);

        doUserSearch("Hello World"); // This would usually be in onclick but right now is just used to put dummy data to screen

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if(!TextUtils.isEmpty(searchQuery)){
                    Log.d(TAG, "Searching User: " + searchQuery);
                }
            }
        });
    }

    // TODO: 3/1/2020 Need to interface with a search to twitter api
    // This just posts to tv.
    private void doUserSearch(String searchQuery){
        ArrayList<DummyData> searchResult = new ArrayList<>();
        Log.d(TAG, "Inside of user search: " + searchQuery);
        for(int i = 0; i < 6; i++){
            DummyData tempData = new DummyData();
            Log.d(TAG, "Assigning user: " + dummyData.twitter_users[i]);
            tempData.twitter_user = dummyData.twitter_users[i];
            searchResult.add(tempData);
            mUserSearchAdapter.updateSearchResults(searchResult);
        }
        for(int i = 0; i < 6; i++){
            DummyData tempData = new DummyData();
            Log.d(TAG, "Assigning user: " + dummyData.twitter_users[i]);
            tempData.twitter_user = dummyData.twitter_users[i];
            searchResult.add(tempData);
            mUserSearchAdapter.updateSearchResults(searchResult);
        }
    }

    @Override
    public void onSearchResultClicked(DummyData repo) {
        Log.d(TAG, "Search query?" + repo.twitter_user);
    }
}
