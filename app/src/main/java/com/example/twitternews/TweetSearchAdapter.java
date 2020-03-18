package com.example.twitternews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.DummyData;
import com.example.twitternews.Data.TwitterData;

import java.util.ArrayList;

public class TweetSearchAdapter extends RecyclerView.Adapter<TweetSearchAdapter.TweetResultViewHolder> {

    // TODO: 3/17/2020 : Dummy Data replace
    private TwitterData mTwitterData;
    private OnTweetResultClickListener mClickListener;

    interface OnTweetResultClickListener{
        // TODO: 3/17/2020 update dummy data
        void onTweetResultClicked(TwitterData data);
    }

    public TweetSearchAdapter(OnTweetResultClickListener listener){
        mClickListener = listener;
    }
    
    public void updateTweetResults(TwitterData tweetResults){
        // TODO: 3/17/2020 will need to change
        mTwitterData = tweetResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TweetResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.twitter_tweet_item, parent, false);
        return new TweetResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetResultViewHolder holder, int position) {
        // TODO: 3/17/2020 change this bull shit
        holder.bind(TwitterData);
    }

    @Override
    public int getItemCount() {
        // TODO: 3/17/2020 change dummy data
        if(mTwitterData != null){
            return mTwitterData.twitter_tweets.size();
        }else{
            return 0;
        }
    }

    public class TweetResultViewHolder extends RecyclerView.ViewHolder{
        private TextView mSearchQueryTV;

        public TweetResultViewHolder(@NonNull View itemView) {
            super(itemView);
            mSearchQueryTV = itemView.findViewById(R.id.tv_tweet_item);
            String item = (String) mSearchQueryTV.getText();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onTweetResultClicked(
                            mTwitterData.get(getAdapterPosition())
                    );
                }
            });
        }

        // TODO: 3/17/2020 change this, this needs to update with query result for a good title from web crawl
        void bind(TwitterData data){
            mSearchQueryTV.setText(data.twitter_tweet);
        }
    }
}
