package com.example.twitternews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.DummyData;
import com.example.twitternews.Data.DummyDataTwo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.SearchResultViewHolder>
{
    // TODO: 3/1/2020 : Need to update DummyData value with actual data field
    private ArrayList<DummyData> mSearchResultList;
    private OnSearchResultClickListener mResultClickListener;

    interface OnSearchResultClickListener {
        // TODO: 3/1/2020 Update dummy data 
        void onSearchResultClicked(DummyData repo);
    }

    public UserSearchAdapter(OnSearchResultClickListener listener) {
        mResultClickListener = listener;
    }

    public void updateSearchResults(ArrayList<DummyData> searchResultsList){
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
        private Spinner mSpinner;
        //Will remove below to replace with live data
        DummyData data = new DummyData();

        // TODO: 3/11/2020 : Need to make this grab content from the twitter tweets and grab url.
        // TODO: 3/11/2020 Need to construct a class to do this


        SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);
            mSpinner = itemView.findViewById(R.id.s_user_queries);

            ArrayAdapter<String> tweetsAdapter = new ArrayAdapter<String>(MyApplication.getAppContext(), android.R.layout.simple_spinner_item, data.twitter_tweets);
            tweetsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(tweetsAdapter);

            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mResultClickListener.onSearchResultClicked(
                            mSearchResultList.get(getAdapterPosition())
                    );

                    mSpinner.performClick();
                }
            });
        }

        void bind(DummyData repo) {
            mSearchResultTV.setText(repo.twitter_user);
        }
    }
}
