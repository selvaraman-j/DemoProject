package com.selva.demo.presenter;

/**
 * Interface is to update the activity's action bar title
 * and display the message in snack bar
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/1/2018
 */

public interface FeedsCallback {
    void updateTitle(String title);

    void showSnackBarMessage(String message);
}
