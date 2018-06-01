package com.selva.demo.service;

import com.selva.demo.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface for API
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public interface ApiService {
    String BASE_URL = "https://dl.dropboxusercontent.com/";

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Response> getFeeds();
}