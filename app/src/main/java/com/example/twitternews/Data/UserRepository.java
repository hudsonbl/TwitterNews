package com.example.twitternews.Data;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.twitternews.Utils.TwitterUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UserRepository implements UserAsyncTask.Callback{
    private static final String TAG = UserRepository.class.getSimpleName();
    private MutableLiveData<List<TwitterData>> mUserResults;
    private List<TwitterData> mDummyList;

    private String mCurrentQuery;
    private int mNumTweets;
    private TwitterUtil mTwitterUtil = new TwitterUtil();

    public UserRepository() {
        mUserResults = new MutableLiveData<>();
        mUserResults.setValue(null);
    }

    public LiveData<List<TwitterData>> getUserResults() {
        return mUserResults;
    }

    private boolean shouldExecuteSearch(String searchQuery, int numTweets){
        return !TextUtils.equals(searchQuery, mCurrentQuery)
                || mNumTweets != numTweets;
    }

    public void loadUserSearch(String searchQuery, int numTweets) {
        if(shouldExecuteSearch(searchQuery, numTweets)){
            mCurrentQuery = searchQuery;
            mNumTweets = numTweets;

            mUserResults.setValue(null);
            new UserAsyncTask(this, mNumTweets).execute(searchQuery);
        }else{
            Log.d(TAG, "Using cached search results");
        }
    }

    @Override
    public void onSearchFinished(TwitterData searchResults) {
        mDummyList.add(searchResults);
        mUserResults.setValue(mDummyList);
    }
}
