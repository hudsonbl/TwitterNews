package com.example.twitternews.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TwitterData implements Serializable {
    public String twitter_username;
    public ArrayList<String> twitter_tweets;

    public TwitterData() {
        twitter_tweets = new ArrayList<>();
    }
}
