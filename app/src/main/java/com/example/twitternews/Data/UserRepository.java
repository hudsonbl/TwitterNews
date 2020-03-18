package com.example.twitternews.Data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.twitternews.Utils.TwitterUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UserRepository {
    private MutableLiveData<ArrayList<TwitterData>> mUserResults;
//        mTwitterData.add(mTwitterUtil.searchForUserTweets(searchQuery, 5));
    private String mCurrentQuery;
    private TwitterUtil mTwitterUtil = new TwitterUtil();

    public UserRepository() {
        mUserResults = new MutableLiveData<>();
        mUserResults.setValue(null);
    }

    public LiveData<ArrayList<TwitterData>> getUserResults() {
        return mUserResults;
    }

    public void LoadUserResults(String query){
        mUserResults.setValue(null);
        Log.d(TAG, "GETTING USERS WITH NAME: " + query);
//        mUserResults.add(mTwitterUtil.searchForUserTweets(searchQuery, 5));
    }


}
