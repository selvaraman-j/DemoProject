package com.selva.demo.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selva.demo.R;
import com.selva.demo.model.Feeds;

import java.util.List;

/**
 * Class is to inflate feeds view list items
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/1/2018
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder> {
    private List<Feeds> mFeedsList;

    public FeedsAdapter(List<Feeds> mFeedsList) {
        this.mFeedsList = mFeedsList;
    }

    @NonNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed
                , parent, false);
        return new FeedsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsViewHolder holder, int position) {
        Feeds feeds = mFeedsList.get(position);
        if (null != feeds) {
            holder.mTextViewTitle.setText(feeds.getTitle());
            holder.mTextViewDescription.setText(feeds.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mFeedsList.size();
    }

    class FeedsViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDescription;
        private ImageView mImageViewFeed;

        FeedsViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_item_title);
            mTextViewDescription = itemView.findViewById(R.id.text_item_description);
            mImageViewFeed = itemView.findViewById(R.id.image_item_feed);
        }
    }
}
