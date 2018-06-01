package com.selva.demo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class is to store feed response
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public class Response {

    @SerializedName("title")
    private String title;
    @SerializedName("rows")
    private List<Feeds> feedsList;

    /**
     * Empty constructor
     */
    public Response() {
    }

    /**
     * Method is to return title of application
     *
     * @return String the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method is to return list of feeds
     *
     * @return List the feed list
     */
    public List<Feeds> getFeedsList() {
        return feedsList;
    }
}
