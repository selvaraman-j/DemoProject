package com.selva.demo;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.selva.demo.utils.NetworkUtils;
import com.selva.demo.view.activity.MainActivity;
import com.selva.demo.view.fragment.FeedsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;

/**
 * Class is to test the ui of the application
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/4/2018
 */
@RunWith(AndroidJUnit4.class)
public class FeedsFragmentTest {
    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new
            ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FeedsFragment.getInstance());
    }

    /**
     * Method is to test the actionbar is displayed
     */
    @Test
    public void testActionBarIsDisplayed() {
        if (null != activityTestRule.getActivity().getSupportActionBar()
                && null != activityTestRule.getActivity().getSupportActionBar().getTitle()) {
            onView(withText(activityTestRule.getActivity().getSupportActionBar().getTitle().toString()))
                    .check(matches(isDisplayed()));
        }
    }

    /**
     * Method is to check all the feeds are displayed in list
     */
    @Test
    public void testFeedAllItemsIsDisplayed() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.feeds_view);
        int totalItems = recyclerView.getAdapter().getItemCount();

        if (activityTestRule.getActivity().findFeedFragment() instanceof FeedsFragment) {
            assertEquals(totalItems, ((FeedsFragment) activityTestRule
                    .getActivity().findFeedFragment()).getFeedsListSize());
        }
    }

    /**
     * Method is to test refresh layout
     */
    @Test
    public void testPullDownRefresh() {
        onView(withId(R.id.swipe_layout)).perform(swipeDown());
    }

    /**
     * Method is to test whether the no internet connection view is displayed or not
     * when the device is not connected to internet
     */
    @Test
    public void testNoInternetConnectionViewIsDisplayed() {
        if (!NetworkUtils.isNetworkConnected()) {
            onView(withText(R.string.no_internet_connection)).check(matches(isDisplayed()));
        }
    }

    /**
     * Method is to test the orientation of the screen
     */
    @Test
    public void orientationChangeTest() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;
        activityTestRule.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT)
                        ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
