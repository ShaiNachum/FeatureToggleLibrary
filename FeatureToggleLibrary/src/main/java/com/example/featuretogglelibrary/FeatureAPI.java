package com.example.featuretogglelibrary;

import com.example.featuretogglelibrary.model.CreateFeatureRequest;
import com.example.featuretogglelibrary.model.CreateFeatureResponse;
import com.example.featuretogglelibrary.model.Feature;
import com.example.featuretogglelibrary.model.UpdateFeatureRequest;
import com.example.featuretogglelibrary.model.UpdateFeatureResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeatureAPI {

    // endpoint for creating a feature
    @POST("/feature-toggle")
    Call<CreateFeatureResponse> createFeature(@Body CreateFeatureRequest request);


    // endpoint for getting all features for a package
    @GET("/feature-toggles/{package_name}")
    Call<List<Feature>> getAllFeatures(
            @Path(value = "package_name", encoded = true) String package_name
    );


    // endpoint for getting a single feature by id for a package
    @GET("/feature-toggle/{package_name}/{feature_id}")
    Call<Feature> getFeatureById(
            @Path(value = "package_name", encoded = true) String package_name,
            @Path(value = "feature_id", encoded = true) String feature_id
    );


    // endpoint for getting all active features for a package
    @GET("/feature-toggles/{package_name}/active")
    Call<List<Feature>> loadActiveFeatures(
            @Path(value = "package_name", encoded = true) String package_name
    );


    // endpoint for getting all features for a package by date
    @GET("/feature-toggles/{package_name}/by-date")
    Call<List<Feature>> getFeaturesByDate(
            @Path(value = "package_name", encoded = true) String package_name,
            @Query("date") String date
    );


    // endpoint for updating feature dates for a package and feature id
    @PUT("/feature-toggle/{package_name}/{feature_id}/update-dates")
    Call<UpdateFeatureResponse> updateFeatureDates(
            @Path(value = "package_name", encoded = true) String package_name,
            @Path(value = "feature_id", encoded = true) String feature_id,
            @Body UpdateFeatureRequest request
    );



}