package com.example.featuretogglelibrary;

import com.example.featuretogglelibrary.callbacks.Callback_Features;
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
    private static final String BASE_URL = "feature-toggle-library-beckend.vercel.app";
    private Callback_Features callbackFeatures;

    public void setCallbackFeatures(Callback_Features callbackFeatures) {
        this.callbackFeatures = callbackFeatures;
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

}
