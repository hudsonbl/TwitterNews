package com.example.twitternews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitternews.Data.WebData;

import java.util.List;

public class WebSearchAdapter extends RecyclerView.Adapter<WebSearchAdapter.WebResultViewHolder> {
    private List<WebData> mWebData;
    private OnWebClickedListener mClickListener;

    public WebSearchAdapter(OnWebClickedListener listener) {
        mClickListener = listener;
    }

    interface OnWebClickedListener{
        void onWebClickedListener(WebData webData);
    }

    public void updateWebData(List<WebData> data){
        mWebData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WebResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.web_item, parent, false);
        return new WebResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebSearchAdapter.WebResultViewHolder holder, int position) {
        holder.bind(mWebData.get(position));
    }

    @Override
    public int getItemCount() {
        if(mWebData != null){
            return mWebData.size();
        }else{
            return 0;
        }
    }

    public class WebResultViewHolder extends RecyclerView.ViewHolder{
        private TextView mWebNameTV;

        public  WebResultViewHolder(@NonNull View itemView){
            super(itemView);
            mWebNameTV = itemView.findViewById(R.id.tv_web_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onWebClickedListener(
                            mWebData.get(getAdapterPosition())
                    );
                }
            });
        }

        public void bind(WebData webData) {
            mWebNameTV.setText(webData.Title);
        }
    }
}
