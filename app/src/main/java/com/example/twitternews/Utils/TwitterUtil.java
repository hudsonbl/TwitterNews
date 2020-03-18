package com.example.twitternews.Utils;

import android.os.StrictMode;
import android.util.Log;

import com.example.twitternews.Data.TwitterData;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import static android.content.ContentValues.TAG;

public class TwitterUtil{

    public TwitterUtil() { }
    public TwitterData searchForUserTweets(String user, int numTweets){
        TwitterData userData = new TwitterData();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Key")
                .setOAuthConsumerSecret("Key")
                .setOAuthAccessToken("Key")
                .setOAuthAccessTokenSecret("Key");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try{

            List<Status> statuses;
            if(user != null){
                statuses = twitter.getUserTimeline(user);
                int i = 0;

                userData.twitter_username = user;
                for(Status status : statuses){
                    i++;
                    Log.d(TAG, "Itr: " + i + " User name: " + user + " Tweet: " + status.getText());
                    Log.d(TAG, "Itr: " + i + " User name2: " + user + " Tweet: " + status.getText());
                    userData.twitter_tweets.add(status.getText());
                    if(i == numTweets){
                        return userData;
                    }
                }
            }else{
                return null;
            }
        }catch (TwitterException te) {
            te.printStackTrace();
        }
        return null;
    }
}