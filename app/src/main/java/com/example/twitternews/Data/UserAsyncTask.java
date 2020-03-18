package com.example.twitternews.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.twitternews.Utils.TwitterUtil;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UserAsyncTask extends AsyncTask<String, Void, TwitterData> {

    private Callback mCallback;
    private int mNumTweets;


    UserAsyncTask(Callback callback, int numTweets){
        mNumTweets = numTweets;
        mCallback = callback;
    }

    @Override
    protected TwitterData doInBackground(String... strings) {
        String twitter_account = strings[0];
        TwitterData data;
        TwitterUtil twitterUtil = new TwitterUtil();
        data = twitterUtil.searchForUserTweets(twitter_account, mNumTweets);
        return data;
    }

    public interface Callback {
        void onSearchFinished(List<TwitterData> searchResults);
    }

    @Override
    protected void onPostExecute(TwitterData account) {
        List<TwitterData> userResults = null;
        userResults.add(account);
        mCallback.onSearchFinished(userResults);
    }
}
