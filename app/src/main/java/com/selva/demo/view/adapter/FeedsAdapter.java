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
import com.selva.demo.presenter.OnFeedItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Class is to inflate feeds view list items
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/1/2018
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder> {
    private final List<Feeds> mFeedsList;
    private final OnFeedItemClickListener mOnFeedItemClickListener;

    public FeedsAdapter(List<Feeds> mFeedsList, OnFeedItemClickListener onFeedItemClickListener) {
        this.mFeedsList = mFeedsList;
        this.mOnFeedItemClickListener = onFeedItemClickListener;
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
            Picasso.get().load(feeds.getImageHref()).into(holder.mImageViewFeed);
        }
    }

    @Override
    public int getItemCount() {
        return mFeedsList.size();
    }

    class FeedsViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewTitle;
        private final TextView mTextViewDescription;
        private final ImageView mImageViewFeed;

        FeedsViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_item_title);
            mTextViewDescription = itemView.findViewById(R.id.text_item_description);
            mImageViewFeed = itemView.findViewById(R.id.image_item_feed);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnFeedItemClickListener) {
                        mOnFeedItemClickListener.onItemClick(mFeedsList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
