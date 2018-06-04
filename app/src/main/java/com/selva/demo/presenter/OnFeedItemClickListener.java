package com.selva.demo.presenter;

import com.selva.demo.model.Feeds;

/**
 * Interface is to notify the feed on item clicks
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/4/2018
 */
public interface OnFeedItemClickListener {
    void onItemClick(Feeds feed);
}
