package com.example.twitternews.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.twitternews.Utils.TwitterUtil;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Twitter;

import static android.content.ContentValues.TAG;

public class UserAsyncTask extends AsyncTask<Void, Void, String> {
    private String mUserName;
    private ArrayList<TwitterData> mUsers = new ArrayList<>();
    private TwitterUtil mTwitterUtil = new TwitterUtil();
    private Callback mCallback;

    UserAsyncTask(String userName, Callback callback){
        mUserName = userName;
        mCallback = callback;
    }

    public interface Callback {
        void onSearchFinished(ArrayList<TwitterData> searchResults);
    }


    @Override
    protected String doInBackground(Void... voids) {
        String results = "good";
        Boolean returnType = false;

        if(mUserName != null){
                returnType = mUsers.add(mTwitterUtil.searchForUserTweets(mUserName, 5));
        }
        return results;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<TwitterData> userResults = null;
        if(s!=null){
            Log.d(TAG,"THIS IS WHERE WE ADD USERS?");
        }

    }
}
