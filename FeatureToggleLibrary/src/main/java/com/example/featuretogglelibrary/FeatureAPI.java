package com.example.featuretogglelibrary;

import com.example.featuretogglelibrary.model.CreateFeatureRequest;
import com.example.featuretogglelibrary.model.CreateFeatureResponse;
import com.example.featuretogglelibrary.model.Feature;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeatureAPI {
    @GET("/feature-toggles/{package_name}/active")
    Call<List<Feature>> loadActiveFeatures(
            @Path(value = "package_name", encoded = true) String package_name
    );

    // New endpoint for creating a feature
    @POST("/feature-toggle")
    Call<CreateFeatureResponse> createFeature(@Body CreateFeatureRequest request);

    // New endpoint for getting all features for a package
    @GET("/feature-toggles/{package_name}")
    Call<List<Feature>> getAllFeatures(
            @Path(value = "package_name", encoded = true) String package_name
    );
}