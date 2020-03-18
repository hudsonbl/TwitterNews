package com.example.twitternews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.twitternews.Data.TwitterData;
import com.example.twitternews.Data.UserRepository;

import java.util.List;

public class TweetPackageViewModel extends ViewModel {
    private UserRepository mRepository;
    private LiveData<List<TwitterData>> mTwitterData;

    public TweetPackageViewModel(){
        mRepository = new UserRepository();
        mTwitterData = mRepository.getUserResults();
    }

    public void loadUserSearch(String searchQuery, int numTweets) {
        mRepository.loadUserSearch(searchQuery, numTweets);
    }

    public LiveData<List<TwitterData>> getTwitterData(){
        return mTwitterData;
    }

}
