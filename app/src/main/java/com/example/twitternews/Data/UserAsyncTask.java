package com.example.twitternews.Data;

import android.os.AsyncTask;

import com.example.twitternews.Utils.TwitterUtil;

import java.util.List;

public class UserAsyncTask extends AsyncTask<String, Void, String> {

    private Callback mCallback;
    private int mNumTweets;
    private String mUser;


    UserAsyncTask(Callback callback, int numTweets){
        mNumTweets = numTweets;
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String twitter_account = strings[0];
        if(twitter_account != null){
            mUser = twitter_account;
        }
        return mUser;
    }

    public interface Callback {
        void onSearchFinished(TwitterData searchResults);
    }

    @Override
    protected void onPostExecute(String account) {
        TwitterData userResults = null;
        TwitterUtil twitterUtil = new TwitterUtil();

        if(account != null){
            userResults = (twitterUtil.searchForUserTweets(mUser, mNumTweets));
        }
        mCallback.onSearchFinished(userResults);
    }
}
