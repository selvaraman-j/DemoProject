package com.selva.demo.presenter;

import com.selva.demo.model.Response;

/**
 * Interface is to update the feeds response once downloaded
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/1/2018
 */

public interface FeedsView {
    void updateFeedsView(Response response);
}
