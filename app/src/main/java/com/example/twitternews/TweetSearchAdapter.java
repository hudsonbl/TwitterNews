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
import java.util.List;

public class TweetSearchAdapter extends RecyclerView.Adapter<TweetSearchAdapter.TweetResultViewHolder> {

    private List<String> mTweetData;
    private OnTweetResultClickListener mClickListener;

    interface OnTweetResultClickListener{
        void onTweetResultClicked(String tweet);
    }

    public TweetSearchAdapter(OnTweetResultClickListener listener){
        mClickListener = listener;
    }
    
    public void updateTweetResults(List<String> tweetResults){
        mTweetData = tweetResults;
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
        holder.bind(mTweetData.get(position));
    }

    @Override
    public int getItemCount() {
        // TODO: 3/17/2020 change dummy data
        if(mTweetData != null){
            return mTweetData.size();
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
                            mTweetData.get(getAdapterPosition())
                    );
                }
            });
        }

        public void bind(String tweet) {
            mSearchQueryTV.setText(tweet);
        }
    }
}
