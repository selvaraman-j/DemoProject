package com.selva.demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Class is to store feed items
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public class Feeds implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("imageHref")
    private String imageHref;

    /**
     * Empty constructor
     */
    public Feeds() {
    }

    /**
     * Method is to return title of feed
     *
     * @return String the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method is to return description of feed
     *
     * @return String the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method is to return image of feed
     *
     * @return String the URL
     */
    public String getImageHref() {
        return imageHref;
    }
}
