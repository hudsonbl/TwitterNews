package com.example.twitternews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.TwitterData;

import java.util.ArrayList;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.SearchResultViewHolder>
{
    private ArrayList<TwitterData> mSearchResultList;
    private OnSearchResultClickListener mResultClickListener;

    interface OnSearchResultClickListener {
        void onSearchResultClicked(TwitterData repo);
    }

    public UserSearchAdapter(OnSearchResultClickListener listener) {
        mResultClickListener = listener;
    }

    public void updateSearchResults(ArrayList<TwitterData> searchResultsList){
        mSearchResultList = searchResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mSearchResultList != null){
            return mSearchResultList.size();
        }else{
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.twitter_profile_search_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(mSearchResultList.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder{
        private TextView mSearchResultTV;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mResultClickListener.onSearchResultClicked(
                            mSearchResultList.get(getAdapterPosition())
                    );
                }
            });
        }

        void bind(TwitterData repo) {
            mSearchResultTV.setText(repo.twitter_username);
        }
    }
}
