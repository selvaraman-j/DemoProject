package com.selva.demo.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selva.demo.R;
import com.selva.demo.model.Feeds;
import com.selva.demo.view.activity.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * Class is to display the detailed of feed
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/4/2018
 */
public class FeedDetailedFragment extends Fragment {
    private static final String ARGUMENT_FEED = "ARGUMENT_FEED";

    /**
     * Method is to initialise instance of feeds details fragment
     *
     * @param feeds The Feeds
     * @return FeedDetailedFragment the instance of FeedDetailedFragment
     */
    public static FeedDetailedFragment getInstance(Feeds feeds) {
        FeedDetailedFragment feedDetailedFragment = new FeedDetailedFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_FEED, feeds);
        feedDetailedFragment.setArguments(bundle);
        return feedDetailedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_details, container, false);
        initialiseViews(view);
        return view;
    }

    /**
     * Method is to initialise feed details fragment views with value
     *
     * @param view The View
     */
    private void initialiseViews(View view) {
        TextView titleText = view.findViewById(R.id.text_feed_detail_title);
        ImageView feedImage = view.findViewById(R.id.image_feed_detail);
        TextView descriptionText = view.findViewById(R.id.text_feed_detail_description);

        Bundle bundle = getArguments();
        if (null != bundle && null != bundle.get(ARGUMENT_FEED)) {
            Feeds feeds = (Feeds) bundle.get(ARGUMENT_FEED);
            if (null == feeds) return;
            if (null != feeds.getTitle()) {
                titleText.setText(feeds.getTitle());
            }
            if (null != feeds.getImageHref()) {
                Picasso.get().load(feeds.getImageHref()).into(feedImage);
            }
            if (null != feeds.getDescription()) {
                descriptionText.setText(feeds.getDescription());
            }
        }
        // show the back arrow navigation icon
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showOrHideNavIcon(true);
        }
    }
}
