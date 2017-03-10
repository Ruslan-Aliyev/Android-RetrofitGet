package com.test.retrofitget.Service;

import com.test.retrofitget.Model.Model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Victorious on 18/09/2016.
 */
public interface Service {
    @GET("/retrofit/{version}/get.php")
    public void getAsync(@Path("version") String version, @Query("test_name") String test_name, Callback<Model> response);

    @GET("/retrofit/{version}/get.php")
    public Model getSync(@Path("version") String version, @Query("test_name") String test_name);
}
