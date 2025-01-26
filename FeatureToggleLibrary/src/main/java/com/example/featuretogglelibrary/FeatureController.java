package com.example.featuretogglelibrary;

import com.example.featuretogglelibrary.callbacks.Callback_CreateFeature;
import com.example.featuretogglelibrary.callbacks.Callback_Features;
import com.example.featuretogglelibrary.model.CreateFeatureRequest;
import com.example.featuretogglelibrary.model.CreateFeatureResponse;
import com.example.featuretogglelibrary.model.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeatureController {
    private static final String BASE_URL = "https://feature-toggle-library-beckend.vercel.app/";

    private Callback_Features callbackFeatures;
    private Callback_CreateFeature createFeatureCallback;

    public void setCallbackFeatures(Callback_Features callbackFeatures) {
        this.callbackFeatures = callbackFeatures;
    }

    public void setCreateFeatureCallback(Callback_CreateFeature callback) {
        this.createFeatureCallback = callback;
    }

    private FeatureAPI getAPI(){
        Gson gson  = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(FeatureAPI.class);
    }

    private Callback<List<Feature>> listCallback= new Callback<List<Feature>>() {
        @Override
        public void onResponse(Call<List<Feature>> call, Response<List<Feature>> response) {
            callbackFeatures.ready(response.body());
        }

        @Override
        public void onFailure(Call<List<Feature>> call, Throwable throwable) {
            callbackFeatures.fail(throwable.getMessage());
        }
    };


    public void fetchAllActiveFeatures(String packageName, Callback_Features callbackFeatures){
        setCallbackFeatures(callbackFeatures);
        Call<List<Feature>> call = getAPI().loadActiveFeatures(packageName);
        call.enqueue(listCallback);
    }


    public void createFeature(CreateFeatureRequest request, Callback_CreateFeature callback) {
        setCreateFeatureCallback(callback);
        Call<CreateFeatureResponse> call = getAPI().createFeature(request);

        call.enqueue(new Callback<CreateFeatureResponse>() {
            @Override
            public void onResponse(Call<CreateFeatureResponse> call,
                                   Response<CreateFeatureResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    createFeatureCallback.onSuccess(response.body().get_id());
                } else {
                    createFeatureCallback.onError("Failed to create feature: " +
                            response.code());
                }
            }

            @Override
            public void onFailure(Call<CreateFeatureResponse> call, Throwable t) {
                createFeatureCallback.onError(t.getMessage());
            }
        });
    }


    public void fetchAllFeatures(String packageName, Callback_Features callbackFeatures) {
        // We can reuse the existing callback since it handles the same type of response
        setCallbackFeatures(callbackFeatures);

        // Make the API call to get all features
        Call<List<Feature>> call = getAPI().getAllFeatures(packageName);
        call.enqueue(listCallback);
    }



}
