package com.example.featuretogglelibrary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeatureAPI {
    @GET("/feature-toggles/{package_name}/active")
    Call<List<Feature>> loadActiveFeatures(
            @Path(value= "package_name", encoded = true) String package_name
    );


}
