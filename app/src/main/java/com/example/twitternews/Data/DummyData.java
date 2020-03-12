package com.example.twitternews.Data;

public class DummyData {
    // JSON Field we will extract
    public String twitter_user;

    // Dummy data for appending to screen
    public String twitter_users[] = {"Bloomberg",
                                     "Henry Adams",
                                     "George Washington",
                                     "Donald Trump",
                                     "Obama",
                                     "Bush"};

    public String twitter_tweets[] = {"Democratic debates",
                                      "Good root beer",
                                      "First president of United States",
                                      "What a meme",
                                      "Health care in US",
                                      "Fool me once, your not fooling me again"};

    public String twitter_tweet = "This is america, dont catch you slipping homie";

    @Override
    public String toString(){
        return this.twitter_tweet;
    }

}
